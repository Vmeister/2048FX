/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gamelogic.GameLogic;
import gamelogic.GameObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import misc.LoadGame;
import misc.SaveGame;

/**
 * FXML Controller class
 *
 * @author Ville
 */
public class GameController implements Initializable {

    @FXML
    private Button NewGameBtn;
    @FXML
    private Button SaveBtn;
    @FXML
    private Pane gameMap;
    @FXML
    private TextField scoreField;
    @FXML
    public VBox GameBoard;
    
    private GameLogic game;
    
    private Stage stage;
    private Scene scene;
    
    private ArrayList<GameButton> buttonsInGame;
    public IntegerProperty sizeProperty;
    public IntegerProperty winningNumberProperty;
    public StringProperty typeProperty;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        this.sizeProperty = new SimpleIntegerProperty();
        this.winningNumberProperty = new SimpleIntegerProperty();
        this.typeProperty = new SimpleStringProperty();
        this.buttonsInGame = new ArrayList<>();
        gameMap.getStylesheets().add("/css/stylesheet.css");
    }    

    @FXML
    private void newGameAction(ActionEvent event) {
        removeButtons();
        System.out.println("Starting game with size: " + sizeProperty.getValue() + " winning: " + winningNumberProperty.getValue());
        startGame(sizeProperty.getValue(), winningNumberProperty.getValue());
    }

    @FXML
    private void SaveAction(ActionEvent event) throws IOException {
        SaveGame saveWriter = new SaveGame("save.dat");
        System.out.println(saveWriter.saveGame(game));
    }
    
    @FXML
    private void LoadAction(ActionEvent event) throws IOException, ClassNotFoundException {
        LoadGame saveReader = new LoadGame("save.dat");
        this.game = (GameLogic)saveReader.loadGame();
        drawButtonsAndUpdatePoints();
        createControlListener();
        drawGameBoard(sizeProperty.getValue());
    }
    
    public ArrayList<GameButton> getButtons() {
        ArrayList<GameButton> buttons = new ArrayList<>();
        for(Node node: gameMap.getChildren()) {
            if(node.getClass().equals(GameButton.class)) {
                buttons.add((GameButton)node);
            }
        }
        return buttons;
    }

    void startGame(int size, int winningNumber) {
        game = new GameLogic(size, winningNumber);
        game.startGame();
        drawButtonsAndUpdatePoints();
        createControlListener();
        drawGameBoard(size);
    }

    void addButton(GameObject object) {
        GameButton button = new GameButton(object, typeProperty.getValue());
        changeButtonStyle(button);
        button.setSize(sizeProperty.getValue());
        button.setLayoutX((object.getPosX())*400/sizeProperty.getValue());
        button.setLayoutY((object.getPosY())*400/sizeProperty.getValue());
        gameMap.getChildren().add(button);
        buttonsInGame.add(button);
        drawButtonsAndUpdatePoints();
    }
    
    public void removeButtons() {
        ArrayList<Node> removableNodes = new ArrayList<>();
        for(Node node: gameMap.getChildren()) {
            if(node.getClass().equals(GameButton.class)) {
                removableNodes.add(node);
            }
        }
        gameMap.getChildren().removeAll(removableNodes);
    }
    
    public void removeButton(GameButton button) {
        Node removableNode = null;
        for(Node node: gameMap.getChildren()) {
            if(node.getClass().equals(GameButton.class)) {
                if(((GameButton)node).equals(button)) {
                    removableNode = node;
                    break;     
                }
            }
        }
        gameMap.getChildren().remove(removableNode);
    }
    
   public void printButtons() {
       ArrayList<GameButton> buttons = getButtons();
       System.out.println("Buttons currenty in game: ");
       for(GameButton button: buttons) {
           System.out.println(button.getObject().getValue() + "(" + 
                   button.getObject().getPosX() + "," + button.getObject().getPosY()+ ")");
       }
   }
   
   public void drawButtonsAndUpdatePoints() {
        ArrayList<GameObject> objects = game.getObjectsInGame();
        removeButtons();
        for(GameObject object : objects) {
           GameButton button = new GameButton(object, typeProperty.getValue());
           button.setSize(sizeProperty.getValue());
           int x = button.getObject().getPosX();
           int y = button.getObject().getPosY();
           button.setLayoutX(x*400/sizeProperty.getValue());
           button.setLayoutY(y*400/sizeProperty.getValue());
           changeButtonStyle(button);
           gameMap.getChildren().add(button);
       }
        scoreField.setText(""+game.getPoints());
   }
   
    private void createControlListener() {
        int size = sizeProperty.getValue();
        gameMap.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    moveUp(); break;
                    case S:  moveDown(); break;
                    case A:  moveLeft(); break;
                    case D: moveRight(); break;
                }
            }

            void moveUp() {
                boolean moved = game.moveUp();
                if(moved) {
                    game.addRandomObject();
                    drawButtonsAndUpdatePoints();
                }
                if(game.getObjectsInGame().size() == size*size) {
                    /*if(!game.checkIfLost())
                        gameOver();*/
                }
            }

            void moveDown() {
                boolean moved = game.moveDown();
                if(moved) {
                    game.addRandomObject();
                    drawButtonsAndUpdatePoints();
                }
                if(game.getObjectsInGame().size() == size*size) {
                    /*if(!game.checkIfLost())
                        gameOver();*/
                }
            }

            void moveLeft() {
                boolean moved = game.moveLeft();
                if(moved) {
                    game.addRandomObject();
                    drawButtonsAndUpdatePoints();
                }
                if(game.getObjectsInGame().size() == size*size) {
                    /*if(!game.checkIfLost())
                        gameOver();*/
                }
            }

            void moveRight() {
                boolean moved = game.moveRight();
                if(moved) {
                    game.addRandomObject();
                    drawButtonsAndUpdatePoints();
                }
                if(game.getObjectsInGame().size() == size*size) {
                    /*if(!game.checkIfLost())
                        gameOver();*/
                }
            }
        });
    }
   
    private void drawGameBoard(int size) {
        removeLines();
        double x = 400/size;
        double x2 = x;
        while(x <= 400.0) {
           Line line = new Line();
           line.setLayoutX(x);
           line.setLayoutY(0.0);
           line.setStartX(0.0);
           line.setStartY(0.0);
           line.setEndX(0.0);
           line.setEndY(400.0);
           line.setStrokeWidth(1);
           x = x + x2;
           gameMap.getChildren().add(line);
        }
        double y = 400/size;
        double y2 = y;
        while(y <= 400.0) {
           Line line = new Line();
           line.setLayoutY(y);
           line.setLayoutX(0.0);
           line.setStartX(0.0);
           line.setStartY(0.0);
           line.setEndX(400.0);
           line.setEndY(0.0);
           line.setStrokeWidth(1);
           y = y + y2;
           gameMap.getChildren().add(line); 
        }
   }

    private void changeButtonStyle(GameButton button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("button"+button.getObject().getValue());
        button.setStyle(".button"+button.getObject().getValue());
    }
    
    private void removeLines() {
        ArrayList<Node> removableNodes = new ArrayList<>();
        for(Node node : gameMap.getChildren()) {
            if(node.getClass().equals(Line.class))
                removableNodes.add(node);
        }
        for(Node node : removableNodes) {
            gameMap.getChildren().remove(node);
        }
    }
    
    private void gameOver() {
        /*
        Pane gameOverPane = new Pane();
        Stage gameOverStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/gameoverfx.fxml"));
        try {
            gameOverPane = (Pane)loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene stageScene = new Scene(gameOverPane, 150, 150);
        gameOverStage.setScene(stageScene);
        gameOverStage.show();*/
    }
}
