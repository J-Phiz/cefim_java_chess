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

    public void nouvellePartie() {
        // appelle plateau.initialiserPlateau()
    }

    public void nouveauDeplacement() {

    }

    public void annulerDeplacement() {

    }

}
