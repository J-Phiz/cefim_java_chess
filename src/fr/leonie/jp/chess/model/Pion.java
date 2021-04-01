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

        int colonne = carreau.getColonne();
        int ligne = carreau.getLigne();
        Carreau newCarreau;

        // Check une case devant
        ligne += this.couleur == CouleurPiece.BLANC ? -1 : 1;

        newCarreau = checkMove(colonne, ligne);
        if(newCarreau != null) {
            deplacements.add(new Deplacement(
                    this,
                    carreau,
                    plateau.getCarreaux()[colonne][ligne],
                    plateau.getCarreaux()[colonne][ligne].getContenu()
            ));
        }

        //Check 2 cases devant si à la position inititale
        if ((carreau.getLigne() == 6 && this.couleur == CouleurPiece.BLANC) ||
                (carreau.getLigne() == 1 && this.couleur == CouleurPiece.NOIR)) {

            ligne += this.couleur == CouleurPiece.BLANC ? -1 : 1;

            newCarreau = checkMove(colonne, ligne);
            if(newCarreau != null) {
                deplacements.add(new Deplacement(
                        this,
                        carreau,
                        plateau.getCarreaux()[colonne][ligne],
                        plateau.getCarreaux()[colonne][ligne].getContenu()
                ));
            }
        }

        return deplacements;
    }

}
