package com.loltoxics.dao;

import com.loltoxics.util.HibernateUtil;
import com.loltoxics.model.Usuario;
import java.sql.Connection;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author deadkff01
 */
public class UsuarioDao {
    private Session sessao;
    private Transaction trans;
    private List<Usuario> listaUsuarios;

    @SuppressWarnings("unchecked")
    public List listar(){
	sessao = HibernateUtil.getSessionFactory().openSession();
 
	try{
	    Criteria cri = sessao.createCriteria(Usuario.class);
	    return cri.list();
	}finally{
		sessao.close();
 
		}
	}
    
    public void addUsuario(Usuario user) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            Usuario newUser = new Usuario();
            newUser.setNome(user.getNome());
            newUser.setSenha(user.getSenha());
            newUser.setEmailusuario("11");
            
            sessao.save(newUser);
            trans.commit();
        
        } catch(Exception e){
        } finally {
            sessao.close();
        }
        
    }
    
    
    public void removeUsuario(Usuario user) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            sessao.delete(user);
            trans.commit();
        
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        
    }
    
      
     public void editarUsuario(Usuario admin)throws Exception{
	 
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
    
    
    public Usuario verificarDados(Usuario userToVerify) throws Exception{
       Usuario userVerified = null;
       
       try {
           sessao = HibernateUtil.getSessionFactory().openSession();
           String hql = "from Usuario where nomeusuario='"+userToVerify.getNome()
                   +"' and senhausuario='"+userToVerify.getSenha()+"'";
           Query query = sessao.createQuery(hql);
       
        if(!query.list().isEmpty()) {
            userVerified = (Usuario) query.list().get(0);
        }
       
       }catch(Exception e){
           throw e;
       }
       finally {
            sessao.close();
        }
       
        return userVerified;
    } 
    
}
