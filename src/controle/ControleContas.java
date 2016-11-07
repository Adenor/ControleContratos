/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Contas;
import entidades.Contratos;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utilidade.ContratosUtil;

/**
 *
 * @author amrsilva
 */
public class ControleContas {
    private final Session Sessao = ContratosUtil.getSessionFactory().openSession();
    
    public void Incluir(Contas Conta) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.persist(Conta);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex.getCause());
        }
    }
    
    public void Alterar(Contas Conta) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.merge(Conta);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex.getCause());
        }
    }
    
    public void Excluir(Contas Conta) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.delete(Conta);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public List<Contas> ListarTodos(Contratos Pai) throws Exception{
        try {
            Query Consulta =  Sessao.createQuery("FROM Contas ct WHERE ct.contratos = :Pai ORDER BY ct.numero");
            Consulta.setParameter("Pai", Pai);
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he.getCause());
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex.getCause());
        }
    }
}
