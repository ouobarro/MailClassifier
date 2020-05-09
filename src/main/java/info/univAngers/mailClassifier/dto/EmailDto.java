/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
public class EmailDto {
    
    @Getter @Setter
    private Integer id;
    
    @Getter @Setter
    private String eAddress;
    
    @Getter @Setter
    private String signature;
    
    @Getter @Setter
    private PersonDto personDto;
    
    @Getter @Setter
    private BroadcastListDto bclDto;
    
    @Getter @Setter
    @JsonIgnore
    private List<MailDto> mailDtoList;
}
