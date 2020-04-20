/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.univAngers.mailClassifier.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author barro
 */
@Entity
@Table(name="mail")
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
    @Column(length = 100)
    @Getter @Setter
    private String subject;
    
    @Size(max = 40000)
    @Column(length = 40000)
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date sendDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail", fetch = FetchType.LAZY)
    private List<Attachment> attachmentList;
    
    @JoinColumn(name = "broadcast_list_id", referencedColumnName = "id_broadcast_list", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private BroadcastList broadcastList;
    
    @JoinColumn(name = "sender_id", referencedColumnName = "id_person", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private Person sender;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail", fetch = FetchType.LAZY)
    private List<PersonIncomeMail> personIncomeMailList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail", fetch = FetchType.LAZY)
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


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @XmlTransient
    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    @XmlTransient
    public List<PersonIncomeMail> getPersonIncomeMailList() {
        return personIncomeMailList;
    }

    public void setPersonIncomeMailList(List<PersonIncomeMail> personIncomeMailList) {
        this.personIncomeMailList = personIncomeMailList;
    }

    @XmlTransient
    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
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
