/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Objetos;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utilidade.ContratosUtil;

/**
 *
 * @author amrsilva
 */
public class ControleObjetos {
    private final Session Sessao = ContratosUtil.getSessionFactory().openSession();
    
    public void Incluir(Objetos Objeto) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.persist(Objeto);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex.getCause());
        }
    }
    
    public void Excluir(Objetos Objeto) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.delete(Objeto);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public List<Objetos> ListarTodos() throws Exception{
        try {
            Query Consulta =  Sessao.createQuery("FROM Objetos ob ORDER BY ob.objeto");
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he.getCause());
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex.getCause());
        }
    }
}
