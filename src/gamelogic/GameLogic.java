/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Ville
 */
public class GameLogic implements Serializable {
    private Random random;
    private int points = 0;
    private ArrayList<GameObject> objects;
    private int size;
    private int winningPoints;
    
    public GameLogic(int size, int winningPoints) {
        this.random = new Random();
        objects = new ArrayList<>();
        this.size = size;
        this.winningPoints = winningPoints;
    } 

    
    public void startGame() {
        int randX = random.nextInt(size);
        int randY = random.nextInt(size);
        GameObject cube = new GameObject(randX, randY);
        cube.setValue(nextRandom());
        addObject(cube);
        
    }
    
    public int nextRandom() {
        int randInt = random.nextInt(100);
        if(randInt >= 80)
            return 4;
        else return 2;
    }
    
    public boolean moveUp() {
        boolean movementTookPlace = false;
        int y = 0;
        while(y >= 0 && y < size) {
            boolean somethingMoved = false;
            GameObject[] upperLayer = getObjectsByY(y);
            GameObject[] belowUpperLayer = getObjectsByY(y+1);

            for(int i = 0; i < size; i++) {
                GameObject object = upperLayer[i];
                GameObject object2 = belowUpperLayer[i];
                if(object.getValue() != 0 && object2.getValue() != 0) {
                    if(object.getValue() == object2.getValue()) {
                        boolean combined = combineObjects(object,object2);
                        if(combined) {
                            somethingMoved = true;
                            movementTookPlace = true;
                        }
                    }
                } else if(object.getValue() == 0 && object2.getValue() != 0) {
                    object2.setY(y);
                    somethingMoved = true;
                    movementTookPlace = true;
                }
            }
            if(!somethingMoved) {
                y++;
            }
            else {
                if(y > 0)
                    y--;
            }
        }
        return movementTookPlace;
    }
    
    public boolean canMoveUp() {
        int y = 0;
        while(y >= 0 && y < size) {
            GameObject[] upperLayer = getObjectsByY(y);
            GameObject[] belowUpperLayer = getObjectsByY(y+1);

            for(int i = 0; i < size; i++) {
                GameObject object = upperLayer[i];
                GameObject object2 = belowUpperLayer[i];
                if(object.getValue() != 0 && object2.getValue() != 0) {
                    if(object.getValue() == object2.getValue()) {
                        boolean combined = canCombine(object,object2);
                        if(combined) {
                            return true;
                        }
                    }
                } else if(object.getValue() == 0 && object2.getValue() != 0) {
                    object2.setY(y);
                    return true;
                }
            }
            if(y > 0)
                y--;
            else y++;
            
        }
        return false;
    }

    public boolean moveDown() {
        boolean movementTookPlace = false;
        int y = size-1;
        while(y >= -1 && y < size) {
            boolean somethingMoved = false;
            GameObject[] lowerLayer = getObjectsByY(y);
            GameObject[] aboveLowerLayer = getObjectsByY(y-1);
            
            for(int i = 0; i < size; i++) {
                GameObject object = lowerLayer[i];
                GameObject object2 = aboveLowerLayer[i];
                if(object.getValue() != 0 && object2.getValue() != 0) {
                    if(object.getValue() == object2.getValue()) {
                        boolean combined = combineObjects(object,object2);
                        if(combined) {
                            somethingMoved = true;
                            movementTookPlace = true;
                        }
                    }
                } else if(object.getValue() == 0 && object2.getValue() != 0) {
                    object2.setY(y);
                    somethingMoved = true;
                    movementTookPlace = true;
                }
            }
            if(!somethingMoved) {
                y--;
            }
            else {
                if(y < size-1)
                    y++;
            }
            
        }
       return movementTookPlace;
    }
    
    public boolean canMoveDown() {
        int y = size-1;
        while(y >= -1 && y < size) {
            GameObject[] lowerLayer = getObjectsByY(y);
            GameObject[] aboveLowerLayer = getObjectsByY(y-1);
            
            for(int i = 0; i < size; i++) {
                GameObject object = lowerLayer[i];
                GameObject object2 = aboveLowerLayer[i];
                if(object.getValue() != 0 && object2.getValue() != 0) {
                    if(object.getValue() == object2.getValue()) {
                        boolean combined = canCombine(object,object2);
                        if(combined) {
                            return true;
                        }
                    }
                } else if(object.getValue() == 0 && object2.getValue() != 0) {
                    object2.setY(y);
                    return true;
                }
            }
            if(y < size-1)
                y++;
            else y--;        
        }
       return false;
    }

    public boolean moveLeft() {
        boolean movementTookPlace = false;
        int x = 0;
        while(x >= 0 && x < size) {
            boolean somethingMoved = false;
            GameObject[] leftLayer = getObjectsByX(x);
            GameObject[] afterLeftLayer = getObjectsByX(x+1);
            
            for(int i = 0; i < size; i++) {
                GameObject button = leftLayer[i];
                GameObject button2 = afterLeftLayer[i];
                if(button.getValue() != 0 && button2.getValue() != 0) {
                    if(button.getValue() == button2.getValue()) {
                        boolean combined = combineObjects(button,button2);
                        if(combined) {
                            somethingMoved = true;
                            movementTookPlace = true;
                        }
                    }
                } else if(button.getValue() == 0 && button2.getValue() != 0) {
                    button2.setX(x);
                    somethingMoved = true;
                    movementTookPlace = true;
                }
            }
            if(!somethingMoved) {
                x++;
            }
            else {
                if(x > 0)
                    x--;
            }
        }
        return movementTookPlace;
    }
    
