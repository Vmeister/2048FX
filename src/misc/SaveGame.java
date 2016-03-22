package misc;

import java.io.*;
/**
 *
 * @author Ville
 */
public class SaveGame {
    private ObjectOutputStream output;
    private String fileName;
    
    public SaveGame(String fileName) {
        this.fileName = fileName;
    }

    public boolean writeSaveFile(Object gameObject) throws IOException {
        if (output != null) {
            output.writeObject(gameObject);
            return true;
        }
        return false;
    }
    
    public boolean openSaveFile() throws FileNotFoundException, IOException {
 	if (fileName == null)
            return false;
        
    	else {
      		File file = new File(fileName);
      		if (!file.exists() || file.canWrite() ) {

      		output = new ObjectOutputStream(new FileOutputStream(fileName));
      		return true;
    		}

    	else {
      		return false;
            }
  	}
    }
    
    public void closeWriting() throws IOException {
        output.close();
    }
    
    public boolean saveGame(Object gameObject) throws IOException {
        if(openSaveFile()) {
            writeSaveFile(gameObject);
            closeWriting();
            return true;
        }
        return false;
    }
    
}
