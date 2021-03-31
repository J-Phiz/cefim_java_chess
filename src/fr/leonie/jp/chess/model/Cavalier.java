package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Cavalier extends Piece {

    public Cavalier(CouleurPiece couleur) {
        super(
                "cavalier",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_knight.png": absPath + "black_knight.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau) {
        return null;
    }

}
