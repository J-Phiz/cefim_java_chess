package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Pion extends Piece {

    public Pion(CouleurPiece couleur) {
        super(
                "pion",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_pawn.png": absPath + "black_pawn.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau) {

        ArrayList<Deplacement> deplacements = new ArrayList<>();

        Deplacement deplacement = new Deplacement(
                this,
                carreau,
                plateau.getCarreaux()[carreau.getColonne()][carreau.getLigne()+1],
                plateau.getCarreaux()[carreau.getColonne()][carreau.getLigne()+1].getContenu()
        );
        deplacements.add(deplacement);

        return deplacements;
    }

}
