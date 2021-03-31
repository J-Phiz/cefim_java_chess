package fr.leonie.jp.chess.model;

import java.util.ArrayList;

public abstract class Piece {

    protected enum Couleur {
        BLANCS, NOIRES
    }

    protected String nom;
    protected Couleur couleur;
    protected String image;

    public abstract ArrayList<Deplacement> deplacementsPossibles(Plateau plateau);

}
