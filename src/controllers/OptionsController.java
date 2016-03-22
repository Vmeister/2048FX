/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import misc.MusicPlayer;
/**
 * FXML Controller class
 *
 * @author Ville
 */
public class OptionsController implements Initializable {

    @FXML
    private Slider volumeSlider;
    @FXML
    public ChoiceBox<Integer> winningNumberSelector;
    @FXML
    private ChoiceBox<String> musicSelector;
    @FXML
    public ChoiceBox<Integer> boardSizeSelector;
    @FXML
    public ChoiceBox<String> gameModeSelector;
    @FXML
    public Pane Options;
    
    MusicPlayer player;

    public Integer boardSize;
    public IntegerProperty boardSizeProperty;
    public Integer winningNumber;
    public IntegerProperty winningNumberProperty;
    public String gameType;
    public StringProperty gameTypeProperty;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boardSize = 4;
        winningNumber = 2048;
        boardSizeProperty = new SimpleIntegerProperty(boardSize);
        winningNumberProperty = new SimpleIntegerProperty(winningNumber);
        gameTypeProperty = new SimpleStringProperty();
        this.player = new MusicPlayer();
        findMusicFiles();
        createChoiceBoxListeners();
        createVolumeListener();
    }    

    private void findMusicFiles() {
        try {
            musicSelector.getItems().addAll(player.musicFiles());
        } catch (Exception ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createChoiceBoxListeners() {         
        musicSelector.getSelectionModel().selectedIndexProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue observable, Number oldValue, Number newValue) {
                    player.stopPlaying();
                    try {
                        player.playMusic(musicSelector.getItems().get(newValue.intValue()));
                        player.setVolume((int)volumeSlider.getValue());
                    } catch (Exception ex) {}
                }              
            });
        
        winningNumberSelector.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue) {
                winningNumber = winningNumberSelector.getItems().get((int)newValue);
                winningNumberProperty.setValue(winningNumber);
            }
        });
        boardSizeSelector.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue) {
                boardSize = boardSizeSelector.getItems().get((int)newValue);
                boardSizeProperty.setValue(boardSize);
            }
        });
        gameModeSelector.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue) {
                gameTypeProperty.setValue(gameModeSelector.getItems().get((int)newValue));
            }
        });
        
        Integer[] gameSize = {4, 5, 6};
        boardSizeSelector.getItems().addAll(gameSize);
        boardSizeSelector.getSelectionModel().select(0);
        Integer[] winningNumbers = {1024, 2048, 4096, 8192};
        winningNumberSelector.getItems().addAll(winningNumbers);
        winningNumberSelector.getSelectionModel().select(1);      
        String[] gameMode = {"Normal", "Cute", "Manly"};
        gameModeSelector.getItems().addAll(gameMode);
        gameModeSelector.getSelectionModel().select(1);
    }
    void createVolumeListener() {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number oldVal, Number newVal) {
                    player.setVolume(newVal.intValue());
            }
        });
    }
    
    public Integer getBoardSize() {
        return boardSize;
    }
    
    public Integer getWinningNumber() {
        return winningNumber;
    }
}
    

