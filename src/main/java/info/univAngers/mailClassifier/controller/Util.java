/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barro
 */
public class Util {
    
    public static List<File> getDirectoryFiles(String dirPath){
        
        List<File> dirFiles = new ArrayList<>();
        File folder = new File(dirPath);
        File[] files = folder.listFiles();
        
        for (File file : files){
            dirFiles.add(file);
        }
        return dirFiles;
    }
    
}
