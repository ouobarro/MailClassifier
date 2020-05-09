/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.AttachTypeDto;
import info.univAngers.mailClassifier.model.AttachType;
import java.util.List;

/**
 *
 * @author barro
 */
public interface AttachTypeServiceInterface {
    
    public List<AttachTypeDto> getAllAttachType() throws Exception;
    
}
