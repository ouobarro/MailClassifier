/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.service;

import info.univAngers.mailClassifier.dto.BroadcastListDto;
import info.univAngers.mailClassifier.dto.EmailDto;
import java.util.List;

/**
 *
 * @author barro
 */
public interface BroadcastListServiceInterface {
    
    public BroadcastListDto getBroadcastListById(Integer id) throws Exception;
    
    public List <BroadcastListDto> getAllBroadcastList() throws Exception;
    
    public List<EmailDto> getBclEmailList(Integer idBcl) throws Exception;
    
    public BroadcastListDto getBroadcastListByLibelle(String libelle) throws Exception;
    
}
