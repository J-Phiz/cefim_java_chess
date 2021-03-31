package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;

public class Carreau {

    private final CouleurCarreau couleur;
    private Piece contenu;
    private boolean selectionnee;

    public Carreau(CouleurCarreau couleur) {
        this.couleur = couleur;
        contenu = null;
        selectionnee = false;
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
}
