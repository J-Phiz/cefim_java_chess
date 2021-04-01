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
        Deplacement deplacement;

        // Check une case devant
        deplacement = checkMove(carreau, 0, this.couleur == CouleurPiece.BLANC ? -1 : 1);
        if(deplacement != null) {
            deplacements.add(deplacement);
        }

        // Check 2 cases devant si à la position inititale
        if ((carreau.getLigne() == 6 && this.couleur == CouleurPiece.BLANC) ||
                (carreau.getLigne() == 1 && this.couleur == CouleurPiece.NOIR)) {
            deplacement = checkMove(carreau, 0, this.couleur == CouleurPiece.BLANC ? -2 : 2);
            if(deplacement != null) {
                deplacements.add(deplacement);
            }
        }

        // Check 1ere diagonale pour manger la piece d'un adversaire
        deplacement = checkEat(carreau, -1, this.couleur == CouleurPiece.BLANC ? -1 : 1);
        if(deplacement != null) {
            deplacements.add(deplacement);
        }

        // Check 2eme diagonale pour manger la piece d'un adversaire
        deplacement = checkEat(carreau, 1, this.couleur == CouleurPiece.BLANC ? -1 : 1);
        if(deplacement != null) {
            deplacements.add(deplacement);
        }

        return deplacements;
    }

    @Override
    protected Deplacement checkMove(Carreau carreau, int deltaColonne, int deltaLigne) {
        Carreau[][] carreaux = Plateau.getInstance().getCarreaux();
        int indexColonneArrivee = carreau.getColonne() + deltaColonne;
        int indexLigneArrivee = carreau.getLigne() + deltaLigne;

        Deplacement deplacement = null;
        if ((indexColonneArrivee >= 0 && indexColonneArrivee <= 7 && indexLigneArrivee >= 0 && indexLigneArrivee <= 7) &&
                (carreaux[indexColonneArrivee][indexLigneArrivee].getContenu() == null)) {
            Carreau destination = carreaux[indexColonneArrivee][indexLigneArrivee];
            deplacement = new Deplacement(
                    this,
                    carreau,
                    destination,
                    destination.getContenu()
            );
        }
        return deplacement;
    }

    protected Deplacement checkEat(Carreau carreau, int deltaColonne, int deltaLigne) {
        Carreau[][] carreaux = Plateau.getInstance().getCarreaux();
        int indexColonneArrivee = carreau.getColonne() + deltaColonne;
        int indexLigneArrivee = carreau.getLigne() + deltaLigne;

        Deplacement deplacement = null;
        if ((indexColonneArrivee >= 0 && indexColonneArrivee <= 7 && indexLigneArrivee >= 0 && indexLigneArrivee <= 7) &&
                (carreaux[indexColonneArrivee][indexLigneArrivee].getContenu() != null) &&
                (carreaux[indexColonneArrivee][indexLigneArrivee].getContenu().getCouleur() != couleur)) {
            Carreau destination = carreaux[indexColonneArrivee][indexLigneArrivee];
            deplacement = new Deplacement(
                    this,
                    carreau,
                    destination,
                    destination.getContenu()
            );
        }
        return deplacement;
    }
}
