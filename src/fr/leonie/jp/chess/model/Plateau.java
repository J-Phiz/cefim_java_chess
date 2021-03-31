package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;

import java.util.ArrayList;

public class Plateau {

    private final Carreau[][] carreaux = new Carreau[8][8];
    private final ArrayList<Piece> piecesBlanches;
    private final ArrayList<Piece> piecesNoires;

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
                carreaux[i][j] = new Carreau(couleur);
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

    public void initialiserPlateau() {
        // créé les pièces et les place sur les bons carreaux
    }

    public ArrayList<Carreau> deplacementsPossibles(Carreau carreau) {
       return null;
    }

}
