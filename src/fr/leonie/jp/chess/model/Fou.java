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
        int indexColonneDepart = carreau.getColonne();
        int indexLigneDepart = carreau.getLigne();

        // déplacement diagonale haut/droit
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart + i, indexLigneDepart + i);
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

        // déplacement diagonale bas/droit
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart + i, indexLigneDepart - i);
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

        // déplacement diagonale haut/gauche
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart - i, indexLigneDepart + i);
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

        // déplacement diagonale bas/gauche
        for(int i = 1; i < 8; i++) {
            Carreau destination = this.checkMove(indexColonneDepart - i, indexLigneDepart - i);
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
