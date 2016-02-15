package com.loltoxics.dao;

import com.loltoxics.model.Reporte;
import com.loltoxics.util.HibernateUtil;
import com.loltoxics.model.Troll;
import java.sql.Connection;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Order;

/**
 *
 * @author deadkff01
 */
public class TrollDao {
    private Session sessao;
    private Transaction trans;
    private List<Troll> listaTrolls;
    
   private List<Reporte> listaUltimosReportes;
    
    //dados do troll enviados para trollapibean
    private Integer  pontuacao;
    private String nomeTroll;
    private int posicao;
 
    public List<Troll> getListaTrolls() {
        
        try{
        sessao = HibernateUtil.getSessionFactory().openSession();
        /*
        trans = sessao.beginTransaction();
        
        Criteria cri = sessao.createCriteria(Troll.class).addOrder(Order.desc("pontuacao"));
        
        this.listaTrolls = cri.list();
        */
        

        String sql = "SELECT * FROM trolls ORDER BY pontuacao DESC";
        SQLQuery query = sessao.createSQLQuery(sql);
        query.addEntity(Troll.class);
        this.listaTrolls = query.list();
        
        
              } catch(Exception e){
        } finally {
            sessao.close();
           
        }
        
        return listaTrolls;
    }
    
    
    
    

    public int getListaTrollsIndex(String name) {
        
        List listaIndex = null;
        int aux = 0;
        
        try{  
            sessao = HibernateUtil.getSessionFactory().openSession();

            String sql = "SELECT nometroll FROM trolls ORDER BY pontuacao DESC";
            SQLQuery query = sessao.createSQLQuery(sql);

            listaIndex = query.list();

             for (int i = 0; i < listaIndex.size(); i++) {
                if (listaIndex.get(i).toString().equalsIgnoreCase(name)) {
                 aux = i;
                 break;
               }
             }
        
        //aux =  listaIndex.indexOf(name)+1;
        
              } catch(Exception e){
        } 
        
        return aux + 1;
    }
    
    public String getUniqueTroll(String nomeTroll) {
        
        try{     
            sessao = HibernateUtil.getSessionFactory().openSession();

            //
            String sqlNome = "SELECT nometroll FROM trolls WHERE nometroll='"+nomeTroll+"'";
            SQLQuery queryNome = sessao.createSQLQuery(sqlNome);

            this.setNomeTroll(queryNome.list().get(0).toString());

            String sql1 = "SELECT pontuacao FROM trolls WHERE nometroll='"+nomeTroll+"'";
            SQLQuery queryPontuacao = sessao.createSQLQuery(sql1);

            //String listaCount = Integer.toString(getListaTrollsIndex(nomeTroll));
            
            this.setPontuacao((Integer) queryPontuacao.list().get(0));
            this.setPosicao(getListaTrollsIndex(nomeTroll));
        
               } catch(Exception e){
                   return  " ";
        } finally {
            sessao.close();
            
        }
        return  getNomeTroll()+" "+getPontuacao();
        
     
    }
    
    
    
    public List<Troll> getLista10Trolls() {
        
        
       try{
        sessao = HibernateUtil.getSessionFactory().openSession();

        String sql = "SELECT * FROM trolls ORDER BY pontuacao DESC LIMIT 10";
        SQLQuery query = sessao.createSQLQuery(sql);
        query.addEntity(Troll.class);
        this.listaTrolls = query.list();
        
            } catch(Exception e){
        } finally {
            sessao.close();
        }
        
        return listaTrolls;
    }

    public List<Reporte> getListaUltimosTrolls() {
        
       try{
        sessao = HibernateUtil.getSessionFactory().openSession();

        String sql = "SELECT * FROM reportes ORDER BY reportedata DESC LIMIT 10";
        SQLQuery query = sessao.createSQLQuery(sql);
        query.addEntity(Reporte.class);
        this.listaUltimosReportes = query.list();
        
          } catch(Exception e){
        } finally {
            sessao.close();
        }
        
       
       
        return listaUltimosReportes;
    }


