/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Ville
 */
public class MusicPlayer {
    
    InputStream inputStream;
    String pathToMusic;
    MediaPlayer mediaPlayer;
    
    public MusicPlayer() {
    }
    
    public void playMusic(String fileName) throws Exception {
        Media media = new Media(getClass().getResource("/misc/music/"+fileName).toString());
        this.mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        
    }
    
    public ArrayList<String> musicFiles() throws Exception {
        ArrayList<String> musicFiles = new ArrayList<>();
        CodeSource src = this.getClass().getProtectionDomain().getCodeSource();
        if (src != null) {
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            while(true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null)
                    break;
                String name = e.getName();
                if (name.endsWith(".mp3")) {
                    String fileName = name.replace("misc/music/", "");
                    musicFiles.add(fileName);
                }
            }
        }
        return musicFiles;
    }
    
    public void stopPlaying() {
        if(mediaPlayer != null)
            mediaPlayer.stop();
    }  
    
        
    public void setVolume(int volumeLevel) {
        if(mediaPlayer != null)
            mediaPlayer.setVolume(volumeLevel/100.0);
    }
}
