package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Fou extends Piece {

    public Fou(CouleurPiece couleur) {
        super(
                "fou",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_bishop.png": absPath + "black_bishop.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau) {
        ArrayList<Deplacement> deplacements = new ArrayList<>();

        // déplacement diagonale haut/droit
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, i, i);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

        // déplacement diagonale bas/droit
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, i, -i);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

        // déplacement diagonale haut/gauche
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, -i, i);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

        // déplacement diagonale bas/gauche
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, -i, -i);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

        return deplacements;
    }

}
