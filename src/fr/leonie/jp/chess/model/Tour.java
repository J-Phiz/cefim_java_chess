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
    public ArrayList<Deplacement> deplacementsPossibles(Plateau plateau, Carreau carreau) {
        ArrayList<Deplacement> deplacements = new ArrayList<>();
        int indexColonneDepart = carreau.getColonne();
        int indexLigneDepart = carreau.getLigne();

        // déplacement vers la droite
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart + i, indexLigneDepart);
            if (destination != null) {
                Deplacement deplacement = new Deplacement(
                        this,
                        carreau,
                        destination,
                        destination.getContenu()
                );
                deplacements.add(deplacement);

                if(destination.getContenu() != null) {
                    break;
                }

            } else {
                break;
            }
        }

        // déplacement vers la gauche
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart - i, indexLigneDepart);
            if (destination != null) {
                Deplacement deplacement = new Deplacement(
                        this,
                        carreau,
                        destination,
                        destination.getContenu()
                );
                deplacements.add(deplacement);

                if(destination.getContenu() != null) {
                    break;
                }

            } else {
                break;
            }
        }

        // déplacement vers le haut
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart, indexLigneDepart + i);
            if (destination != null) {
                Deplacement deplacement = new Deplacement(
                        this,
                        carreau,
                        destination,
                        destination.getContenu()
                );
                deplacements.add(deplacement);

                if(destination.getContenu() != null) {
                    break;
                }

            } else {
                break;
            }
        }

        // déplacement vers le bas
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart, indexLigneDepart - i);
            if (destination != null) {
                Deplacement deplacement = new Deplacement(
                        this,
                        carreau,
                        destination,
                        destination.getContenu()
                );
                deplacements.add(deplacement);

                if(destination.getContenu() != null) {
                    break;
                }

            } else {
                break;
            }
        }

        return deplacements;
    }

}
