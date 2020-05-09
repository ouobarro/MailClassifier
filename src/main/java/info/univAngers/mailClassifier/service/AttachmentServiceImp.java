/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dao.AttachmentDaoInterface;
import info.univAngers.mailClassifier.dto.AttachmentDto;
import info.univAngers.mailClassifier.model.Attachment;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author barro
 */

@Service
@Transactional
public class AttachmentServiceImp implements AttachmentServiceInterface{
    
    @Autowired
    private AttachmentDaoInterface attachmentDao;

    @Override
    public AttachmentDto getAttachmentByIdAttachment(Integer idAttachment) throws Exception {
        try{
            Attachment attachment = attachmentDao.getAttachmentByIdAttachment(idAttachment);
            if(attachment != null){
                return EntityDtoConverter.convertToDto(attachment);
            }else{
                return null;
            }
        }catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public List<AttachmentDto> getAllAttachment() throws Exception {
        try{
            List<Attachment> attachmentList = attachmentDao.getAllAttachment();
            List<AttachmentDto> attachmentDtoList = new ArrayList<>();
            if(attachmentList != null){
                for(Attachment attachment: attachmentList){
                    attachmentDtoList.add(EntityDtoConverter.convertToDto(attachment));
                }
            }
            return attachmentDtoList;
        } catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public List<AttachmentDto> getAttachmentByMail(Integer idMail) throws Exception {
        try{
            List <Attachment> attachmentList = attachmentDao.getAttachmentByMail(idMail);
            List<AttachmentDto> attachmentDtoList = new ArrayList<>();
            if(attachmentList != null){
                for(Attachment attachment: attachmentList){
                    attachmentDtoList.add(EntityDtoConverter.convertToDto(attachment));
                }
            }
            return attachmentDtoList;
        }catch(Exception ex){
           throw ex;    
        }
    }

    @Override
    public void insertAttachment(Attachment attachment) throws Exception {
        try{
            attachmentDao.insertAttachment(attachment);
        }catch(Exception ex){
           throw ex;    
        }
    }
    
    
}
