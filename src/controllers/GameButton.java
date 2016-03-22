/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gamelogic.GameObject;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ville
 */
public class GameButton extends Button {
    GameObject object;
    String type;
    ImageView imageView;
    
    public GameButton(GameObject object, String type) {
        this.object = object;
        this.type = type;
        if(type.equals("Normal"))
            this.setText(""+object.getValue());
        else if(type.equals("Cute")) {
            Image image = new Image(getClass().getResourceAsStream("/misc/cute/img"+object.getValue()+".jpg"));
            this.imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(this.widthProperty()); 
            imageView.fitHeightProperty().bind(this.heightProperty());
            this.setGraphic(imageView);
        }
        else {
            Image image = new Image(getClass().getResourceAsStream("/misc/manly/manly"+object.getValue()+".jpg"));
            this.imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(this.widthProperty()); 
            imageView.fitHeightProperty().bind(this.heightProperty());
            this.setGraphic(imageView); 
        }
    }
    
    public GameObject getObject() {
        return object;
    }
    
    public void setObject(GameObject object) {
        this.object = object;
    }
    
    public void setSize(int size) {
        this.setPrefWidth(400.0/size);
        this.setWidth(400.0/size);
        this.setPrefHeight(400.0/size);
        this.setHeight(400.0/size);
    }
    
}
