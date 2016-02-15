
package com.loltoxics.beans;

import com.loltoxics.model.Administrador;
import com.loltoxics.dao.AdministradorDao;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author deadkff01
 */
@ManagedBean
@SessionScoped
public class AdministradorBean {
   
    Administrador admin = new Administrador();
    private AdministradorDao adminDao = new AdministradorDao();
    public String my ="";

    public String getMy() {
        return my;
    }

    public void setMy(String my) {
        this.my = my;
    }
    
    public AdministradorBean() {
    }
    
    
    
    public String addAdministrador(){
        adminDao.addAdministrador(admin);
        admin.setNome(null);
        admin.setSenha(null);
        return "gerenciarAdmin";
    }
    
 
    public String readAdmin(Administrador a) {
         this.admin = a;
         my = a.getNome();
         
        return "editarAdmin";
        //return "editarAdmin?faces-redirect=true"
    }
    
    public String editarAdmin() throws Exception {
        adminDao.editarAdministrador(this.admin);
        this.admin.setIdadministrador(null);
        this.admin.setNome(null);
        this.admin.setSenha(null);
        
        return "gerenciarAdmin?faces-redirect=true";
    }
     public String removerAdmin(Administrador admin) throws Exception {
        adminDao.removeAdministrador(admin);
        return "gerenciarAdmin";
    }
    
      public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public AdministradorDao getAdminDao() {
        return adminDao;
    }

    /**
     * Creates a new instance of AdministradoresBean
     * @param adminDao
     */
    public void setAdminDao(AdministradorDao adminDao) {
        this.adminDao = adminDao;
    }

    public List listarAdministradores(){
        return adminDao.getListaAdministradores();
    }
    

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.admin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdministradorBean other = (AdministradorBean) obj;
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        return true;
    }

    
}


