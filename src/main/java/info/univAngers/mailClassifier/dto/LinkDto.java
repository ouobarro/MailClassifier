/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
public class LinkDto {
    
    @Getter @Setter
    private Integer id;
    
    @Getter @Setter
    private String url;
    
    @Getter @Setter
    private Integer mailId;
}
