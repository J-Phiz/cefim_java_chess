package fr.leonie.jp.chess.controller;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;
import fr.leonie.jp.chess.enumeration.CouleurPiece;
import fr.leonie.jp.chess.model.*;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChessController implements Initializable {
    @FXML
    private Pane paneCarreau00, paneCarreau10, paneCarreau20, paneCarreau30, paneCarreau40, paneCarreau50, paneCarreau60, paneCarreau70;

    @FXML
    private Pane paneCarreau01, paneCarreau11, paneCarreau21, paneCarreau31, paneCarreau41, paneCarreau51, paneCarreau61, paneCarreau71;

    @FXML
    private Pane paneCarreau02, paneCarreau12, paneCarreau22, paneCarreau32, paneCarreau42, paneCarreau52, paneCarreau62, paneCarreau72;

    @FXML
    private Pane paneCarreau03, paneCarreau13, paneCarreau23, paneCarreau33, paneCarreau43, paneCarreau53, paneCarreau63, paneCarreau73;

    @FXML
    private Pane paneCarreau04, paneCarreau14, paneCarreau24, paneCarreau34, paneCarreau44, paneCarreau54, paneCarreau64, paneCarreau74;

    @FXML
    private Pane paneCarreau05, paneCarreau15, paneCarreau25, paneCarreau35, paneCarreau45, paneCarreau55, paneCarreau65, paneCarreau75;

    @FXML
    private Pane paneCarreau06, paneCarreau16, paneCarreau26, paneCarreau36, paneCarreau46, paneCarreau56, paneCarreau66, paneCarreau76;

    @FXML
    private Pane paneCarreau07, paneCarreau17, paneCarreau27, paneCarreau37, paneCarreau47, paneCarreau57, paneCarreau67, paneCarreau77;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonNew;

    @FXML
    private Button buttonQuit;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelCheckWhite;

    @FXML
    private Label labelCheckBlack;

    @FXML
    private Pane panePopup;

    @FXML
    private Label labelWinner;

    @FXML
    private Button buttonNewPopup;

    @FXML
    private Button buttonQuitPopup;

    private final Partie partie = Partie.getInstance();
    private final Plateau plateau = Plateau.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clickablePanes();
        cancelButtonInit();
        newButtonInit();
        partie.nouvellePartie();
        updateUI();
    }

    private void updateUI() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                try {
                    Field field = ChessController.class.getDeclaredField("paneCarreau" + i + j);
                    Carreau carreau = plateau.getCarreaux()[i][j];
                    Pane pane = (Pane) field.get(this);

                    // Gestion de la couleur des carreaux
                    if (carreau.isSelectionnee()) {
                        pane.setStyle("-fx-background-color:#A36F85; -fx-border-color:#A36F85;");
                    } else if (carreau.isDestination()) {
                        if(carreau.getContenu() == null) {
                            pane.setStyle("-fx-background-color:#F0EDD3; -fx-border-color:#779CA3;");
                        } else {
                            pane.setStyle("-fx-background-color:#779CA3; -fx-border-color:#779CA3;");
                        }
                    } else {
                        pane.setStyle("-fx-background-color:" + carreau.getCouleur().getColorValue() + "; -fx-border-color:" + carreau.getCouleur().getColorValue() + ";");
                    }

                    // Gestion de l'image dans les carreaux
                    if (carreau.getContenu() != null) {
                        ImageView imageView = new ImageView(new Image(
                                "file:" + carreau.getContenu().getImage(),
                                100,
                                100,
                                false,
                                true
                        ));
                        pane.getChildren().setAll(imageView);

                        if (carreau.isSelectionnee()) {
                            // Incliner la pi??ce s??lectionn??e
                            RotateTransition rotation = new RotateTransition(
                                    Duration.seconds(1),
                                    imageView
                            );
                            rotation.setFromAngle(0);
                            rotation.setToAngle(-35);
                            rotation.setCycleCount(10000);
                            rotation.setAutoReverse(true);
                            rotation.play();
                        } else if (carreau.getContenu().getNom().equals("roi")) {
                            // Faire trembler le roi en ??chec
                            if((carreau.getContenu().getCouleur() == CouleurPiece.BLANC && partie.isRoiBlancMenace()) ||
                            carreau.getContenu().getCouleur() == CouleurPiece.NOIR && partie.isRoiNoirMenace()) {
                                RotateTransition rotation = new RotateTransition(
                                        Duration.millis(10),
                                        imageView
                                );
                                rotation.setFromAngle(-10);
                                rotation.setToAngle(10);
                                rotation.setCycleCount(10000);
                                rotation.setAutoReverse(true);
                                rotation.play();
                            }
                        }
                    } else {
                        pane.getChildren().removeAll();
                        pane.getChildren().clear();
                    }

                } catch(Exception exception) {
                    System.out.println(
                            "Erreur dans la r??cup??ration du pane[" + i + "][" + j + "] : " + exception.getMessage()
                    );
                }
            }
        }

        // affichage popup
        if(partie.isRoiBlancMat() && partie.getNbTours() % 2 == 0) {
            labelWinner.setText("Noir gagnant");
            panePopup.setStyle("visibility: true; -fx-background-color: WHITE");
        } else if (partie.isRoiNoirMat() && partie.getNbTours() % 2 == 1) {
            labelWinner.setText("Blanc gagnant");
            panePopup.setStyle("visibility: true; -fx-background-color: WHITE");
        } else {
            labelWinner.setText("");
            panePopup.setStyle("visibility: false; -fx-background-color: WHITE");
        }

        // activation/desactivation bouton
        buttonCancel.setDisable(partie.getNbTours() == 0);

        // affichage informations jeu
        labelDescription.setText("Tour n??" + (partie.getNbTours()+1) + " - " + (partie.getNbTours() % 2 == 0 ? "blancs" : "noirs"));

        // affichage information ??chec au roi
        labelCheckWhite.setText(partie.isRoiBlancMenace() ? "Echec au roi Blanc !" : "");
        labelCheckBlack.setText(partie.isRoiNoirMenace() ? "Echec au roi Noir !" : "");

        // quitter en cliquant sur le bouton Quitter
        buttonQuit.setOnMouseClicked(mouseEvent -> System.exit(0));
        buttonQuitPopup.setOnMouseClicked(mouseEvent -> System.exit(0));
    }

    private void clickablePanes() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                try {
                    Field field = ChessController.class.getDeclaredField("paneCarreau" + i + j);
                    Pane pane = (Pane) field.get(this);
                    Carreau carreau = plateau.getCarreaux()[i][j];

                    pane.setOnMouseClicked(mouseEvent -> handlePaneSelection(carreau));
                } catch(Exception exception) {
                    System.out.println(
                            "Erreur dans la r??cup??ration du pane[" + i + "][" + j + "] : " + exception.getMessage()
                    );
                }
            }
        }
    }

    private void handlePaneSelection(Carreau carreau) {
        CouleurPiece tourCouleur = partie.getNbTours() % 2 == 0 ? CouleurPiece.BLANC : CouleurPiece.NOIR;
        Carreau ancienCarreau = plateau.getCarreauSelectionne();

        if(carreau.getContenu() != null && carreau.getContenu().getCouleur() == tourCouleur) {
            carreau.setSelectionnee(!carreau.isSelectionnee());
            hideAllowMoves();
            if(carreau.isSelectionnee()) {
                if(ancienCarreau != null) {
                    ancienCarreau.setSelectionnee(false);
                }
                plateau.setCarreauSelectionne(carreau);
                showAllowMoves(plateau.deplacementsPossibles());
            } else {
                plateau.setCarreauSelectionne(null);
            }
        } else if(ancienCarreau != null) {
            if(carreau.isDestination()) {

                Deplacement deplacement = new Deplacement(
                        ancienCarreau.getContenu(),
                        ancienCarreau,
                        carreau,
                        carreau.getContenu(),
                        checkPasseEnAvant(ancienCarreau, carreau)
                );
                deplacement.setRoque(carreau.isDestinationRoque());
                partie.nouveauDeplacement(deplacement);
                ancienCarreau.setSelectionnee(false);
                plateau.setCarreauSelectionne(null);
                hideAllowMoves();
            }
        }

        updateUI();
    }

    private Carreau checkPasseEnAvant(Carreau debutCarreau, Carreau finCarreau) {
        if(debutCarreau.getColonne() != finCarreau.getColonne() && debutCarreau.getContenu().getNom().equals("pion") && finCarreau.getContenu() == null) {
            System.out.println("Je suis la debut=" + debutCarreau + " fin=" + finCarreau);
            if(debutCarreau.getContenu().getCouleur() == CouleurPiece.BLANC) {
                return plateau.getCarreaux()[finCarreau.getColonne()][finCarreau.getLigne() + 1];
            } else {
                return plateau.getCarreaux()[finCarreau.getColonne()][finCarreau.getLigne() - 1];
            }
        } else {
            return finCarreau;
        }
    }

    private void hideAllowMoves() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                plateau.getCarreaux()[i][j].setDestination(false);
            }
        }
    }

    private void showAllowMoves(ArrayList<Deplacement> deplacements) {
        if (deplacements != null) {
            deplacements.forEach(deplacement -> {
                deplacement.getCarreauFin().setDestination(true);
                deplacement.getCarreauFin().setDestinationRoque(deplacement.isRoque());
            });
        }
    }

    private void cancelButtonInit() {
        buttonCancel.setOnMouseClicked(mouseEvent -> {
            partie.annulerDeplacement();
            updateUI();
        });
    }

    private void newButtonInit() {
        buttonNew.setOnMouseClicked(mouseEvent -> {
            partie.nouvellePartie();
            updateUI();
        });
        buttonNewPopup.setOnMouseClicked(mouseEvent -> {
            partie.nouvellePartie();
            updateUI();
        });
    }

}
