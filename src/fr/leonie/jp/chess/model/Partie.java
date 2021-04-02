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

    public boolean isRoqueLeftAllowed() {
        boolean condition1; // le roi n'a pas quitté sa position initiale
        boolean condition2; // la tour n'a pas quitté sa position initiale
        boolean condition3; // le roi n'est pas en échec
        boolean condition4; // les cases entre sont vides

        if(nbTours % 2 == 0) {
            condition1 = deplacements.stream().noneMatch(d -> (d.getCarreauDepart().getLigne() == 7 && d.getCarreauDepart().getColonne() == 4));
            condition2 = deplacements.stream().noneMatch(d-> (d.getCarreauDepart().getLigne() == 7 && d.getCarreauDepart().getColonne() == 0));
            condition3 = !roiBlancMenace;
            condition4 = plateau.getCarreaux()[1][7].getContenu() == null && plateau.getCarreaux()[2][7].getContenu() == null && plateau.getCarreaux()[3][7].getContenu() == null;
        } else {
            condition1 = deplacements.stream().noneMatch(d -> (d.getCarreauDepart().getLigne() == 0 && d.getCarreauDepart().getColonne() == 4));
            condition2 = deplacements.stream().noneMatch(d-> (d.getCarreauDepart().getLigne() == 0 && d.getCarreauDepart().getColonne() == 0));
            condition3 = !roiNoirMenace;
            condition4 = plateau.getCarreaux()[1][0].getContenu() == null && plateau.getCarreaux()[2][0].getContenu() == null && plateau.getCarreaux()[3][0].getContenu() == null;
        }

        return condition1 && condition2 && condition3 && condition4;
    }

    public boolean isRoqueRightAllowed() {
        boolean condition1; // le roi n'a pas quitté sa position initiale
        boolean condition2; // la tour n'a pas quitté sa position initiale
        boolean condition3; // le roi n'est pas en échec
        boolean condition4; // les cases entre sont vides

        if(nbTours % 2 == 0) {
            condition1 = deplacements.stream().noneMatch(d -> (d.getCarreauDepart().getLigne() == 7 && d.getCarreauDepart().getColonne() == 4));
            condition2 = deplacements.stream().noneMatch(d-> (d.getCarreauDepart().getLigne() == 7 && d.getCarreauDepart().getColonne() == 7));
            condition3 = !roiBlancMenace;
            condition4 = plateau.getCarreaux()[5][7].getContenu() == null && plateau.getCarreaux()[6][7].getContenu() == null;
        } else {
            condition1 = deplacements.stream().noneMatch(d -> (d.getCarreauDepart().getLigne() == 0 && d.getCarreauDepart().getColonne() == 4));
            condition2 = deplacements.stream().noneMatch(d-> (d.getCarreauDepart().getLigne() == 0 && d.getCarreauDepart().getColonne() == 7));
            condition3 = !roiNoirMenace;
            condition4 = plateau.getCarreaux()[5][0].getContenu() == null && plateau.getCarreaux()[6][0].getContenu() == null;
        }

        return condition1 && condition2 && condition3 && condition4;
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
        deplacementDesPions(deplacement);
        checkKingMat();
        nbTours++;
    }

    public void deplacementDesPions(Deplacement deplacement) {
        deplacement.getCarreauDepart().setContenu(null);
        deplacement.getCarreauMangee().setContenu(null);
        deplacement.getCarreauFin().setContenu(deplacement.getPiece());
        deplacements.add(deplacement);

        // cas particulier Roque
        if(deplacement.isRoque()) {
            Piece tour;
            Carreau carreauFinTour;
            Carreau carreauDepartTour = plateau.getCarreaux()[deplacement.getCarreauFin().getColonne() + 1][deplacement.getCarreauFin().getLigne()];
            if(carreauDepartTour.getContenu() != null && carreauDepartTour.getContenu().getNom().equals("tour")) {
                tour = carreauDepartTour.getContenu();
                carreauFinTour = plateau.getCarreaux()[deplacement.getCarreauFin().getColonne() - 1][deplacement.getCarreauFin().getLigne()];
            } else {
                carreauDepartTour = plateau.getCarreaux()[deplacement.getCarreauFin().getColonne() - 2][deplacement.getCarreauFin().getLigne()];
                tour = carreauDepartTour.getContenu();
                carreauFinTour = plateau.getCarreaux()[deplacement.getCarreauFin().getColonne() + 1][deplacement.getCarreauFin().getLigne()];
            };
            Deplacement deplacementTour = new Deplacement(tour, carreauDepartTour, carreauFinTour, null, null);
            deplacementTour.setRoque(true);

            deplacementTour.getCarreauDepart().setContenu(null);
            deplacementTour.getCarreauFin().setContenu(deplacementTour.getPiece());
            deplacements.add(deplacementTour);
        }

        checkKingThreaten();
    }

    public void annulerDeplacement() {
        int indexDernierDeplacement = deplacements.size() - 1;

        if(indexDernierDeplacement >= 0) { // inutile en realite car bouton disable
            Deplacement deplacement = deplacements.get(indexDernierDeplacement);

            if(deplacements.get(indexDernierDeplacement).isRoque()) {
                Deplacement deplacementRoi = deplacements.get(indexDernierDeplacement - 1);
                marcheArriereDesPions(deplacementRoi);
            }
            marcheArriereDesPions(deplacement);

            checkKingThreaten();
            nbTours--;
        }
    }

    public void annulerDeplacementSimul() {
        int indexDernierDeplacement = deplacements.size() - 1;

        if(indexDernierDeplacement >= 0) { // inutile en realite car bouton disable
            Deplacement deplacement = deplacements.get(indexDernierDeplacement);

            if(deplacements.get(indexDernierDeplacement).isRoque()) {
                Deplacement deplacementRoi = deplacements.get(indexDernierDeplacement - 1);
                marcheArriereDesPions(deplacementRoi);
            }
            marcheArriereDesPions(deplacement);

            checkKingThreaten();
        }
    }

    public void marcheArriereDesPions(Deplacement deplacement) {
        deplacement.getCarreauDepart().setContenu(deplacement.getPiece());
        deplacement.getCarreauFin().setContenu(null);
        deplacement.getCarreauMangee().setContenu(deplacement.getPieceMangee());
        deplacements.remove(deplacement);
    }

    public Deplacement getLastDeplacement() {
        return deplacements.get(deplacements.size() - 1);
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
