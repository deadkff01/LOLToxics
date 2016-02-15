package com.loltoxics.dao;

import com.loltoxics.util.HibernateUtil;

import com.loltoxics.model.Role;
import java.sql.Connection;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author deadkff01
 */
public class RoleDao {
    private Session sessao;
    private Transaction trans;
    private List<Role> listaRoles;

    public List<Role> getListaRoles() {
        
        try{
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        
        Criteria cri = sessao.createCriteria(Role.class);
        this.listaRoles = cri.list();
        } finally {
            sessao.close();
        }
        return listaRoles;
    }

    public void setListaRole(List<Role> listaRoles) {
        this.listaRoles = listaRoles;
    }
    
    public void addRole(Role role) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            Role newRole = new Role();
            newRole.setNomerole(role.getNomerole());
            
            sessao.save(newRole);
            trans.commit();
        
        } catch(Exception e){
        } finally {
            sessao.close();
        }
        
    }
    
    
    public void removeRole(Role role) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            sessao.delete(role);
            trans.commit();
        
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        
    }
    
        
     public void editarRole(Role role)throws Exception{
	 
	try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.update(role);
            sessao.getTransaction().commit();
	}catch(Exception e ){
            sessao.getTransaction().rollback();
            throw e;
        }
        
        finally{
	    sessao.close();
 
         }
        }
     
     
    
    
      

    
}
