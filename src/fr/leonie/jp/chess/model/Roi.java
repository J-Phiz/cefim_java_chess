package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Roi extends Piece {

    public Roi(CouleurPiece couleur) {
        super(
                "roi",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_king.png": absPath + "black_king.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Carreau carreau) {
        ArrayList<Deplacement> deplacements = new ArrayList<>();

        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                Deplacement deplacement = this.checkMove(carreau, i, j);
                if(deplacement != null) {
                    deplacements.add(deplacement);
                }
            }
        }

        return deplacements;
    }

}
