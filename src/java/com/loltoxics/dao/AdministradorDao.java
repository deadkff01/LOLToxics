package com.loltoxics.dao;

import com.loltoxics.util.HibernateUtil;
import com.loltoxics.model.Administrador;
import java.sql.Connection;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author deadkff01
 */
public class AdministradorDao {
    private Session sessao;
    private Transaction trans;
    private List<Administrador> listaAdministradores;

    public List<Administrador> getListaAdministradores() {
        
        try{
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        
        Criteria cri = sessao.createCriteria(Administrador.class);
        this.listaAdministradores = cri.list();
        } finally {
            sessao.close();
        }
        return listaAdministradores;
    }

    public void setListaAdministradores(List<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }
    
    public void addAdministrador(Administrador admin) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            Administrador newAdmin = new Administrador();
            newAdmin.setNome(admin.getNome());
            newAdmin.setSenha(admin.getSenha());
            
            sessao.save(newAdmin);
            trans.commit();
        
        } catch(Exception e){
        } finally {
            sessao.close();
        }
        
    }
    
    
    public void removeAdministrador(Administrador admin) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            sessao.delete(admin);
            trans.commit();
        
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        
    }
    
        
     public void editarAdministrador(Administrador admin)throws Exception{
	 
	try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.update(admin);
            sessao.getTransaction().commit();
	}catch(Exception e ){
            sessao.getTransaction().rollback();
            throw e;
        }
        
        finally{
	    sessao.close();
 
         }
        }
     
     
    
    
    public Administrador verificarDados(Administrador admToVerify) throws Exception{
       Administrador adminVerified = null;
       
       try {
           sessao = HibernateUtil.getSessionFactory().openSession();
           String hql = "from Administrador where nomeadministrador='"+admToVerify.getNome()
                   +"' and senhaadministrador='"+admToVerify.getSenha()+"'";
           Query query = sessao.createQuery(hql);
       
        if(!query.list().isEmpty()) {
            adminVerified = (Administrador) query.list().get(0);
        }
       
       }catch(Exception e){
           throw e;
       } 
       finally{
	    sessao.close();
 
         }
       
       
        return adminVerified;
    } 
    
    

    
}
