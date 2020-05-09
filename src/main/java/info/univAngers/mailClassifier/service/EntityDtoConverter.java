/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.AttachTypeDto;
import info.univAngers.mailClassifier.dto.AttachmentDto;
import info.univAngers.mailClassifier.dto.BroadcastListDto;
import info.univAngers.mailClassifier.dto.EmailDto;
import info.univAngers.mailClassifier.dto.LinkDto;
import info.univAngers.mailClassifier.dto.MailDto;
import info.univAngers.mailClassifier.dto.PersonDto;
import info.univAngers.mailClassifier.model.AttachType;
import info.univAngers.mailClassifier.model.Attachment;
import info.univAngers.mailClassifier.model.BroadcastList;
import info.univAngers.mailClassifier.model.Email;
import info.univAngers.mailClassifier.model.Link;
import info.univAngers.mailClassifier.model.Mail;
import info.univAngers.mailClassifier.model.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barro
 */
public class EntityDtoConverter {
    
    public static MailDto convertToDto(Mail mail){
        MailDto mailDto = new MailDto();
        mailDto.setId(mail.getIdMail());
        mailDto.setSubject(mail.getSubject());
        mailDto.setContent(mail.getContent());
        mailDto.setSendDate(mail.getSendDate());
        
        List<AttachmentDto> attachDtoList = new ArrayList<>();
        if(mail.getAttachmentList()!= null){
            for(Attachment attach : mail.getAttachmentList()){
                attachDtoList.add(convertToDto(attach));
            }
        }
        mailDto.setAttachListDto(attachDtoList);
        
        List<LinkDto> linkDtoList = new ArrayList<>();
        if(mail.getAttachmentList()!= null){
            for(Link link : mail.getLinkList()){
                linkDtoList.add(convertToDto(link));
            }
        }
        mailDto.setLinkListDto(linkDtoList);
        
        if(mail.getEmail() != null){
            mailDto.setEmailDto(convertToDto(mail.getEmail()));
        }
        
        
        
        List<EmailDto> receiverDtoList = new ArrayList<>();
        if(mail.getReceiverList()!= null){
            for(Email email : mail.getReceiverList()){
                receiverDtoList.add(convertToDto(email));
            }
        }
        mailDto.setReceiverList(receiverDtoList);
        
        List<EmailDto> receiverCcDtoList = new ArrayList<>();
        if(mail.getReceiverCcList()!= null){
            for(Email email : mail.getReceiverCcList()){
                receiverCcDtoList.add(convertToDto(email));
            }
        }
        mailDto.setReceiverCcList(receiverCcDtoList);
        
        return mailDto;
    }
    
    public static AttachTypeDto convertToDto(AttachType attachType){
        AttachTypeDto attachTypeDto = new AttachTypeDto();
        attachTypeDto.setId(attachType.getIdAttachType());
        attachTypeDto.setAttachTypeName(attachType.getAttachTypeName());
        
        return attachTypeDto;
    }
    
    public static AttachmentDto convertToDto(Attachment attach){
        AttachmentDto attachDto = new AttachmentDto();
        attachDto.setId(attach.getIdAttachment());
        attachDto.setName(attach.getName());
        attachDto.setAttachPath(attach.getAttachmentPath());
        attachDto.setMailId(attach.getMail().getIdMail());
        if(attach.getAttachType() != null){
            attachDto.setAttachType(convertToDto(attach.getAttachType()));
        }
        
        
        return attachDto;
    }
    
    public static LinkDto convertToDto(Link link){
        LinkDto linkDto = new LinkDto();
        linkDto.setId(link.getIdLink());
        linkDto.setUrl(link.getUrlLink());
        linkDto.setMailId(link.getMail().getIdMail());
        
        return linkDto;
    }
    
    public static BroadcastListDto convertToDto(BroadcastList bcl){
        BroadcastListDto bclDto = new BroadcastListDto();
        bclDto.setId(bcl.getIdBroadcastList());
        bclDto.setLibelle(bcl.getLibelle());
        
        return bclDto;
    }
    
    public static PersonDto convertToDto(Person person){
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getIdPerson());
        personDto.setName(person.getName());
        return personDto;
    }
    
    
    public static EmailDto convertToDto(Email email){
        EmailDto emailDto = new EmailDto();
        emailDto.setId(email.getIdEmail());
        emailDto.setEAddress(email.getEmailAddress());
        emailDto.setSignature(email.getEmailSignature());
        
        if(email.getPerson() != null){
            emailDto.setPersonDto(convertToDto(email.getPerson()));
        }
        
        if(email.getBroadcastList() != null){
            emailDto.setBclDto(convertToDto(email.getBroadcastList()));
        }
        
        return emailDto;
    }  
}
