/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loltoxics.beans;

import com.loltoxics.dao.AdministradorDao;
import com.loltoxics.model.Administrador;
import java.util.Objects;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author deadkff01
 */
@ManagedBean
@RequestScoped
public class UsuarioAdministradorBean {

   private Administrador admin = new Administrador();

   public String mensagem = "";
   
   
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
 
   
    @Override
    public int hashCode() {
        return admin.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return admin.equals(o);
    }
  
    public UsuarioAdministradorBean() {
  
    }
    
    public String getAdminNome(){
            
        if(admin.getNome() == null){
            return "peixe frito";
        }
        
        return this.admin.getNome();
    }
    
     public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
    
    public String verificarDados() throws Exception {
        AdministradorDao adminDao = new AdministradorDao();
        Administrador admToVerify;
        
        String resultado = "";
        
        try{
             admToVerify = adminDao.verificarDados(this.admin);
             
             if(admToVerify != null) {
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", admToVerify);
                  
             resultado = "toxiclogado/homeAdmin?faces-redirect=true";
                
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
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrador") != null;
    }
    
    
    public String encerrarSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
     }
    

}
