/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.dao;

import info.univAngers.mailClassifier.model.Link;
import java.util.List;

/**
 *
 * @author barro
 */

public interface LinkDaoInterface {
    
    public List<Link> getLinkByMail(Integer idMail) throws Exception;
    
    public Link getLinkByIdLink(Integer idLink) throws Exception;
    
     public Link getLinkByUrlLink(String urlLink) throws Exception;
    
    public List<Link> getAllLink() throws Exception;
}
