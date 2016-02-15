
package com.loltoxics.beans;

import com.loltoxics.model.Troll;
import com.loltoxics.dao.TrollDao;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

/**
 *
 * @author deadkff01
 */
@ManagedBean
@SessionScoped
public class TrollBean {
   
    Troll troll = new Troll();
    private TrollDao trollDao = new TrollDao();
    public String my ="";

    public String getMy() {
        return my;
    }

    public void setMy(String my) {
        this.my = my;
    }
    
    public TrollBean() {
    }
    
    
    
    public String addTroll(){
        trollDao.addTroll(troll);
        troll.setNometroll(null);
        troll.setPontuacao(null);
        return "trollsAdmin";
    }
    
 
    public String readTroll(Troll a) {
         this.troll = a;
         my = a.getNometroll();
         
        return "editarTroll";
        //return "editarAdmin?faces-redirect=true"
    }
    
    public String editarTroll() throws Exception {
        trollDao.editarTroll(this.troll);
        this.troll.setIdtroll(null);
        this.troll.setNometroll(null);
        this.troll.setPontuacao(null);
        
        return "trollsAdmin?faces-redirect=true";
    }
     public String removerTroll(Troll troll) throws Exception {
        trollDao.removeTroll(troll);
        return "trollsAdmin";
    }
    
      public Troll getTroll() {
        return troll;
    }

    public void settTroll(Troll troll) {
        this.troll = troll;
    }

    public TrollDao getTrollDao() {
        return trollDao;
    }

    /**
     * Creates a new instance of TrollesBean
     * @param trollDao
     */
    public void setTrollDao(TrollDao trollDao) {
        this.trollDao = trollDao;
    }

    public List listarTrolls(){
        return trollDao.getListaTrolls();
    }
    
    public List listar10Trolls(){
        return trollDao.getLista10Trolls();
    }

    public List listarUltimosTrolls(){
        return trollDao.getListaUltimosTrolls();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.troll);
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
        final TrollBean other = (TrollBean) obj;
        if (!Objects.equals(this.troll, other.troll)) {
            return false;
        }
        return true;
    }

    
}


