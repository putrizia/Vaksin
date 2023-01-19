/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Query;
import model.pojo.AdminLogin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author aldys
 */
public class DAOAdmin {
    
    public List<AdminLogin> getBy (String uName, String uPass)
    {
        Transaction trans = null;
        AdminLogin adm = new AdminLogin();
        List <AdminLogin> admi = new ArrayList();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from AdminLogin where username=: uName AND password=: uPass");
            query.setString("uName", uName);
            query.setString("uPass", uPass);
            adm = (AdminLogin) query.uniqueResult();
            admi = query.list();
            trans.commit();;
        }catch (Exception e){
            System.out.println(e);
        }
        return admi;
    }
    
    public List<AdminLogin> retrvetabeladmin()
    {
        List daftar = new ArrayList();
        AdminLogin daftar1 = new AdminLogin();
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from AdminLogin");
            daftar = query.list();
            daftar.add(daftar1.getIdAdmin());
            daftar.add(daftar1.getUsername());
            daftar.add(daftar1.getPassword());
            trans.commit();
        }catch (Exception e){
            System.out.println(e);
        }
        return daftar;
    }
    
    public List<AdminLogin> getbyID (Integer idA)
    {
        AdminLogin adm = new AdminLogin();
        List <AdminLogin> admi = new ArrayList();
        
        Transaction trans = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from AdminLogin where id_admin= :id");
            query.setInteger("id", idA);
            adm = (AdminLogin) query.uniqueResult();
            admi = query.list();
            trans.commit();
        }catch (Exception e){
            System.out.println(e);
        }
        return admi;
    }
    
    public void deleteAdmin (Integer idA)
        {
            Transaction trans = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                trans = session.beginTransaction();
                AdminLogin adm = (AdminLogin) session.load(AdminLogin.class, new Integer (idA));
                session.delete(adm);
                trans.commit();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    
    public void editAdmin (AdminLogin adm)
    {
        Transaction trans = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try{
                trans = session.beginTransaction();
                session.update(adm);
                trans.commit();
            }catch (Exception e){
                System.out.println(e);
            }
    }
    
    public void resetAdmin(AdminLogin adm)
   {
       Transaction trans = null;
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           session.clear();
           trans.commit();
       }catch(Exception e){
           System.out.println(e);
       }
   }
}
