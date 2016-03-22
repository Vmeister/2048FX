/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gamelogic.GameLogic;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ville
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button startGameBtn;
    @FXML
    private Button scoresBtn;
    @FXML
    private Button exitGameBtn;
    @FXML
    public Pane MainMenu;
    @FXML
    private Label mainMenuLabel;
    
    private GameController gameController;
    
    private GameLogic gameLogic;
    
    public IntegerProperty gameSizeProperty;
    public IntegerProperty winningNumberProperty;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gameSizeProperty = new SimpleIntegerProperty();
        this.winningNumberProperty = new SimpleIntegerProperty();
    } 
    
    @FXML
    private void startGameAction(ActionEvent event) {
        TabPane tabPane = (TabPane)MainMenu.getParent().getParent();
        tabPane.getTabs().get(0).setContent(gameController.GameBoard);
    }


    @FXML
    private void showScoresAction(ActionEvent event) {
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
