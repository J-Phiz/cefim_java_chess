package fr.leonie.jp.chess.model;

import java.util.ArrayList;

public abstract class Piece implements Cloneable {

    protected enum Couleur {
        BLANC, NOIR
    }

    protected final String nom;
    protected final Couleur couleur;
    protected final String image;

    public Piece(String nom, Couleur couleur, String image) {
        this.nom = nom;
        this.couleur = couleur;
        this.image = image;
    }

    public abstract ArrayList<Deplacement> deplacementsPossibles(Plateau plateau);

    @Override
    public Object clone() {
        Piece p = null;
        try {
            p = (Piece) super.clone();
        } catch (CloneNotSupportedException e) {} // Won't happen
        return p;
    }

}
