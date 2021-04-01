package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Cavalier extends Piece {

    public Cavalier(CouleurPiece couleur) {
        super(
                "cavalier",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_knight.png": absPath + "black_knight.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau) {
        ArrayList<Deplacement> deplacements = new ArrayList<>();

        int[][] targets = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for(int i = 0; i < targets.length; i++) {
            Deplacement deplacement = this.checkMove(carreau, targets[i][0], targets[i][1]);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
        }

        return deplacements;
    }

}
