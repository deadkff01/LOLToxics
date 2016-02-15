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
@Table(name="roles")
public class Role implements Serializable {
    
    @Id
    @GeneratedValue  
    @Column(name="idrole") 
    private Integer idrole;
    private String nomerole;

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getNomerole() {
        return nomerole;
    }

    public void setNomerole(String nomerole) {
        this.nomerole = nomerole;
    }

   

  
}
