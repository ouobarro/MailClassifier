/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.AttachType;
import java.util.List;

/**
 *
 * @author barro
 */
public interface AttachTypeDaoInterface {
    
    public AttachType getAttachTypeById(Integer idAttachType) throws Exception;
    
    public AttachType getAttachTypeByName(String typeName) throws Exception;
    
    public List<AttachType> getAllAttachType() throws Exception;
        
    public void insertAttachType(AttachType attachType) throws Exception;
    
}
