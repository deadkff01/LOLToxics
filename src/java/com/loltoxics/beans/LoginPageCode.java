package com.loltoxics.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author deadkff01
 */
@ManagedBean (name ="loginPageCode")
@SessionScoped
public class LoginPageCode implements Serializable {
    private static final long serialVersionUID = -1611162265998907599L;
 
    public String getFacebookUrlAuth() {
        HttpSession session =
        (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String sessionId = session.getId();
        String appId = "1658605061028420";
        String redirectUrl = "http://localhost:8080/LOLtoxics/usuariologado/homeUsuario.xhtml";
        String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
                + appId + "&redirect_uri=" + redirectUrl
                + "&scope=email,user_birthday&state=" + sessionId;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", "logado");
        
        return returnValue;
    }
 
    
        public String encerrarSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/loginUsuario?faces-redirect=true";
     }
    
    
    
    public String getUserFromSession() {
        HttpSession session =
        (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String userName = (String) session.getAttribute("FACEBOOK_USER");
        if (userName != null) {
            return "Hello " + userName;
        }
        else {
            return "";
        }
    }
}
