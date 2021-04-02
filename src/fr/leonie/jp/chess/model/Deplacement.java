package fr.leonie.jp.chess.model;

public class Deplacement {

    private final Piece piece;
    private final Carreau carreauDepart;
    private final Carreau carreauFin;
    private final Piece pieceMangee;
    private final Carreau carreauMangee;
    private boolean isRoque = false;

    public Deplacement(Piece piece, Carreau carreauDepart, Carreau carreauFin, Piece pieceMangee, Carreau carreauMangee) {
        this.piece = piece;
        this.carreauDepart = carreauDepart;
        this.carreauFin = carreauFin;
        this.pieceMangee = pieceMangee;
        this.carreauMangee = carreauMangee;
    }

    public Piece getPiece() {
        return piece;
    }

    public Carreau getCarreauDepart() {
        return carreauDepart;
    }

    public Carreau getCarreauFin() {
        return carreauFin;
    }

    public Piece getPieceMangee() {
        return pieceMangee;
    }

    public boolean isRoque() {
        return isRoque;
    }

    public void setRoque(boolean roque) {
        isRoque = roque;
    }

    public Carreau getCarreauMangee() {
        return carreauMangee;
    }
}
