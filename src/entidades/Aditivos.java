package entidades;
// Generated 04/11/2016 16:56:09 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Aditivos generated by hbm2java
 */
@Entity
@Table(name="aditivos"
    ,catalog="contratosbd"
)
public class Aditivos  implements java.io.Serializable {


     private Integer idAditivo;
     private Contratos contratos;
     private int numero;
     private String descricao;
     private Date assinatura;

    public Aditivos() {
    }

    public Aditivos(Contratos contratos, int numero, String descricao, Date assinatura) {
       this.contratos = contratos;
       this.numero = numero;
       this.descricao = descricao;
       this.assinatura = assinatura;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idAditivo", unique=true, nullable=false)
    public Integer getIdAditivo() {
        return this.idAditivo;
    }
    
    public void setIdAditivo(Integer idAditivo) {
        this.idAditivo = idAditivo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Contratos_idContrato", nullable=false)
    public Contratos getContratos() {
        return this.contratos;
    }
    
    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    
    @Column(name="Numero", nullable=false)
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    
    @Column(name="Descricao", nullable=false, length=250)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="Assinatura", nullable=false, length=10)
    public Date getAssinatura() {
        return this.assinatura;
    }
    
    public void setAssinatura(Date assinatura) {
        this.assinatura = assinatura;
    }




}


