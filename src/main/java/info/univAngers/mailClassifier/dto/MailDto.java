/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
public class MailDto {
    
    @Getter @Setter
    private Integer id;
    
    @Getter @Setter
    private String subject;
    
    @Getter @Setter
    private String content;
    
    @Getter @Setter
    private Date sendDate;
    
    @Getter @Setter
    private BroadcastListDto bclDto;
    
    @Getter @Setter
    private List<AttachmentDto> attachListDto;
    
    @Getter @Setter
    private List<LinkDto> linkListDto;
    
    @Getter @Setter
    private PersonDto sender;
    
    @Getter @Setter
    private List<PersonDto> receiverList;
}