    public void setListaTrolls(List<Troll> listaTrolles) {
        this.listaTrolls = listaTrolles;
    }
    
    public void addTroll(Troll troll) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            Troll newTroll = new Troll();
            newTroll.setNometroll(troll.getNometroll());
            newTroll.setPontuacao(troll.getPontuacao());
            
            sessao.save(newTroll);
            trans.commit();
        
        } catch(Exception e){
        } finally {
            sessao.close();
        }
        
    }
    
    
    public void removeTroll(Troll troll) {
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            
            sessao.delete(troll);
            trans.commit();
        
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        
    }
    
        
     public void editarTroll(Troll troll)throws Exception{
	 
	try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.update(troll);
            sessao.getTransaction().commit();
	}catch(Exception e ){
            sessao.getTransaction().rollback();
            throw e;
        }
        
        finally{
	    sessao.close();
 
         }
        }
     
     
    
    
    public Troll verificarDados(Troll admToVerify) throws Exception{
       Troll trollVerified = null;
       
       try {
           sessao = HibernateUtil.getSessionFactory().openSession();
           String hql = "from Troll where nometroll='"+admToVerify.getNometroll()
                   +"' and pontuacao='"+admToVerify.getPontuacao()+"'";
           Query query = sessao.createQuery(hql);
       
        if(!query.list().isEmpty()) {
            trollVerified = (Troll) query.list().get(0);
        }
       
       }catch(Exception e){
           throw e;
       }
       finally{
	    sessao.close();
 
         }
       
       
        return trollVerified;
    } 
    
    
    
    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNomeTroll() {
        return nomeTroll;
    }

    public void setNomeTroll(String nomeTroll) {
        this.nomeTroll = nomeTroll;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    
    
   
    
    public void reportar(String nometroll, String idUsuario) {
        
      try{
          
            sessao = HibernateUtil.getSessionFactory().openSession();

            String sql = "SELECT pontuacao FROM trolls WHERE nometroll='"+nometroll+"'";
            SQLQuery queryPontuacao = sessao.createSQLQuery(sql);
            
            if(!queryPontuacao.list().isEmpty()){
                
                
                String checkReport = "SELECT * FROM reportes WHERE nometrollreportado='"+nometroll+"' and idusuarioreporte='"+idUsuario+"'";
                SQLQuery queryReport = sessao.createSQLQuery(checkReport);
                
                 if(queryReport.list().isEmpty()) {
                
                    Reporte report = new Reporte(); 
                    report.setIdusuarioreporte(idUsuario);
                    report.setNomeTroll(nometroll);

                    sessao.save(report);

                    //metodo pra adicionar mais um no report
                    int pontuacaoTroll = Integer.parseInt(queryPontuacao.list().get(0).toString());
                    pontuacaoTroll += 1;

                    String q = "update Troll set pontuacao='"+pontuacaoTroll+"' WHERE nometroll='"+nometroll+"'";

                    Query query = sessao.createQuery(q);

                    sessao.beginTransaction();
                    query.executeUpdate();  
                    sessao.getTransaction().commit();

                 }
                    
             } else {
            
                
                Reporte report = new Reporte(); 
                report.setIdusuarioreporte(idUsuario);
                report.setNomeTroll(nometroll);

                sessao.save(report);
            
                //caso o troll nunca foi reportado
                sessao.beginTransaction();

                Troll newTroll = new Troll();
                newTroll.setNometroll(nometroll);
                newTroll.setPontuacao(1);

                sessao.save(newTroll);
                sessao.getTransaction().commit();
                
                
            } 
            
	}catch(Exception e ){
            sessao.getTransaction().rollback();
            throw e;
        }
        
        finally{
	    sessao.close();
 
         }
        return;
    }

    
}
