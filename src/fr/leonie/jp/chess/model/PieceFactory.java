package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;
import fr.leonie.jp.chess.enumeration.TypePiece;

public final class PieceFactory {

    private PieceFactory() {} //empêche l'instanciation

    public static Piece getPiece(TypePiece type, CouleurPiece couleur) {
        Piece piece = null;
        switch(type) {
            case PION:
                piece = new Pion(couleur);
                break;
            case TOUR:
                piece = new Tour(couleur);
                break;
            case CAVALIER:
                piece = new Cavalier(couleur);
                break;
            case FOU:
                piece = new Fou(couleur);
                break;
            case ROI:
                piece = new Roi(couleur);
                break;
            case REINE:
                piece = new Reine(couleur);
                break;
        }
        return piece;
    }
}
