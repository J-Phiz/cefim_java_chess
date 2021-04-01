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
