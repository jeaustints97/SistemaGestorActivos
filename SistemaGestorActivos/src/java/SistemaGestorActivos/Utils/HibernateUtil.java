/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaGestorActivos.Utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private Transaction transc;
    private Session sesion;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void iniciaOperacion() throws HibernateException{
        this.sesion= HibernateUtil.getSessionFactory().openSession();
        this.transc = sesion.beginTransaction();
    }
    
    public void manejaExcepcion(HibernateException he) throws HibernateException{
        transc.rollback();
        throw new HibernateException("Ha ocurrido un error en la capa de acceso a datos",he);
    }

    public Transaction getTransc() {
        return transc;
    }

    public void setTransc(Transaction transc) {
        this.transc = transc;
    }

    public Session getSesion() {
        return sesion;
    }

    public void setSesion(Session sesion) {
        this.sesion = sesion;
    }
    
    
}