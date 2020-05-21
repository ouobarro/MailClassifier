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
public class DataCountDto {
    
    @Getter @Setter
    private Integer attachCount;
    
    @Getter @Setter
    private Integer bclCount;
    
    @Getter @Setter
    private Integer personCount;
    
    @Getter @Setter
    private Integer personMoralCount;
    
    @Getter @Setter
    private Integer emailCount;
    
    @Getter @Setter
    private Integer mailCount;
    
    @Getter @Setter
    private Integer linkCount;

    public DataCountDto() {
        this.attachCount = 0;
        this.bclCount = 0;
        this.personCount = 0;
        this.emailCount = 0;
        this.mailCount = 0;
        this.linkCount = 0;
        this.personCount =0;
        this.personMoralCount =0;

    }
    
    
    
}
