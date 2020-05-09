/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.mailFileReader;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
public class Attach {
    
    @Getter @Setter
    private String fileName;
    
    @Getter @Setter
    private String attPath;
  
    @Getter @Setter 
    private String extension;
   
    @Override
    public String toString() {
        return "Attach{" + "fileName=" + fileName + ", attPath=" + attPath +", extension=" + extension + '}';
    }
    
    
    
}
