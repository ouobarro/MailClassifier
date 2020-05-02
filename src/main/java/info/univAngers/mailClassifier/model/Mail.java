/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
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
@Table(name = "mail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mail.findAll", query = "SELECT m FROM Mail m"),
    @NamedQuery(name = "Mail.findByIdmail", query = "SELECT m FROM Mail m WHERE m.idMail = :idMail"),
    @NamedQuery(name = "Mail.findBySubject", query = "SELECT m FROM Mail m WHERE m.subject = :subject"),
    @NamedQuery(name = "Mail.findByContent", query = "SELECT m FROM Mail m WHERE m.content = :content"),
    @NamedQuery(name = "Mail.findBySendDate", query = "SELECT m FROM Mail m WHERE m.sendDate = :sendDate")})
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mail", nullable = false)
    @Getter
    private Integer idMail;

    @Size(max = 100)
    @Column(name = "subject", length = 100)
    @Getter
    @Setter
    private String subject;

    @Size(max = 40000)
    @Column(name = "content", length = 40000)
    @Getter
    @Setter
    private String content;

    @Basic(optional = false)
    @NotNull
    @Column(name = "send_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date sendDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Attachment> attachmentList;

    @JoinColumn(name = "broadcast_list_id", referencedColumnName = "id_broadcast_list", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @Getter
    @Setter
    private BroadcastList broadcastList;

    @JoinColumn(name = "sender_id", referencedColumnName = "id_person", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter
    @Setter
    private Person sender;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "person_mail_to",
            joinColumns = @JoinColumn(name = "mail_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @Getter @Setter
    private List<Person> receiverList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Link> linkList;

    public Mail() {
    }

    public Mail(Integer idMail) {
        this.idMail = idMail;
    }

    public Mail(Integer idMail, Date sendDate) {
        this.idMail = idMail;
        this.sendDate = sendDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMail != null ? idMail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mail)) {
            return false;
        }
        Mail other = (Mail) object;
        if ((this.idMail == null && other.idMail != null) || (this.idMail != null && !this.idMail.equals(other.idMail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.univAngers.mailClassifier.model.Mail[ idMail=" + idMail + " ]";
    }

}
