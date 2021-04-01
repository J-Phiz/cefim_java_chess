package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public abstract class Piece implements Cloneable {

    protected static final String absPath = System.getProperty("user.dir") + "/src/resources/pieces/";

    protected final String nom;
    protected final CouleurPiece couleur;
    protected final String image;

    public Piece(String nom, CouleurPiece couleur, String image) {
        this.nom = nom;
        this.couleur = couleur;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public CouleurPiece getCouleur() {
        return couleur;
    }

    public abstract ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau);

    protected Deplacement checkMove(Carreau carreau, int deltaColonne, int deltaLigne) {
        Carreau[][] carreaux = Plateau.getInstance().getCarreaux();
        int indexColonneArrivee = carreau.getColonne() + deltaColonne;
        int indexLigneArrivee = carreau.getLigne() + deltaLigne;

        Deplacement deplacement = null;
        if ((indexColonneArrivee >= 0 && indexColonneArrivee <= 7 && indexLigneArrivee >= 0 && indexLigneArrivee <= 7) &&
                (carreaux[indexColonneArrivee][indexLigneArrivee].getContenu() == null ||
                        carreaux[indexColonneArrivee][indexLigneArrivee].getContenu().getCouleur() != couleur)) {
            Carreau destination = carreaux[indexColonneArrivee][indexLigneArrivee];
            deplacement = new Deplacement(
                    this,
                    carreau,
                    destination,
                    destination.getContenu()
            );
        }
        return deplacement;
    }

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
