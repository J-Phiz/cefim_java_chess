package fr.leonie.jp.chess.model;

public class Deplacement {

    private Piece piece;
    private Carreau careauDepart;
    private Carreau carreauFin;

    public Deplacement(Piece piece, Carreau careauDepart, Carreau carreauFin) {
        this.piece = piece;
        this.careauDepart = careauDepart;
        this.carreauFin = carreauFin;
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
}
