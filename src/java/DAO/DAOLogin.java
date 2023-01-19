/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import model.pojo.AdminLogin;
import model.pojo.RegistrationData;
import org.hibernate.Query;
import org.hibernate.Transaction;
import util.HibernateUtil;
/**
 *
 * @author aldys
 */
public class DAOLogin {
    public List<AdminLogin> getBy (String uName, String uPass)
    {
        Transaction trans = null;
        AdminLogin us = new AdminLogin();
        List<AdminLogin> user = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from AdminLogin where username=:uName AND password=:uPass");
            query.setString("uName", uName);
            query.setString("uPass", uPass);
            us = (AdminLogin) query.uniqueResult();
            user = query.list();
            trans.commit();
        }catch (Exception e){
            System.out.println(e);
        }
        return user;
    }
    
    public void add_admin (AdminLogin admin)
    {
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            session.save(admin);
            trans.commit();
            
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
