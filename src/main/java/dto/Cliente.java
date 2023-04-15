/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jesus
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCodiClie", query = "SELECT c FROM Cliente c WHERE c.codiClie = :codiClie"),
    @NamedQuery(name = "Cliente.findByCodiTipoDocu", query = "SELECT c FROM Cliente c WHERE c.codiTipoDocu = :codiTipoDocu"),
    @NamedQuery(name = "Cliente.findByNumeDocu", query = "SELECT c FROM Cliente c WHERE c.numeDocu = :numeDocu"),
    @NamedQuery(name = "Cliente.findByAppaClie", query = "SELECT c FROM Cliente c WHERE c.appaClie = :appaClie"),
    @NamedQuery(name = "Cliente.findByApmaClie", query = "SELECT c FROM Cliente c WHERE c.apmaClie = :apmaClie"),
    @NamedQuery(name = "Cliente.findByNombClie", query = "SELECT c FROM Cliente c WHERE c.nombClie = :nombClie"),
    @NamedQuery(name = "Cliente.findByRaznSociClie", query = "SELECT c FROM Cliente c WHERE c.raznSociClie = :raznSociClie"),
    @NamedQuery(name = "Cliente.findByNumeCeluClie", query = "SELECT c FROM Cliente c WHERE c.numeCeluClie = :numeCeluClie"),
    @NamedQuery(name = "Cliente.findByMailClie", query = "SELECT c FROM Cliente c WHERE c.mailClie = :mailClie"),
    @NamedQuery(name = "Cliente.findBySexoClie", query = "SELECT c FROM Cliente c WHERE c.sexoClie = :sexoClie"),
    @NamedQuery(name = "Cliente.findByCodiUsuaAlta", query = "SELECT c FROM Cliente c WHERE c.codiUsuaAlta = :codiUsuaAlta"),
    @NamedQuery(name = "Cliente.findByFechRegiAlta", query = "SELECT c FROM Cliente c WHERE c.fechRegiAlta = :fechRegiAlta"),
    @NamedQuery(name = "Cliente.findByCodiUsuaModi", query = "SELECT c FROM Cliente c WHERE c.codiUsuaModi = :codiUsuaModi"),
    @NamedQuery(name = "Cliente.findByFechRegiModi", query = "SELECT c FROM Cliente c WHERE c.fechRegiModi = :fechRegiModi")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiClie")
    private Integer codiClie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codiTipoDocu")
    private String codiTipoDocu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "numeDocu")
    private String numeDocu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "appaClie")
    private String appaClie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "apmaClie")
    private String apmaClie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nombClie")
    private String nombClie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "raznSociClie")
    private String raznSociClie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "numeCeluClie")
    private String numeCeluClie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mailClie")
    private String mailClie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexoClie")
    private String sexoClie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiUsuaAlta")
    private int codiUsuaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechRegiAlta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechRegiAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiUsuaModi")
    private int codiUsuaModi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechRegiModi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechRegiModi;

    public Cliente() {
    }

    public Cliente(Integer codiClie) {
        this.codiClie = codiClie;
    }

    public Cliente(Integer codiClie, String codiTipoDocu, String numeDocu, String appaClie, String apmaClie, String nombClie, String raznSociClie, String numeCeluClie, String mailClie, String sexoClie, int codiUsuaAlta, Date fechRegiAlta, int codiUsuaModi, Date fechRegiModi) {
        this.codiClie = codiClie;
        this.codiTipoDocu = codiTipoDocu;
        this.numeDocu = numeDocu;
        this.appaClie = appaClie;
        this.apmaClie = apmaClie;
        this.nombClie = nombClie;
        this.raznSociClie = raznSociClie;
        this.numeCeluClie = numeCeluClie;
        this.mailClie = mailClie;
        this.sexoClie = sexoClie;
        this.codiUsuaAlta = codiUsuaAlta;
        this.fechRegiAlta = fechRegiAlta;
        this.codiUsuaModi = codiUsuaModi;
        this.fechRegiModi = fechRegiModi;
    }

    public Cliente(Integer codiClie, String numeDocu, String appaClie, String apmaClie, String nombClie, String raznSociClie, String numeCeluClie, String mailClie, String sexoClie) {
        this.codiClie = codiClie;
        this.numeDocu = numeDocu;
        this.appaClie = appaClie;
        this.apmaClie = apmaClie;
        this.nombClie = nombClie;
        this.raznSociClie = raznSociClie;
        this.numeCeluClie = numeCeluClie;
        this.mailClie = mailClie;
        this.sexoClie = sexoClie;
    }

    
    
    
    public Integer getCodiClie() {
        return codiClie;
    }

    public void setCodiClie(Integer codiClie) {
        this.codiClie = codiClie;
    }

    public String getCodiTipoDocu() {
        return codiTipoDocu;
    }

    public void setCodiTipoDocu(String codiTipoDocu) {
        this.codiTipoDocu = codiTipoDocu;
    }

    public String getNumeDocu() {
        return numeDocu;
    }

    public void setNumeDocu(String numeDocu) {
        this.numeDocu = numeDocu;
    }

    public String getAppaClie() {
        return appaClie;
    }

    public void setAppaClie(String appaClie) {
        this.appaClie = appaClie;
    }

    public String getApmaClie() {
        return apmaClie;
    }

    public void setApmaClie(String apmaClie) {
        this.apmaClie = apmaClie;
    }

    public String getNombClie() {
        return nombClie;
    }

    public void setNombClie(String nombClie) {
        this.nombClie = nombClie;
    }

    public String getRaznSociClie() {
        return raznSociClie;
    }

    public void setRaznSociClie(String raznSociClie) {
        this.raznSociClie = raznSociClie;
    }

    public String getNumeCeluClie() {
        return numeCeluClie;
    }

    public void setNumeCeluClie(String numeCeluClie) {
        this.numeCeluClie = numeCeluClie;
    }

    public String getMailClie() {
        return mailClie;
    }

    public void setMailClie(String mailClie) {
        this.mailClie = mailClie;
    }

    public String getSexoClie() {
        return sexoClie;
    }

    public void setSexoClie(String sexoClie) {
        this.sexoClie = sexoClie;
    }

    public int getCodiUsuaAlta() {
        return codiUsuaAlta;
    }

    public void setCodiUsuaAlta(int codiUsuaAlta) {
        this.codiUsuaAlta = codiUsuaAlta;
    }

    public Date getFechRegiAlta() {
        return fechRegiAlta;
    }

    public void setFechRegiAlta(Date fechRegiAlta) {
        this.fechRegiAlta = fechRegiAlta;
    }

    public int getCodiUsuaModi() {
        return codiUsuaModi;
    }

    public void setCodiUsuaModi(int codiUsuaModi) {
        this.codiUsuaModi = codiUsuaModi;
    }

    public Date getFechRegiModi() {
        return fechRegiModi;
    }

    public void setFechRegiModi(Date fechRegiModi) {
        this.fechRegiModi = fechRegiModi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiClie != null ? codiClie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.codiClie == null && other.codiClie != null) || (this.codiClie != null && !this.codiClie.equals(other.codiClie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Cliente[ codiClie=" + codiClie + " ]";
    }
    
}
