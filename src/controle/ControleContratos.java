/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Contratos;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utilidade.ContratosUtil;

/**
 *
 * @author amrsilva
 */
public class ControleContratos {
    private final Session Sessao = ContratosUtil.getSessionFactory().openSession();
    
    public void Incluir(Contratos Contrato) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.persist(Contrato);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public void Alterar(Contratos Contrato) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.merge(Contrato);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public void Excluir(Contratos Contrato) throws Exception{
        try {
            Sessao.getTransaction().begin();
            Sessao.delete(Contrato);
            Sessao.getTransaction().commit();
        } catch ( Exception ex) {
            Sessao.getTransaction().rollback();
            throw new Exception(ex);
        }
    }
    
    public List<Contratos> ListarTodos() throws Exception{
        try {
            Query Consulta =  Sessao.createSQLQuery("SELECT * FROM contratosbd.contratos" 
                    + " ORDER BY agencia AND vigencia").addEntity(Contratos.class);
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he);
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public List<Contratos> ListarComUmFiltro(String Dados, int Tipo) throws Exception{
        Query Consulta=null;
        try {
            switch (Tipo){
                case 2:
                    Consulta =  Sessao.createSQLQuery("SELECT * FROM contratosbd.contratos" 
                            + " WHERE contratos.Agencia = '" + Dados 
                            + "' ORDER BY agencia AND vigencia;").addEntity(Contratos.class);
                break;
                case 3:
                    Consulta =  Sessao.createSQLQuery("SELECT * FROM contratosbd.contratos" 
                            + " WHERE contratos.Razao LIKE '%" + Dados 
                            + "%' ORDER BY agencia AND vigencia;").addEntity(Contratos.class);
                break;
                case 1:
                    Consulta =  Sessao.createSQLQuery("SELECT * FROM contratosbd.contratos"
                            + " WHERE contratos.Cnpj = '" + Dados 
                            + "' ORDER BY agencia AND vigencia;").addEntity(Contratos.class);
                break;
                case 0:
                    Consulta =  Sessao.createSQLQuery("SELECT * FROM contratosbd.contratos"
                            + " WHERE contratos.Referencia = '" + Dados 
                            + "' ORDER BY agencia AND vigencia;").addEntity(Contratos.class);
                break;
                case 4:
                    Consulta =  Sessao.createSQLQuery("SELECT * FROM contratosbd.contratos"
                            + " WHERE contratos.vigencia BETWEEN '" + Dados.split(";")[0] + "' AND '" 
                            + Dados.split(";")[1] + "' ORDER BY agencia AND vigencia;").addEntity(Contratos.class);
                break;
                case 5:
                    Consulta =  Sessao.createSQLQuery("SELECT * FROM contratosbd.contratos"
                            + " WHERE contratos.assinatura BETWEEN '" + Dados.split(";")[0] + "' AND '" 
                            + Dados.split(";")[1] + "' ORDER BY agencia AND vigencia;").addEntity(Contratos.class);
                break;
            }
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he);
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public List<Contratos> ListarComMultiplosFiltros(String[] Dados) throws Exception{
        Query Consulta;
        String[] tempDados = Dados;
        String Ref = tempDados[0],
               Ide = tempDados[1],
               Ano = tempDados[2],
               Age = tempDados[3],
               Raz = "%"+tempDados[4]+"%",
               Obj = tempDados[5],
               DtI = tempDados[6],
               DtF = tempDados[7];
        String Op = "", Sep = "", vSQL = "";
        try {
            if (!tempDados[0].equals("")) Dados[0]= "con.referencia = :ref ";
            if (!tempDados[1].equals("")) Dados[1]= "con.identificador = :ide ";
            if (!tempDados[2].equals("")) Dados[2]= "con.ano = :ano ";
            if (!tempDados[3].equals("")) Dados[3]= "con.agencia = :age";
            if (!tempDados[4].equals("")) Dados[4]= "con.razao LIKE :raz";
            if (!tempDados[5].equals("")) Dados[5]= "con.objeto = :obj";
            if (!tempDados[6].equals("") && !Dados[7].equals("")){
                Op = " BETWEEN ";
                tempDados[6]= ":dti AND";
                tempDados[7] =  " :dtf";
            } else if (tempDados[6].equals("") && !tempDados[7].equals("")){
                Op = " > ";
                tempDados[7]= " :dtf";
            } else if (!tempDados[6].equals("") && tempDados[7].equals("")){
                Op = " < ";
                tempDados[6]= " :dti";
            }
            if (!Op.equals("")){
                if (tempDados[8].equals("true")){
                    tempDados[8] = "(con.vigencia " + Op +  tempDados[6] + tempDados[7];
                    if (!tempDados[9].equals("true")) tempDados[9] = tempDados[8] + " AND con.vigencia IS NOT NULL) ";
                    else tempDados[9]= tempDados[8] + " OR con.vigencia IS NULL) ";
                } else if (tempDados[8].equals("false")) tempDados[9] = "con.assinatura " + Op +  tempDados[6] + tempDados[7];
            } else tempDados[9] = "";
            tempDados[6] = "";
            tempDados[7] = "";
            tempDados[8] = "";
            for (String Dado : Dados){
                if (!Dado.equals("")){
                    vSQL= vSQL + Sep + Dado;
                    Sep = " AND ";
                }
            }
            if (!vSQL.equals("")){
                Consulta =  Sessao.createQuery("FROM Contratos con WHERE " + vSQL + " ORDER BY con.referencia DESC, con.ano DESC");
                if  (!Ref.equals("")) Consulta.setParameter("ref", Ref);
                if  (!Ide.equals("")) Consulta.setParameter("ide", Ide);
                if  (!Ano.equals("")) Consulta.setParameter("ano", Ano);
                if  (!Age.equals("")) Consulta.setParameter("age", Age);
                if  (!Raz.equals("%%")) Consulta.setParameter("raz", Raz);
                if  (!Obj.equals("")) Consulta.setParameter("obj", Obj);
                if  (!DtI.equals("")) Consulta.setParameter("dti", DtI);
                if  (!DtF.equals("")) Consulta.setParameter("dtf", DtF);
            } else {
                Consulta =  Sessao.createQuery("FROM Contratos con ORDER BY con.referencia DESC, con.ano DESC");
            }
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he);
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public List<Contratos> ListarVencidos() throws Exception{
        Query Consulta;
        GregorianCalendar c1 = new GregorianCalendar();
        c1.setTimeInMillis(System.currentTimeMillis());
        String DataInicial = (new SimpleDateFormat("yyyy-MM-dd").format(c1.getTime()));
        c1.add(GregorianCalendar.MONTH, +3);
        String DataFinal = (new SimpleDateFormat("yyyy-MM-dd").format(c1.getTime()));
        try {
            Consulta =  Sessao.createQuery("FROM Contratos con WHERE con.vigencia BETWEEN '" + DataInicial
                    + "' AND '" + DataFinal + "' ORDER BY agencia AND vigencia;");
            return Consulta.list();
        } catch (HibernateException he) {
            throw new HibernateException(he);
        } catch (ExceptionInInitializerError ex){
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public void Backup(){
        Sessao.createSQLQuery("SELECT * INTO OUTFILE 'c:\\Teste.txt'\n" +
                "  FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"'\n" +
                "  LINES TERMINATED BY '\\n'\n" +
                "  FROM contratosbd.contratos;");
    }
}
