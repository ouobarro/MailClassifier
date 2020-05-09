/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
@Entity
@Table(name = "attach_type")
@NamedQueries({
    @NamedQuery(name = "AttachType.findAll", query = "SELECT a FROM AttachType a"),
    @NamedQuery(name = "AttachType.findByIdAttachType", query = "SELECT a FROM AttachType a WHERE a.idAttachType = :idAttachType"),
    @NamedQuery(name = "AttachType.findByAttachTypeName", query = "SELECT a FROM AttachType a WHERE a.attachTypeName = :attachTypeName")})
public class AttachType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Getter
    @Column(name = "id_attach_type", nullable = false)
    private Integer idAttachType;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "attach_type_name", nullable = false, length = 12)
    @Getter @Setter
    private String attachTypeName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attachType", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Attachment> attachList;

    public AttachType() {
    }

    public AttachType(Integer idAttachType) {
        this.idAttachType = idAttachType;
    }

    public AttachType(Integer idAttachType, String attachTypeName) {
        this.idAttachType = idAttachType;
        this.attachTypeName = attachTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttachType != null ? idAttachType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttachType)) {
            return false;
        }
        AttachType other = (AttachType) object;
        if ((this.idAttachType == null && other.idAttachType != null) || (this.idAttachType != null && !this.idAttachType.equals(other.idAttachType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.univAngers.mailClassifier.model.AttachType[ idAttachType=" + idAttachType + " ]";
    }
    
}
