package entidades;
// Generated 21/11/2016 12:48:18 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tarifas generated by hbm2java
 */
@Entity
@Table(name="tarifas"
    ,catalog="contratosbd"
)
public class Tarifas  implements java.io.Serializable {


     private Integer idTarifa;
     private Contratos contratos;
     private String descricao;
     private BigDecimal valor;

    public Tarifas() {
    }

    public Tarifas(Contratos contratos, String descricao, BigDecimal valor) {
       this.contratos = contratos;
       this.descricao = descricao;
       this.valor = valor;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idTarifa", unique=true, nullable=false)
    public Integer getIdTarifa() {
        return this.idTarifa;
    }
    
    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Contratos_idContrato", nullable=false)
    public Contratos getContratos() {
        return this.contratos;
    }
    
    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    
    @Column(name="Descricao", nullable=false, length=15)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="Valor", nullable=false, precision=6)
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }




}


