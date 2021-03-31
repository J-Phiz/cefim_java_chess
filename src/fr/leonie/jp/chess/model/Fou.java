package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Fou extends Piece {

    public Fou(CouleurPiece couleur) {
        super(
                "fou",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_bishop.png": absPath + "black_bishop.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau) {
        return null;
    }

}
