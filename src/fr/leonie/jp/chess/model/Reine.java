package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.util.ArrayList;

public class Reine extends Piece {

    public Reine(CouleurPiece couleur) {
        super(
                "reine",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_queen.png": absPath + "black_queen.png"
        );
    }

    @Override
    public ArrayList<Deplacement> deplacementsPossibles(Carreau carreau) {
        ArrayList<Deplacement> deplacements = new ArrayList<>();

        // déplacement vers la droite
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, i, 0);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

        // déplacement vers la gauche
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, -i, 0);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

        // déplacement vers le haut
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, 0, i);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

        // déplacement vers le bas
        for(int i = 1; i < 8; i++) {
            Deplacement deplacement = this.checkMove(carreau, 0, -i);
            if (deplacement != null) {
                deplacements.add(deplacement);
            }
            if(deplacement == null || deplacement.getCarreauFin().getContenu() != null) {
                break;
            }
        }

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
