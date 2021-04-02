package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;

public class Carreau {

    private final CouleurCarreau couleur;
    private Piece contenu;
    private boolean selectionnee;
    private boolean destination;
    private boolean destinationRoque;
    private final int colonne;
    private final int ligne;

    public Carreau(CouleurCarreau couleur, int colonne, int ligne) {
        this.couleur = couleur;
        contenu = null;
        selectionnee = false;
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public Piece getContenu() {
        // Copie défensive
        Piece piece = null;

        if (contenu != null) {
            piece = (Piece) contenu.clone();
        }
        return piece;
    }

    public void setContenu(Piece contenu) {
        this.contenu = contenu;
    }

    public boolean isSelectionnee() {
        return selectionnee;
    }

    public void setSelectionnee(boolean selectionnee) {
        this.selectionnee = selectionnee;
    }

    public boolean isDestination() {
        return destination;
    }

    public void setDestination(boolean destination) {
        this.destination = destination;
    }

    public boolean isDestinationRoque() {
        return destinationRoque;
    }

    public void setDestinationRoque(boolean destinationRoque) {
        this.destinationRoque = destinationRoque;
    }

    public CouleurCarreau getCouleur() {
        return couleur;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    @Override
    public String toString() {
        return "Carreau{" +
                "couleur=" + couleur +
                ", contenu=" + contenu +
                ", selectionnee=" + selectionnee +
                ", destination=" + destination +
                ", colonne=" + colonne +
                ", ligne=" + ligne +
                '}';
    }
}
