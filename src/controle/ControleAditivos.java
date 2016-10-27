/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Aditivos;
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
public class ControleAditivos {
    private final Session Sessao = ContratosUtil.getSessionFactory().openSession();
    
    public void Incluir(Aditivos Aditivo) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.persist(Aditivo);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex.getCause());
        }
    }
    
    public void Alterar(Aditivos Aditivo) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.merge(Aditivo);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex.getCause());
        }
    }
    
    public void Excluir(Aditivos Aditivo) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.delete(Aditivo);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public List<Aditivos> ListarTodos(Contratos Pai) throws Exception{
        try {
            Query Consulta =  Sessao.createQuery("FROM Aditivos ad WHERE ad.contratos = :Pai ORDER BY ad.numero");
            Consulta.setParameter("Pai", Pai);
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he.getCause());
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex.getCause());
        }
    }
}
