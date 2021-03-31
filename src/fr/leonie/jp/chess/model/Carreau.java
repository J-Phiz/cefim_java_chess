package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;

public class Carreau {

    private final CouleurCarreau couleur;
    private Piece contenu;
    private boolean selectionnee;
    private final int colonne;
    private final int ligne;

    public Carreau(CouleurCarreau couleur, int ligne, int colonne) {
        this.couleur = couleur;
        contenu = null;
        selectionnee = false;
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public Piece getContenu() {
        // Copie défensive
        return (Piece) contenu.clone();
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

    public CouleurCarreau getCouleur() {
        return couleur;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
