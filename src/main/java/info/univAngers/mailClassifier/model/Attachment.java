/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name="attachment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attachment.findAll", query = "SELECT a FROM Attachment a"),
    @NamedQuery(name = "Attachment.findByIdAttachment", query = "SELECT a FROM Attachment a WHERE a.idAttachment = :idAttachment"),
    @NamedQuery(name = "Attachment.findByName", query = "SELECT a FROM Attachment a WHERE a.name = :name")})
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_attachment", nullable = false)
    @Getter
    private Integer idAttachment;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "attachment_path", nullable = false, length = 400)
    @Getter @Setter
    private String attachmentPath;
    
   
    
    @JoinColumn(name = "mail_id", referencedColumnName = "id_mail", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private Mail mail;
    
    @JoinColumn(name = "attach_type_id", referencedColumnName = "id_attach_type", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    @Setter
    private AttachType attachType;

    public Attachment() {
    }

    public Attachment(Integer idAttachment) {
        this.idAttachment = idAttachment;
    }

    public Attachment(Integer idAttachment, String name, String attachmentPath) {
        this.idAttachment = idAttachment;
        this.name = name;
        this.attachmentPath = attachmentPath;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttachment != null ? idAttachment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attachment)) {
            return false;
        }
        Attachment other = (Attachment) object;
        if ((this.idAttachment == null && other.idAttachment != null) || (this.idAttachment != null && !this.idAttachment.equals(other.idAttachment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.univAngers.mailClassifier.model.Attachment[ idAttachment=" + idAttachment + " ]";
    }
    
}
