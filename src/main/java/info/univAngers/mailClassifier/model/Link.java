/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
@Entity
@Table(name="link")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Link.findAll", query = "SELECT l FROM Link l"),
    @NamedQuery(name = "Link.findByIdLink", query = "SELECT l FROM Link l WHERE l.idLink = :idLink"),
    @NamedQuery(name = "Link.findByUrlLink", query = "SELECT l FROM Link l WHERE l.urlLink = :urlLink")})
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_link", nullable = false)
    @Getter
    private Integer idLink;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "url_link", nullable = false, length = 400)
    @Getter @Setter
    private String urlLink;
    
    @JoinColumn(name = "mail_id", referencedColumnName = "id_mail", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private Mail mail;

    public Link() {
    }

    public Link(Integer idLink) {
        this.idLink = idLink;
    }

    public Link(Integer idLink, String urlLink) {
        this.idLink = idLink;
        this.urlLink = urlLink;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLink != null ? idLink.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.idLink == null && other.idLink != null) || (this.idLink != null && !this.idLink.equals(other.idLink))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.univAngers.mailClassifier.model.Link[ idLink=" + idLink + " ]";
    }
    
}
