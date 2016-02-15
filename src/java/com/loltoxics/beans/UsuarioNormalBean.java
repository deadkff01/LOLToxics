/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loltoxics.beans;

import com.loltoxics.dao.AdministradorDao;
import com.loltoxics.dao.UsuarioDao;
import com.loltoxics.model.Administrador;
import com.loltoxics.model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author deadkff01
 */
@ManagedBean
@RequestScoped
public class UsuarioNormalBean {

    /**
     * Creates a new instance of UsarioNormal
     */
    public UsuarioNormalBean() {
    }
    
    
   private Usuario user = new Usuario();

   public String mensagem = "";
   
   
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
 
   
    @Override
    public int hashCode() {
        return user.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return user.equals(o);
    }

    

     public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    public String verificarDados() throws Exception {
        UsuarioDao userDao = new UsuarioDao();
        Usuario userToVerify;
        
        String resultado = "";
        
        try{
             userToVerify = userDao.verificarDados(this.user);
             
             if(userToVerify != null) {
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", userToVerify);
                  
             resultado = "usuariologado/homeUsuario?faces-redirect=true";
                
             }else {
                 mensagem = "erro no formulario";
                 
                 resultado = "login";
             }
             
        }catch(Exception e) {
            throw e;
            
        }
        
        return resultado;
    }
    
    
    public boolean verificarSession() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != null;
    }
    
    
    public String encerrarSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
     }
    
    
}
