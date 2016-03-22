/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Ville
 */
public class LoadGame {
    
    private ObjectInputStream input;
    private String fileName;
            
    public LoadGame(String fileName) {
        this.fileName = fileName;
    }
    
    public Object loadSaveFile() throws IOException, ClassNotFoundException {
        if(input == null)
            return null;
        else {
            Object loadedObject = input.readObject();
            return loadedObject;
        }       
    }
    
    public boolean openForReading() throws FileNotFoundException, IOException {
        if(fileName == null)
            return false;  
        File file = new File(fileName);
        if(file.exists() && file.canRead()) {
            input = new ObjectInputStream(new FileInputStream(fileName));
            return true;
        } else return false;
    }
    
    public void closeReading() throws IOException {
        input.close();
    }
    
    public Object loadGame() throws IOException, ClassNotFoundException {
        if(openForReading()) {
            Object loadedFile = loadSaveFile();
            closeReading();
            return loadedFile;
        }
        return false;
    }
    
}
