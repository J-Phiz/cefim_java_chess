package fr.leonie.jp.chess.model;

import java.util.ArrayList;

public class Tour extends Piece {

    public Tour(String nom, Couleur couleur, String image) {
        super(nom, couleur, image);
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau) {
        return null;
    }

}
