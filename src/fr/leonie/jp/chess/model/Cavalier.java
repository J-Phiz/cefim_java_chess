package fr.leonie.jp.chess.model;

import java.util.ArrayList;

public class Cavalier extends Piece {

    public Cavalier(String nom, Couleur couleur, String image) {
        super(nom, couleur, image);
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau) {
        return null;
    }

}
