

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidade;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author amrsilva
 */
public class ContratosUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://"+ formularios.formPrincipal.SERVIDOR +
                ":3306/ContratosBD?zeroDateTimeBehavior=convertToNull");
        configuration.setProperty("hibernate.connection.username", formularios.formPrincipal.USUARIO);
        configuration.setProperty("hibernate.connection.password", formularios.formPrincipal.SENHA);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return configureSessionFactory();
    }

    
}
