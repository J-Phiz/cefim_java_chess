package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;
import fr.leonie.jp.chess.enumeration.CouleurPiece;
import fr.leonie.jp.chess.enumeration.TypePiece;

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
        // clean avant de regénérer
        for(int i = 0; i < piecesNoires.size(); i++) {
            piecesNoires.remove(i);
        }
        for(int i = 0; i < piecesBlanches.size(); i++) {
            piecesBlanches.remove(i);
        }
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                carreaux[i][j].setContenu(null);
                carreaux[i][j].setSelectionnee(false);
                carreaux[i][j].setDestination(false);
            }
        }

        // création et placement des pièces
        creationAutresPieces(piecesNoires, CouleurPiece.NOIR, 0);
        creationPions(piecesNoires, CouleurPiece.NOIR, 1);
        creationPions(piecesBlanches, CouleurPiece.BLANC, 6);
        creationAutresPieces(piecesBlanches, CouleurPiece.BLANC, 7);

    }

    private void creationPions(ArrayList<Piece> pieces, CouleurPiece couleur, int ligne) {
        Piece piece;

        for (int i = 0; i < 8 ; i++) {
            piece = PieceFactory.getPiece(TypePiece.PION, couleur);
            carreaux[i][ligne].setContenu(piece);
            pieces.add(piece);
        }
    }

    private void creationAutresPieces(ArrayList<Piece> pieces, CouleurPiece couleur, int ligne) {
        Piece piece = null;

        for (int i = 0; i < 8; i++) {
            switch(i) {
                case 0:
                case 7:
                    piece = PieceFactory.getPiece(TypePiece.TOUR, couleur);
                    break;
                case 1:
                case 6:
                    piece = PieceFactory.getPiece(TypePiece.CAVALIER, couleur);
                    break;
                case 2:
                case 5:
                    piece = PieceFactory.getPiece(TypePiece.FOU, couleur);
                    break;
                case 3:
                    piece = PieceFactory.getPiece(TypePiece.REINE, couleur);
                    break;
                case 4:
                    piece = PieceFactory.getPiece(TypePiece.ROI, couleur);
                    break;
            }

            if(piece != null) {
                carreaux[i][ligne].setContenu(piece);
                pieces.add(piece);
            }
        }
    }

    public ArrayList<Carreau> deplacementsPossibles(Carreau carreau) {
        return null;
    }

}
