package entidades;
// Generated 04/11/2016 16:56:09 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Contas generated by hbm2java
 */
@Entity
@Table(name="contas"
    ,catalog="contratosbd"
    , uniqueConstraints = {@UniqueConstraint(columnNames={"Contratos_idContrato", "Agencia", "Numero"}), @UniqueConstraint(columnNames={"Descricao", "Contratos_idContrato"})} 
)
public class Contas  implements java.io.Serializable {


     private Integer idContas;
     private Contratos contratos;
     private String descricao;
     private int agencia;
     private int numero;

    public Contas() {
    }

    public Contas(Contratos contratos, String descricao, int agencia, int numero) {
       this.contratos = contratos;
       this.descricao = descricao;
       this.agencia = agencia;
       this.numero = numero;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idContas", unique=true, nullable=false)
    public Integer getIdContas() {
        return this.idContas;
    }
    
    public void setIdContas(Integer idContas) {
        this.idContas = idContas;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Contratos_idContrato", nullable=false)
    public Contratos getContratos() {
        return this.contratos;
    }
    
    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    
    @Column(name="Descricao", nullable=false, length=25)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="Agencia", nullable=false)
    public int getAgencia() {
        return this.agencia;
    }
    
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    
    @Column(name="Numero", nullable=false)
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }




}

