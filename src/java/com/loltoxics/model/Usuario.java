package com.loltoxics.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

/**
 *
 * @author deadkff01
 */

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue  
    @Column(name="idusuarios") 
    private Integer idusuarios;

    public Integer getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(Integer idusuarios) {
        this.idusuarios = idusuarios;
    }
    
    private String nomeusuario;
    private String senhausuario;
    private String emailusuario;

    public String getEmailusuario() {
        return emailusuario;
    }

    public void setEmailusuario(String emailusuario) {
        this.emailusuario = emailusuario;
    }
    
    
    public String getNome() {
        return nomeusuario;
    }

    public void setNome(String senhausuario) {
        this.nomeusuario = senhausuario;
    }

    public String getSenha() {
        return senhausuario;
    }

    public void setSenha(String senhausuario) {
        this.senhausuario = senhausuario;
    }

    
}
