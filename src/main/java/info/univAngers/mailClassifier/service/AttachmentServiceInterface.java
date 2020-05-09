/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.AttachmentDto;
import info.univAngers.mailClassifier.model.Attachment;
import java.util.List;

/**
 *
 * @author barro
 */
public interface AttachmentServiceInterface {
    
    public AttachmentDto getAttachmentByIdAttachment(Integer idAttachment) throws Exception;
    
    public List<AttachmentDto> getAllAttachment() throws Exception;
    
    public List<AttachmentDto> getAttachmentByMail(Integer idMail) throws Exception;
    
    public void insertAttachment(Attachment attachment) throws Exception;
}
