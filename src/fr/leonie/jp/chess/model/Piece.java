package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Piece implements Cloneable {

    protected static final String absPath = System.getProperty("user.dir") + "/src/resources/pieces/";

    protected final String nom;
    protected final CouleurPiece couleur;
    protected final ImageView image;

    public Piece(String nom, CouleurPiece couleur, String image) {
        this.nom = nom;
        this.couleur = couleur;
        this.image = new ImageView(new Image("file:" + image, 100, 100, false, true));
    }

    public ImageView getImage() {
        return image;
    }

    public CouleurPiece getCouleur() {
        return couleur;
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

    @Override
    public String toString() {
        return "Piece{" +
                "nom='" + nom + '\'' +
                ", couleur=" + couleur +
                ", image='" + image + '\'' +
                '}';
    }
}
