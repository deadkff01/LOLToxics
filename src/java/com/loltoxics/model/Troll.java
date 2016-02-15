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
@Table(name="trolls")
public class Troll implements Serializable {
    
    @Id
    @GeneratedValue  
    @Column(name="idtroll") 
    private Integer idtroll;

    public Integer getIdtroll() {
        return idtroll;
    }

    public void setIdtroll(Integer idtroll) {
        this.idtroll = idtroll;
    }
    
    private String nometroll;
    private Integer pontuacao;

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }


    public String getNometroll() {
        return nometroll;
    }

    public void setNometroll(String nometroll) {
        this.nometroll = nometroll;
    }
    

    
}
