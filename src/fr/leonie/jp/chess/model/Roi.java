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
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau) {
        ArrayList<Deplacement> deplacements = new ArrayList<>();
        int indexColonneDepart = carreau.getColonne();
        int indexLigneDepart = carreau.getLigne();

        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                Carreau destination = this.checkMove(indexColonneDepart + i, indexLigneDepart + j);
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
        }

        return deplacements;
    }

}
