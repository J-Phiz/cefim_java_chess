package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurPiece;

import java.nio.file.Path;
import java.util.ArrayList;

public class Tour extends Piece {

    public Tour(CouleurPiece couleur) {
        super(
                "tour",
                couleur,
                couleur == CouleurPiece.BLANC ? absPath + "white_rook.png": absPath + "black_rook.png"
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

        return deplacements;
    }

}
