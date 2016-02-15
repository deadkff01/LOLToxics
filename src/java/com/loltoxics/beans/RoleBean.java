
package com.loltoxics.beans;

import com.loltoxics.model.Administrador;
import com.loltoxics.dao.RoleDao;
import com.loltoxics.model.Role;
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
public class RoleBean {
   
     Role role = new Role();
    private RoleDao roleDao = new RoleDao();
    public String my ="";

    public String getMy() {
        return my;
    }

    public void setMy(String my) {
        this.my = my;
    }
    
    public RoleBean() {
    }
    
    
    
    public String addRole(){
        roleDao.addRole(this.role);
        
        this.role.setNomerole(null);
        return "gerenciarRole";
    }
    
 
    public String readRole(Role a) {
         this.role = a;
         my = a.getNomerole();
         
        return "editarRole";
        //return "editarAdmin?faces-redirect=true"
    }
    
    public String editarRole() throws Exception {
        roleDao.editarRole(this.role);
        this.role.setIdrole(null);
        this.role.setNomerole(null);
       
        
        return "gerenciarRole?faces-redirect=true";
    }
     public String removerRole(Role role) throws Exception {
        roleDao.removeRole(role);
        return "gerenciarRole";
    }
    

    public RoleDao getRoleDao() {
        return roleDao;
    }

    /**
     * Creates a new instance of AdministradoresBean
     * @param roleDao
     * @param adminDao
     */
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List listarRoles(){
        return roleDao.getListaRoles();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.role);
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
        final RoleBean other = (RoleBean) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }


    
    
    



    

    
}


