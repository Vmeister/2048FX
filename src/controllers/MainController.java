/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Ville
 */
public class MainController implements Initializable {

    @FXML
    private Tab gameTab;
    @FXML
    private Tab optionsTab;
    @FXML
    private TabPane Main;
    @FXML
    private MainMenuController mainMenuController;
    @FXML
    private OptionsController optionsController;
    @FXML
    private GameController gameController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }
    
    public void setOptionsController(OptionsController optionsController) {
        this.optionsController = optionsController;
    }
    
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void printControllers() {
        System.out.println("MainMenuCtrler: " + mainMenuController);
        System.out.println("OptionsCtrler: " + optionsController);
        System.out.println("GameCtrler: " + gameController);
    }

    public void initializeViews() {
        Main.getTabs().get(0).setContent(mainMenuController.MainMenu);
        Main.getTabs().get(1).setContent(optionsController.Options);
    }
    
    public void bindData() {
        gameController.sizeProperty.bindBidirectional(optionsController.boardSizeProperty);
        gameController.winningNumberProperty.bindBidirectional(optionsController.winningNumberProperty);
        gameController.typeProperty.bindBidirectional(optionsController.gameTypeProperty);
    }
}
