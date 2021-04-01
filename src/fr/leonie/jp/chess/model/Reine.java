package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Reine extends Piece {

    public Reine(CouleurPiece couleur) {
        super(
                "reine",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_queen.png": absPath + "black_queen.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau) {
        return null;
    }

}
