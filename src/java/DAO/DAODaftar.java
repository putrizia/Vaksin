/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.pojo.RegistrationData;
import util.HibernateUtil;
/**
 *
 * @author aldys
 */
public class DAODaftar {
     public List<RegistrationData> getBy(String uNama, Integer uNik, String uAlamat, Integer uNohp, String uEmail, String uVaksin, String uTanggal) 
   {
       Transaction trans = null;
       RegistrationData rd = new RegistrationData();
       List<RegistrationData> regis = new ArrayList();
       
       Session session = HibernateUtil.getSessionFactory().openSession();
       try{
           trans = session.beginTransaction();
           Query query = session.createQuery("from RegistrationData where namaLengkap=:uNama AND nik=:uNik AND alamat=:uAlamat AND noHp=:uNohp AND email=:uEmail AND vaksin=:uVaksin AND tanggal=:uTanggal");
           query.setString("uNama", uNama);
           query.setInteger("uNik", uNik);
           query.setString("uAlamat", uAlamat);
           query.setInteger("uNohp", uNohp);
           query.setString("uEmail", uEmail);
           query.setString("uVaksin", uVaksin);
           query.setString("uTanggal", uTanggal);
           rd = (RegistrationData) query.uniqueResult();
           regis = query.list();
           trans.commit();
       } catch (Exception e){
           System.out.println(e);
       }
       return regis;
   }
     
   public void add_product(RegistrationData regis)
   {
       Transaction trans = null;
       Session session = HibernateUtil.getSessionFactory().openSession();
       try{
           trans = session.beginTransaction();
           session.save(regis);
           trans.commit();
       }catch(Exception e){
           System.out.println(e);
       }
   }
   
   public List<RegistrationData> retrevetabelregis()
   {
      List regis = new ArrayList();
      RegistrationData regis1 = new RegistrationData();
      Transaction trans = null;
      Session session = HibernateUtil.getSessionFactory().openSession();
      
      try{
          trans = session.beginTransaction();
          Query query = session.createQuery("from RegistrationData");
          regis = query.list();
          regis.add(regis1.getIdDaftar());
          regis.add(regis1.getNamaLengkap());
          regis.add(regis1.getNik());
          regis.add(regis1.getAlamat());
          regis.add(regis1.getNoHp());
          regis.add(regis1.getEmail());
          regis.add(regis1.getVaksin());
          regis.add(regis1.getTanggal());
          trans.commit();
      }catch (Exception e){
          System.out.println(e);
      }
      return regis;
   }
   
   public List<RegistrationData> getbyID(Integer idP)
   {
       RegistrationData rd = new RegistrationData();
       List<RegistrationData> iR = new ArrayList();
       
       Transaction trans = null;
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           Query query = session.createQuery("from RegistrationData where id_daftar= :id");
           query.setInteger("id", idP);
           rd = (RegistrationData) query.uniqueResult();
           iR = query.list();
           trans.commit();
       } catch (Exception e){
           System.out.println(e);
       }
       return iR;
       
   }
   
    public void deleteDaftar(Integer idP)
   {
       Transaction trans = null;
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           RegistrationData rd = (RegistrationData) session.load(RegistrationData.class, new Integer(idP));
           session.delete(rd);
           trans.commit();
       }catch (Exception e){
           System.out.println(e);
       }
   }
    
    public void editDaftar(RegistrationData rd)
   {
       Transaction trans = null;
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       try{
           trans = session.beginTransaction();
           session.update(rd);
           trans.commit();
       } catch (Exception e){
           System.out.println(e);
       }
   }
    
    public void resetDaftar(RegistrationData rd)
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
