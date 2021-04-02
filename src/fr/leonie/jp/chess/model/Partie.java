package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Partie {

    private final Plateau plateau;
    private final ArrayList<Deplacement> deplacements;
    private int nbTours;
    private boolean roiNoirMenace = false;
    private boolean roiBlancMenace = false;
    private boolean roiNoirMat = false;
    private boolean roiBlancMat = false;

    private static final Partie INSTANCE = new Partie();

    // private constructor
    private Partie() {
        nbTours = 0;
        plateau = Plateau.getInstance();
        deplacements = new ArrayList<Deplacement>();
    }

    public static Partie getInstance() {
        return INSTANCE;
    }

    public int getNbTours() {
        return nbTours;
    }

    public boolean isRoiNoirMenace() {
        return roiNoirMenace;
    }

    public boolean isRoiBlancMenace() {
        return roiBlancMenace;
    }

    public boolean isRoiNoirMat() {
        return roiNoirMat;
    }

    public boolean isRoiBlancMat() {
        return roiBlancMat;
    }

    public void nouvellePartie() {
        nbTours = 0;
        roiNoirMenace = false;
        roiBlancMenace = false;
        roiNoirMat = false;
        roiBlancMat = false;
        for(int i = 0; i < deplacements.size(); i++) {
            deplacements.remove(i);
        }
        plateau.initialiserPlateau();
    }

    public void nouveauDeplacement(Deplacement deplacement) {
        deplacement.getCareauDepart().setContenu(null);
        deplacement.getCarreauFin().setContenu(deplacement.getPiece());

        deplacements.add(deplacement);

        checkKingThreaten();
        checkKingMat();

        nbTours++;
    }

    public void nouveauDeplacementSimul(Deplacement deplacement) {
        deplacement.getCareauDepart().setContenu(null);
        deplacement.getCarreauFin().setContenu(deplacement.getPiece());

        deplacements.add(deplacement);

        checkKingThreaten();
    }

    public void annulerDeplacement() {
        int indexDernierDeplacement = deplacements.size() - 1;

        if(indexDernierDeplacement >= 0) { // inutile en realite car bouton disable
            Deplacement deplacement = deplacements.get(indexDernierDeplacement);

            deplacement.getCareauDepart().setContenu(deplacement.getPiece());
            deplacement.getCarreauFin().setContenu(deplacement.getPieceMangee());

            deplacements.remove(indexDernierDeplacement);

            checkKingThreaten();

            nbTours--;
        }
    }

    public void annulerDeplacementSimul() {
        int indexDernierDeplacement = deplacements.size() - 1;

        if(indexDernierDeplacement >= 0) { // inutile en realite car bouton disable
            Deplacement deplacement = deplacements.get(indexDernierDeplacement);

            deplacement.getCareauDepart().setContenu(deplacement.getPiece());
            deplacement.getCarreauFin().setContenu(deplacement.getPieceMangee());

            deplacements.remove(indexDernierDeplacement);

            checkKingThreaten();
        }
    }

    private void checkKingThreaten() {
        ArrayList<Deplacement> deplacementsMenancantRois = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(plateau.getCarreaux()[i][j].getContenu() != null) {
                    deplacementsMenancantRois.addAll(plateau.getCarreaux()[i][j].getContenu().isKingThreaten(plateau.getCarreaux()[i][j]));
                }
            }
        }
        roiBlancMenace = deplacementsMenancantRois.stream().anyMatch(d -> d.getPieceMangee().getCouleur() == CouleurPiece.BLANC);
        roiNoirMenace = deplacementsMenancantRois.stream().anyMatch(d -> d.getPieceMangee().getCouleur() == CouleurPiece.NOIR);
    }

    private void checkKingMat() {
        ArrayList<Deplacement> deplacementsPossiblesBlancs = new ArrayList<>();
        ArrayList<Deplacement> deplacementsPossiblesNoirs = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(plateau.getCarreaux()[i][j].getContenu() != null) {
                    if(plateau.getCarreaux()[i][j].getContenu().getCouleur() == CouleurPiece.BLANC) {
                        deplacementsPossiblesBlancs.addAll(plateau.deplacementsPossibles(plateau.getCarreaux()[i][j]));
                    }
                    if(plateau.getCarreaux()[i][j].getContenu().getCouleur() == CouleurPiece.NOIR) {
                        deplacementsPossiblesNoirs.addAll(plateau.deplacementsPossibles(plateau.getCarreaux()[i][j]));
                    }
                }
            }
        }

        roiBlancMat = deplacementsPossiblesBlancs.size() == 0;
        roiNoirMat = deplacementsPossiblesNoirs.size() == 0;
    }

}
