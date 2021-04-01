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
        int indexColonneDepart = carreau.getColonne();
        int indexLigneDepart = carreau.getLigne();

        int[][] targets = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for(int i = 0; i < targets.length; i++) {
            Carreau destination = this.checkMove(indexColonneDepart + targets[i][0], indexLigneDepart + targets[i][1]);
            if(destination != null) {
                Deplacement deplacement = new Deplacement(
                        this,
                        carreau,
                        destination,
                        destination.getContenu()
                );
                deplacements.add(deplacement);
            }
        }

        return deplacements;
    }

}
