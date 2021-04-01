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
            if (indexColonneDepart + i >= 0 && indexColonneDepart + i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart + i][indexLigneDepart];
                if (destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        // déplacement vers la gauche
        for(int i = 1; i < 8; i++) {
            if (indexColonneDepart - i >= 0 && indexColonneDepart - i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart - i][indexLigneDepart];
                if (destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        // déplacement vers le haut
        for(int i = 1; i < 8; i++) {
            if (indexLigneDepart + i >= 0 && indexLigneDepart + i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart][indexLigneDepart + i];
                if (destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        // déplacement vers le bas
        for(int i = 1; i < 8; i++) {
            if (indexLigneDepart - i >= 0 && indexLigneDepart - i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart][indexLigneDepart - i];
                if (destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        // déplacement diagonale haut/droit
        for(int i = 1; i < 8; i++) {
            if (indexColonneDepart + i >= 0 && indexColonneDepart + i < 8 && indexLigneDepart + i >= 0 && indexLigneDepart + i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart + i][indexLigneDepart + i];
                if (destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        // déplacement diagonale bas/droit
        for(int i = 1; i < 8; i++) {
            if(indexColonneDepart + i >= 0 && indexColonneDepart + i < 8 && indexLigneDepart - i >= 0 && indexLigneDepart - i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart + i][indexLigneDepart - i];
                if(destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        // déplacement diagonale haut/gauche
        for(int i = 1; i < 8; i++) {
            if (indexColonneDepart - i >= 0 && indexColonneDepart - i < 8 && indexLigneDepart + i >= 0 && indexLigneDepart + i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart - i][indexLigneDepart + i];
                if (destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        // déplacement diagonale bas/gauche
        for(int i = 1; i < 8; i++) {
            if(indexColonneDepart - i >= 0 && indexColonneDepart - i < 8 && indexLigneDepart - i >= 0 && indexLigneDepart - i < 8) {
                Carreau destination = plateau.getCarreaux()[indexColonneDepart - i][indexLigneDepart - i];
                if(destination.getContenu() == null || destination.getContenu().getCouleur() != this.getCouleur()) {
                    Deplacement deplacement = new Deplacement(
                            this,
                            carreau,
                            destination,
                            destination.getContenu()
                    );
                    deplacements.add(deplacement);
                }
                if(destination.getContenu() != null) {
                    break;
                }
            }
        }

        return deplacements;
    }

}
