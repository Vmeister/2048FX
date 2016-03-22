/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ville
 */
public class MainGame extends Application {
    
    @FXML
    private MainMenuController mainMenuController;
    @FXML
    private GameController gameController;
    @FXML
    private OptionsController optionsController;
    @FXML
    private MainController mainController;
    
    private Parent root;
    
    public static void main(String[] args) {
        Application.launch(MainGame.class, (java.lang.String[])null);
    } 

    @Override
    public void start(Stage stage) throws Exception {
        initializeControllers();
        initializeViews();
        Scene scene = new Scene(root, 400, 470);
        stage.setScene(scene);
        scene.getStylesheets().add("/css/stylesheet.css");
        stage.setTitle("2048");
        stage.show();
       
    }
    
    public void initializeControllers() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/layouts/Main.fxml"));
        this.root = (Parent)loader.load();
        mainController = loader.getController();
        FXMLLoader loader2 = new FXMLLoader(MainMenuController.class.getResource("/layouts/MainMenu.fxml"));
        loader2.load();
        mainMenuController = loader2.getController();
        FXMLLoader loader3 = new FXMLLoader(OptionsController.class.getResource("/layouts/Options.fxml"));
        loader3.load();
        optionsController = loader3.getController();
        FXMLLoader loader4 = new FXMLLoader(GameController.class.getResource("/layouts/GameBoard.fxml"));
        loader4.load();
        this.gameController = loader4.getController();
        mainController.setMainMenuController(mainMenuController);
        mainController.setOptionsController(optionsController);
        mainController.setGameController(gameController);
        mainMenuController.setGameController(gameController);
        mainController.printControllers();
        mainController.bindData();
    }

    private void initializeViews() {
        mainController.initializeViews();
    }
}
