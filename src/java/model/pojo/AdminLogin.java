package model.pojo;
// Generated Oct 16, 2022 9:28:00 PM by Hibernate Tools 4.3.1

import DAO.DAOAdmin;
import DAO.DAOLogin;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;


/**
 * AdminLogin generated by hbm2java
 */
@ManagedBean
public class AdminLogin  implements java.io.Serializable {


     private Integer idAdmin;
     private String username;
     private String password;

     public String save_admin() {
        DAOLogin add = new DAOLogin();
        add.add_admin(this);
        return"Registrasi";
    }
     
     public String login_admin() {
        DAOLogin uDao = new DAOLogin();
        List<AdminLogin> us = uDao.getBy(username, password);

        try {
            if (us != null) {
                username = us.get(0).username;
                password = us.get(0).password;
                return "Admin";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "LoginAdmin";
    }
     
     public List<AdminLogin> getData()
    {
        DAOAdmin adm = new DAOAdmin();
        List <AdminLogin> admi = adm.retrvetabeladmin();
        return admi;
    }
     
     public String getById()
    {
        String id_admin = idAdmin.toString();
        DAOAdmin adm = new DAOAdmin();
        List<AdminLogin> admi = adm.getbyID(idAdmin);
        username = admi.get(0).getUsername();
        password = admi.get(0).getPassword ();
        return"Registrasi";
    }
    
    public String edit()
    {
        DAOAdmin adm = new DAOAdmin();
        adm.editAdmin(this);
        username = "";
        password = "";
        return "Registrasi";
    }
    
    public String delete()
    {
        String id_admin = idAdmin.toString();
        DAOAdmin adm = new DAOAdmin();
        adm.deleteAdmin(idAdmin);
        username = "";
        password = "";
        return "Registrasi";
    }
     
    public String reset_admin(){
        DAOAdmin adm = new DAOAdmin();
        adm.resetAdmin(this);
        username = "";
        password = "";
        return "Registrasi";
    }
    
    public AdminLogin() {
    }

    public AdminLogin(String username, String password) {
       this.username = username;
       this.password = password;
    }
   
    public Integer getIdAdmin() {
        return this.idAdmin;
    }
    
    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}

