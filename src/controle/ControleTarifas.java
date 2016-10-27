/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Tarifas;
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
public class ControleTarifas {
    private final Session Sessao = ContratosUtil.getSessionFactory().openSession();
    
    public void Incluir(Tarifas Tarifa) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.persist(Tarifa);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex.getCause());
        }
    }
    
    public void Alterar(Tarifas Tarifa) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.merge(Tarifa);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex.getCause());
        }
    }
    
    public void Excluir(Tarifas Tarifa) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.delete(Tarifa);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public List<Tarifas> ListarTodos(Contratos Pai) throws Exception{
        try {
            Query Consulta =  Sessao.createQuery("FROM Tarifas tf WHERE tf.contratos = :Pai");
            Consulta.setParameter("Pai", Pai);
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he.getCause());
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex.getCause());
        }
    }
}
