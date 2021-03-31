package fr.leonie.jp.chess.model;

import java.util.ArrayList;

public class Fou extends Piece {

    public Fou(String nom, Couleur couleur, String image) {
        super(nom, couleur, image);
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau) {
        return null;
    }

}
