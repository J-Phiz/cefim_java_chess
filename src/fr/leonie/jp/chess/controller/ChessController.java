package fr.leonie.jp.chess.controller;

import fr.leonie.jp.chess.enumeration.CouleurCarreau;
import fr.leonie.jp.chess.enumeration.CouleurPiece;
import fr.leonie.jp.chess.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.lang.reflect.Field;
import java.net.URL;
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

    private final Partie partie = Partie.getInstance();
    private final Plateau plateau = Plateau.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clickablePanes();
        cancelButtonInit();
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

                    if (carreau.isSelectionnee()) {
                        pane.setStyle("-fx-background-color:RED;");
                    } else {
                        pane.setStyle("-fx-background-color:" + carreau.getCouleur().getColorValue() + ";");
                    }
                    if (carreau.getContenu() != null) {
                        pane.getChildren().setAll(new ImageView(new Image(
                                "file:" + carreau.getContenu().getImage(),
                                100,
                                100,
                                false,
                                true
                        )));
                    } else {
                        pane.getChildren().removeAll();
                    }
                } catch(Exception exception) {
                    System.out.println(
                            "Erreur dans la récupération du pane[" + i + "][" + j + "] : " + exception.getMessage()
                    );
                }
            }
        }

        // activation/desactivation bouton
        buttonCancel.setDisable(partie.getNbTours() == 0);
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
                            "Erreur dans la récupération du pane[" + i + "][" + j + "] : " + exception.getMessage()
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
            if(carreau.isSelectionnee()) {
                if(ancienCarreau != null) {
                    ancienCarreau.setSelectionnee(false);
                }
                plateau.setCarreauSelectionne(carreau);
            } else {
                plateau.setCarreauSelectionne(null);
            }
        } else if(ancienCarreau != null) {
            Deplacement deplacement = new Deplacement(ancienCarreau.getContenu(), ancienCarreau, carreau);
            partie.nouveauDeplacement(deplacement);

            ancienCarreau.setSelectionnee(false);
            plateau.setCarreauSelectionne(null);
        }

        updateUI();
    }

    private void cancelButtonInit() {
        buttonCancel.setOnMouseClicked(mouseEvent -> {
            partie.annulerDeplacement();
            updateUI();
        });
    }

}
