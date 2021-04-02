package fr.leonie.jp.chess.model;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;
import fr.leonie.jp.chess.enumeration.CouleurPiece;
import fr.leonie.jp.chess.enumeration.TypePiece;

import java.util.ArrayList;

/**
 * Classe Singleton qui gère un plateau de jeu d'échecs
 *
 * @Author Léonie Dusart et Jean-Philippe Save
 */
public class Plateau {

    private final Carreau[][] carreaux = new Carreau[8][8];
    private final ArrayList<Piece> piecesBlanches;
    private final ArrayList<Piece> piecesNoires;
    private Carreau carreauSelectionne = null;

    private static final Plateau INSTANCE = new Plateau();

    /**
     * Constructeur : <ul>
     *     <li>Initialise les 64 cases du plateau avec une couleur de fond</li>
     *     <li>Initialise les pièces</li>
     * </ul>
     */
    private Plateau() {
        CouleurCarreau couleur;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(i % 2 != j % 2) {
                    couleur = CouleurCarreau.BLANC;
                } else {
                    couleur = CouleurCarreau.ROSE;
                }
                carreaux[i][j] = new Carreau(couleur, i, j);
            }
        }
        piecesBlanches = new ArrayList<Piece>();
        piecesNoires = new ArrayList<Piece>();
    }

    /**
     * Cette méthode récupère l'instance de cette classe Singleton
     * @return l'instance du Plateau
     */
    public static Plateau getInstance() {
        return INSTANCE;
    }

    /**
     * Cette méthode récupère la liste des cases du plateau ordonnée en lignes et colonnes
     * @return tableau de à 2 dimensions [colonne][ligne] d'Objets Carreau
     */
    public Carreau[][] getCarreaux() {
        return carreaux;
    }

    /**
     * Cette méthode récupère la case actuellement sélectionnée par le joueur
     * @return l'objet Carreau correspondant à la case sélectionnée ou null si aucune case n'est sélectionnée
     */
    public Carreau getCarreauSelectionne() {
        // on renvoi l'instance susceptible d'être modifiée
        return carreauSelectionne;
    }

    /**
     * Cette méthode met à jour la case sélectionnée par le joueur
     * @param carreauSelectionne l'objet Carreau à sélectionner ou null si aucune case n'est à sélectionner
     */
    public void setCarreauSelectionne(Carreau carreauSelectionne) {
        this.carreauSelectionne = carreauSelectionne;
    }

    /**
     * Cette méthode place les pièces sur le plateau pour démarrer une nouvelle partie <ul>
     *     <li>Supprimer les pièces des cases d'une partie précédente</li>
     *     <li>Déseléctionner toutes les cases</li>
     *     <li>Placement des pièces pour la nouvelle partie</li>
     * </ul>
     */
    public void initialiserPlateau() {
        // clean avant de regénérer
        for(int i = 0; i < piecesNoires.size(); i++) {
            piecesNoires.remove(i);
        }
        for(int i = 0; i < piecesBlanches.size(); i++) {
            piecesBlanches.remove(i);
        }
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                carreaux[i][j].setContenu(null);
                carreaux[i][j].setSelectionnee(false);
                carreaux[i][j].setDestination(false);
            }
        }
        carreauSelectionne = null;

        // création et placement des pièces
        creationAutresPieces(piecesNoires, CouleurPiece.NOIR, 0);
        creationPions(piecesNoires, CouleurPiece.NOIR, 1);
        creationPions(piecesBlanches, CouleurPiece.BLANC, 6);
        creationAutresPieces(piecesBlanches, CouleurPiece.BLANC, 7);

    }

    /**
     * Cette méthode crée les pièces de type Pion,
     * les place sur le plateau,
     * les ajoute à la liste des pièces du jeu
     * @param pieces la liste des pièces à alimenter
     * @param couleur la couleur de la pièce à créer
     * @param ligne la ligne sur laquelle sera placée la pièce
     */
    private void creationPions(ArrayList<Piece> pieces, CouleurPiece couleur, int ligne) {
        Piece piece;

        for (int i = 0; i < 8 ; i++) {
            piece = PieceFactory.getPiece(TypePiece.PION, couleur);
            carreaux[i][ligne].setContenu(piece);
            pieces.add(piece);
        }
    }

    /**
     * Cette méthode crée les pièces de type Roi, Reine, Fou, Cavalier et Tour,
     * les place sur le plateau (dans la bonne case de départ),
     * les ajoute à la liste des pièces du jeu
     * @param pieces la liste des pièces à alimenter
     * @param couleur la couleur de la pièce à créer
     * @param ligne la ligne sur laquelle sera placée la pièce
     */
    private void creationAutresPieces(ArrayList<Piece> pieces, CouleurPiece couleur, int ligne) {
        Piece piece = null;

        for (int i = 0; i < 8; i++) {
            switch(i) {
                case 0:
                case 7:
                    piece = PieceFactory.getPiece(TypePiece.TOUR, couleur);
                    break;
                case 1:
                case 6:
                    piece = PieceFactory.getPiece(TypePiece.CAVALIER, couleur);
                    break;
                case 2:
                case 5:
                    piece = PieceFactory.getPiece(TypePiece.FOU, couleur);
                    break;
                case 3:
                    piece = PieceFactory.getPiece(TypePiece.REINE, couleur);
                    break;
                case 4:
                    piece = PieceFactory.getPiece(TypePiece.ROI, couleur);
                    break;
            }

            if(piece != null) {
                carreaux[i][ligne].setContenu(piece);
                pieces.add(piece);
            }
        }
    }

    public ArrayList<Deplacement> deplacementsPossibles() {
        return deplacementsPossibles(carreauSelectionne);
    }

    public ArrayList<Deplacement> deplacementsPossibles(Carreau carreau) {
        Partie partie = Partie.getInstance();
        ArrayList<Deplacement> deplacements = new ArrayList<>();

        if(carreau.getContenu() != null) {
            deplacements = carreau.getContenu().deplacementsPossibles(carreau);
            ArrayList<Deplacement> deplacementsToRemove = new ArrayList<>();

            deplacements.forEach(deplacement -> {
                partie.nouveauDeplacementSimul(deplacement);
                if ((deplacement.getPiece().getCouleur() == CouleurPiece.BLANC && partie.isRoiBlancMenace()) ||
                        (deplacement.getPiece().getCouleur() == CouleurPiece.NOIR && partie.isRoiNoirMenace())
                ) {
                    deplacementsToRemove.add(deplacement);
                }
                partie.annulerDeplacementSimul();
            });

            deplacements.removeAll(deplacementsToRemove);
        }

        return deplacements;
    }

}
