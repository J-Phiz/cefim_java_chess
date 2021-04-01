package fr.leonie.jp.chess.model;

public class Deplacement {

    private final Piece piece;
    private final Carreau careauDepart;
    private final Carreau carreauFin;
    private final Piece pieceMangee;

    public Deplacement(Piece piece, Carreau careauDepart, Carreau carreauFin, Piece pieceMangee) {
        this.piece = piece;
        this.careauDepart = careauDepart;
        this.carreauFin = carreauFin;
        this.pieceMangee = pieceMangee;
    }

    public Piece getPiece() {
        return piece;
    }

    public Carreau getCareauDepart() {
        return careauDepart;
    }

    public Carreau getCarreauFin() {
        return carreauFin;
    }

    public Piece getPieceMangee() {
        return pieceMangee;
    }
}
