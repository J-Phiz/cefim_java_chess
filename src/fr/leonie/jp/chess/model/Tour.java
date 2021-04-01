package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.nio.file.Path;
import java.util.ArrayList;

public class Tour extends Piece {

    public Tour(CouleurPiece couleur) {
        super(
                "tour",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_rook.png": absPath + "black_rook.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau) {
        return null;
    }

}
