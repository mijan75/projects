/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_rent;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author User
 */
public class SingleFactory {
    private static final SingleFactory instance = new SingleFactory();
    
    private static SessionFactory factory;
    
    private SingleFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
