package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Fou extends Piece {

    public Fou(String nom, CouleurPiece couleur) {
        super(
                nom,
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_bishop.png": absPath + "black_bishop.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau) {
        return null;
    }

}
