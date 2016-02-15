package com.loltoxics.beans;

import com.loltoxics.dao.UsuarioDao;
import com.loltoxics.model.Usuario;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author deadkff01
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    Usuario user = new Usuario();
    private UsuarioDao userDao = new UsuarioDao();
    
    public String my ="";
    
    public UsuarioBean() {
    }
    
    
        public String getMy() {
        return my;
    }

    public void setMy(String my) {
        this.my = my;
    }
    
     
    public String addUsuario(){
        userDao.addUsuario(user);
        user.setNome(null);
        user.setSenha(null);
        user.setEmailusuario(null);
        return "index";
    }
      
    public List listarUsuarios(){
        return userDao.listar();
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public UsuarioDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UsuarioDao userDao) {
        this.userDao = userDao;
    }
      

  public String addUser(){
        userDao.addUsuario(user);
        user.setNome(null);
        user.setSenha(null);
        return "gerenciarAdmin";
    }
    
    
 
    public String readUsuario(Usuario a) {
         this.user = a;
         my = a.getNome();
         
        return "editarAdmin";
        //return "editarAdmin?faces-redirect=true"
    }
    
    public String editarAdmin() throws Exception {
        userDao.editarUsuario(this.user);
        this.user.setIdusuarios(null);
        this.user.setNome(null);
        this.user.setSenha(null);
        
        return "homeAdmin?faces-redirect=true";
    }
     public String removerAdmin(Usuario admin) throws Exception {
        userDao.removeUsuario(admin);
        return "homeAdmin";
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.user);
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
        final UsuarioBean other = (UsuarioBean) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }
    

}
