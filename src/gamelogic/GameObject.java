package gamelogic;

import java.io.Serializable;

/**
 *
 * @author Ville
 */
public class GameObject implements Serializable {
    private int value;
    private int x;
    private int y;
    
    public GameObject(int x, int y) {
        this.value = 0;
        this.x = x;
        this.y = y;
    }
    
    public void setValue(int newValue) {
        this.value = newValue;
    }
    
    public void collision(int addedValue) {
        this.value+=addedValue;
    }
    
    public int getPosX() {
        return x;
    }
    
    public int getPosY() {
        return y;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public String toString() {
        if(getValue() != 0)
            return getValue() + "(" + getPosX() + "," + getPosY() +")";
        else return "" + 0;
    }
}

