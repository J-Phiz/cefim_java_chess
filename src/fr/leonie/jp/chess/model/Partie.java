package fr.leonie.jp.chess.model;

import java.util.ArrayList;

public class Partie {

    private final Plateau plateau;
    private final ArrayList<Deplacement> deplacements;
    private int nbTours;

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

    public void nouvellePartie() {
        plateau.initialiserPlateau();
    }

    public void nouveauDeplacement(Deplacement deplacement) {
        //TODO: Supprimer la piece si presente sur le carreau de fin
        deplacement.getCareauDepart().setContenu(null);
        deplacement.getCarreauFin().setContenu(deplacement.getPiece());

        deplacements.add(deplacement);

        nbTours++;
    }

    public void annulerDeplacement() {

    }

}
