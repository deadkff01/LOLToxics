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
@Table(name="reportes")
public class Reporte implements Serializable {
    
    @Id
    @GeneratedValue  
    @Column(name="idreporte") 
    private Integer idreporte;

    public Integer getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(Integer idusuarios) {
        this.idreporte = idusuarios;
    }
    
    private String nometrollreportado;
    private String idusuarioreporte;


    public String getIdusuarioreporte() {
        return idusuarioreporte;
    }

    public void setIdusuarioreporte(String emailusuario) {
        this.idusuarioreporte = emailusuario;
    }
    
    
    public String getNomeTroll() {
        return nometrollreportado;
    }

    public void setNomeTroll(String senhausuario) {
        this.nometrollreportado = senhausuario;
    }

  
}
