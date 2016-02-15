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
@Table(name="administradores")
public class Administrador implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idadministrador") 
    private Integer idadministrador;

    public Integer getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }
    
    private String nomeadministrador;
    private String senhaadministrador;
    
    public String getNome() {
        return nomeadministrador;
    }

    public void setNome(String nomeadministrador) {
        this.nomeadministrador = nomeadministrador;
    }

    public String getSenha() {
        return senhaadministrador;
    }

    public void setSenha(String senhaadministrador) {
        this.senhaadministrador = senhaadministrador;
    }

    
}