    public boolean canMoveLeft() {
        int x = 0;
        while(x >= 0 && x < size) {
            GameObject[] leftLayer = getObjectsByX(x);
            GameObject[] afterLeftLayer = getObjectsByX(x+1);
            
            for(int i = 0; i < size; i++) {
                GameObject button = leftLayer[i];
                GameObject button2 = afterLeftLayer[i];
                if(button.getValue() != 0 && button2.getValue() != 0) {
                    if(button.getValue() == button2.getValue()) {
                        boolean combined = canCombine(button,button2);
                        if(combined) {
                            return true;
                        }
                    }
                } else if(button.getValue() == 0 && button2.getValue() != 0) {
                    button2.setX(x);
                    return true;
                }
            }
            if(x > 0)
               x--;
            else x++;
        }
        return false;
    }
    
    public boolean moveRight() {
        boolean movementTookPlace = false;
        int x = size-1;
        while(x >= 0 && x < size) {
            boolean somethingMoved = false;
            GameObject[] rightLayer = getObjectsByX(x);
            GameObject[] beforeLayer = getObjectsByX(x-1);
            
            for(int i = 0; i < size; i++) {
                GameObject object = rightLayer[i];
                GameObject object2 = beforeLayer[i];
                if(object.getValue() != 0 && object2.getValue() != 0) {
                    if(object.getValue() == object2.getValue()) {
                        boolean combined = combineObjects(object,object2);
                        if(combined) {
                            somethingMoved = true;
                            movementTookPlace = true;
                        }
                    }
                } else if(object.getValue() == 0 && object2.getValue() != 0) {
                    object2.setX(x);
                    somethingMoved = true;
                    movementTookPlace = true;
                }
            }
            if(!somethingMoved) {
                x--;
            }
            else {
                if(x < size-1)
                    x++;
            }
        }
        return movementTookPlace;  
    }
    
    public boolean canMoveRight() {
        int x = size-1;
        while(x >= 0 && x < size) {
            boolean canMove = false;
            GameObject[] rightLayer = getObjectsByX(x);
            GameObject[] beforeLayer = getObjectsByX(x-1);
            
            for(int i = 0; i < size; i++) {
                GameObject object = rightLayer[i];
                GameObject object2 = beforeLayer[i];
                if(object.getValue() != 0 && object2.getValue() != 0) {
                    if(object.getValue() == object2.getValue()) {
                        boolean combined = canCombine(object,object2);
                        if(combined) {
                            return true;
                        }
                    }
                } else if(object.getValue() == 0 && object2.getValue() != 0) {
                    object2.setX(x);
                    return true;
                }
            }
            if(x < size-1)
                x++;
            else x--;
        }
        return false;
    }

    public void addRandomObject() { 
        while(true) {
            boolean posTaken = false;
            int randX = random.nextInt(size);
            int randY = random.nextInt(size);
            GameObject newObject = new GameObject(randX, randY);
            newObject.setValue(nextRandom());
            for(GameObject object: objects) {
                if(object.getPosX() == newObject.getPosX() && object.getPosY() == newObject.getPosY()) {
                    posTaken = true;
                    break;
                }
                
            }
            if(!posTaken) {
                addObject(newObject);    
                break;
            }
        }
    }
    

    public GameObject[] getObjectsByX(int x) {
        GameObject[] xObjects = new GameObject[size];
        for(GameObject object: objects) {
            if(object.getPosX() == x) {
                xObjects[object.getPosY()] = object;
            }
        }
        
        for(int i = 0; i < size; i++) {
            if(xObjects[i] == null) {
                xObjects[i] = new GameObject(x, i);
            }
        }
        return xObjects;
    }
    
    public GameObject[] getObjectsByY(int y) {
        GameObject[] yObjects = new GameObject[size];
        for(GameObject object: objects) {
            if(object.getPosY() == y) {
                yObjects[object.getPosX()] = object;
            }
        }
        
        for(int i = 0; i < size; i++) {
            if(yObjects[i] == null) {
                yObjects[i] = new GameObject(i, y);
            }
        }
        return yObjects;
    }

    public boolean combineObjects(GameObject object, GameObject object2) {
        if(object.getValue() == object2.getValue()) {
            removeObject(object2);
            object.setValue(object.getValue()*2);
            points = points + object.getValue();
            return true;
        }
        return false;
    }
    
    public boolean canCombine(GameObject object, GameObject object2) {
        if(object.getValue() == object2.getValue()) {
            return true;
        }
        return false;
    }
    
    public void removeObject(GameObject object) {
        objects.remove(object);
    }
    
    public void addObject(GameObject object) {
        objects.add(object);
    }
    
    public ArrayList<GameObject> getObjectsInGame() {
        return objects;
    }
    
    public int getPoints() {
        return points;
    }
    
    public boolean checkIfWon() {
        for(GameObject object: objects) {
            if(object.getValue() == winningPoints) {
                return true;
            }
        }
        return false;
    }
    
    /*
    public boolean checkIfLost() {
        if(!canMoveUp() && !canMoveDown() && !canMoveLeft() && !canMoveRight()) {
            return true;
        }
        return false;
    }*/
}
