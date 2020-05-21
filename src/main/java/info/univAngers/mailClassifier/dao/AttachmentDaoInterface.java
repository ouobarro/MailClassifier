/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Attachment;
import info.univAngers.mailClassifier.model.Link;
import java.util.List;

/**
 *
 * @author barro
 */
public interface AttachmentDaoInterface {
    
    public Attachment getAttachmentByIdAttachment(Integer idAttachment) throws Exception;
    
    public List<Attachment> getAllAttachment() throws Exception;
    
    public List<Attachment> getAttachmentByMail(Integer idMail) throws Exception;
    
    public void insertAttachment(Attachment attachment) throws Exception;
    
    public Integer countAttachment() throws Exception;
    
}
