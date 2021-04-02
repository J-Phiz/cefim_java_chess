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
    public ArrayList<Deplacement> deplacementsPossibles(Carreau carreau) {
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

        // Check Passe en avant
        if (
                ((carreau.getLigne() == 3 && this.couleur == CouleurPiece.BLANC) ||
                (carreau.getLigne() == 4) && this.couleur == CouleurPiece.NOIR)) {
            deplacement = checkPasseAvant(carreau, -1, this.couleur == CouleurPiece.BLANC ? -1 : 1);
            if(deplacement != null) {
                deplacements.add(deplacement);
            }
            deplacement = checkPasseAvant(carreau, 1, this.couleur == CouleurPiece.BLANC ? -1 : 1);
            if(deplacement != null) {
                deplacements.add(deplacement);
            }
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

    protected Deplacement checkPasseAvant(Carreau carreau, int deltaColonne, int deltaLigne) {
        Partie partie = Partie.getInstance();
        Carreau[][] carreaux = Plateau.getInstance().getCarreaux();
        int indexColonneArrivee = carreau.getColonne() + deltaColonne;
        int indexLigneArrivee = carreau.getLigne() + deltaLigne;
        int indexColonneMangee = carreau.getColonne() + deltaColonne;
        int indexligneMangee = carreau.getLigne();
        Carreau carreauFinPreviousDeplacement = partie.getLastDeplacement().getCarreauFin();

        Deplacement deplacement = null;
        if ((indexColonneArrivee >= 0 && indexColonneArrivee <= 7) &&
                (indexLigneArrivee >= 0 && indexLigneArrivee <= 7) &&
                (indexColonneMangee >= 0 && indexColonneMangee <= 7) &&
                (indexligneMangee >= 0 && indexligneMangee <= 7) &&
                (carreaux[indexColonneMangee][indexligneMangee].getContenu() != null) &&
                (carreaux[indexColonneMangee][indexligneMangee].getContenu().getCouleur() != couleur) &&
                (carreaux[indexColonneMangee][indexligneMangee].getContenu().getNom().equals("pion")) &&
                (carreaux[indexColonneMangee][indexligneMangee] == carreauFinPreviousDeplacement)
        ) {
            Carreau destination = carreaux[indexColonneArrivee][indexLigneArrivee];
            deplacement = new Deplacement(
                    this,
                    carreau,
                    destination,
                    null//carreaux[indexColonneMangee][indexligneMangee].getContenu()
            );
        }
        return deplacement;
    }
}
