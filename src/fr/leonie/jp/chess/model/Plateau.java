package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;
import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Plateau {

    private final Carreau[][] carreaux = new Carreau[8][8];
    private final ArrayList<Piece> piecesBlanches;
    private final ArrayList<Piece> piecesNoires;
    private Carreau carreauSelectionne = null;

    private static final Plateau INSTANCE = new Plateau();

    private Plateau() {
        CouleurCarreau couleur;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(i % 2 != j % 2) {
                    couleur = CouleurCarreau.BLANC;
                } else {
                    couleur = CouleurCarreau.ROSE;
                }
                carreaux[i][j] = new Carreau(couleur, i, j);
            }
        }
        piecesBlanches = new ArrayList<Piece>();
        piecesNoires = new ArrayList<Piece>();
    }

    public static Plateau getInstance() {
        return INSTANCE;
    }

    public Carreau[][] getCarreaux() {
        return carreaux;
    }

    public Carreau getCarreauSelectionne() {
        // on renvoi l'instance susceptible d'être modifiée
        return carreauSelectionne;
    }

    public void setCarreauSelectionne(Carreau carreauSelectionne) {
        this.carreauSelectionne = carreauSelectionne;
    }

    public void initialiserPlateau() {
        creationAutresPieces(piecesNoires, CouleurPiece.NOIR, 0);
        creationPions(piecesNoires, CouleurPiece.NOIR, 1);
        creationPions(piecesBlanches, CouleurPiece.BLANC, 6);
        creationAutresPieces(piecesBlanches, CouleurPiece.BLANC, 7);

    }

    private void creationPions(ArrayList<Piece> pieces, CouleurPiece couleur, int ligne) {
        Piece piece;

        for (int i = 0; i < 8 ; i++) {
            piece = new Pion("Pion" + i, couleur);
            carreaux[i][ligne].setContenu(piece);
            pieces.add(piece);
        }
    }

    private void creationAutresPieces(ArrayList<Piece> pieces, CouleurPiece couleur, int ligne) {
        Piece piece;

        piece = new Tour("Tour1", couleur);
        carreaux[0][ligne].setContenu(piece);
        pieces.add(piece);

        piece = new Cavalier("Cavalier1", couleur);
        carreaux[1][ligne].setContenu(piece);
        pieces.add(piece);

        piece = new Fou("Fou1", couleur);
        carreaux[2][ligne].setContenu(piece);
        pieces.add(piece);

        piece = new Reine("Reine", couleur);
        carreaux[3][ligne].setContenu(piece);
        pieces.add(piece);

        piece = new Roi("Roi", couleur);
        carreaux[4][ligne].setContenu(piece);
        pieces.add(piece);

        piece = new Fou("Fou2", couleur);
        carreaux[5][ligne].setContenu(piece);
        pieces.add(piece);

        piece = new Cavalier("Cavalier2", couleur);
        carreaux[6][ligne].setContenu(piece);
        pieces.add(piece);

        piece = new Tour("Tour2", couleur);
        carreaux[7][ligne].setContenu(piece);
        pieces.add(piece);
    }

    public ArrayList<Carreau> deplacementsPossibles(Carreau carreau) {
        return null;
    }

}
