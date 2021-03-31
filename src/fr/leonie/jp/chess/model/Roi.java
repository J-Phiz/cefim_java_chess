package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Roi extends Piece {

    public Roi(String nom, CouleurPiece couleur) {
        super(
                nom,
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_king.png": absPath + "black_king.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau) {
        return null;
    }

}
