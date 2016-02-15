
package com.loltoxics.beans;

import com.loltoxics.model.Troll;
import com.loltoxics.dao.TrollDao;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.context.FacesContext;

import javax.json.*;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author deadkff01
 */
@ManagedBean
@SessionScoped
public class TrollAPIBean {
   
   public String nomeTrollPesquisa;
   
   public String resultadoPesquisa;
   
   //retorno dos dados do troll 
   public String nomeTrollPesquisado;
   
   public Integer pontuacaoTrollPesquisado;
   
   public int posicaoTrollPesquisado;
   
   public String idUsuario = "";
   
   
    public String sendMyDatabase(String playerName){
        TrollDao trollDao = new TrollDao(); 
       
        String result =  trollDao.getUniqueTroll(playerName);
        
        this.nomeTrollPesquisado = trollDao.getNomeTroll();
        this.pontuacaoTrollPesquisado = trollDao.getPontuacao();
        this.posicaoTrollPesquisado = trollDao.getPosicao();
        
        return result;
    }
    
    
    public String sendAPIRequest(String playerName) throws Exception  {
        /*
         String url = "https://br.api.pvp.net/api/lol/br/v1.4/summoner/by-name/deadknopf?api_key=9890d844-3ad2-4069-8a21-5221a3722277";
		          
         URL objUrl = new URL(url);
         HttpURLConnection connection = (HttpURLConnection) objUrl.openConnection();
         
         connection.setRequestMethod("GET");
         connection.setInstanceFollowRedirects(false);
              
         StringBuilder response = new StringBuilder();
         
         int responseCode = connection.getResponseCode();
            
           if (responseCode != 200) {
                return "Jogador Inexistente!";
            }
         */
         
        String response = null;
        
        URL url = new URL("https://br.api.pvp.net/api/lol/br/v1.4/summoner/by-name/"+playerName+"?api_key=9890d844-3ad2-4069-8a21-5221a3722277");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         
         connection.setRequestMethod("GET");
         connection.setInstanceFollowRedirects(false);
         
            if (connection.getResponseCode() != 200) {
                return "Jogador Inexistente!";
            }
         
        
            try (InputStream is = url.openStream();
                 JsonParser parser = Json.createParser(is)) {
                while (parser.hasNext()) {
                   Event e = parser.next();
                   if (e == Event.KEY_NAME) {
                       switch (parser.getString()) {
                         case "name":
                                parser.next();                            
                                response = parser.getString();
                              break;
                         case "message":
                             parser.next();
                             response = parser.getString();
                             
                            break;
                      }
                  }
               }
           }
 	
        return response;
    }

    public String getNomeTrollPesquisa() {
        return nomeTrollPesquisa;
    }

    public void setNomeTrollPesquisa(String nomeTrollPesquisa) {
        this.nomeTrollPesquisa = nomeTrollPesquisa;
    }

    public String getresultadoPesquisa() {
        return resultadoPesquisa;
    }

    public void setresultadoPesquisa(String nomeResultadoPesquisa) {
        this.resultadoPesquisa = nomeResultadoPesquisa;
    }

    public String getNomeTrollPesquisado() {
        return nomeTrollPesquisado;
    }

    public void setNomeTrollPesquisado(String nomeTrollPesquisado) {
        this.nomeTrollPesquisado = nomeTrollPesquisado;
    }

    public Integer getPontuacaoTrollPesquisado() {
        return pontuacaoTrollPesquisado;
    }

    public void setPontuacaoTrollPesquisado(Integer pontuacaoTrollPesquisado) {
        this.pontuacaoTrollPesquisado = pontuacaoTrollPesquisado;
    }

    public int getPosicaoTrollPesquisado() {
        return posicaoTrollPesquisado;
    }

    public void setPosicaoTrollPesquisado(int posicaoTrollPesquisado) {
        this.posicaoTrollPesquisado = posicaoTrollPesquisado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


    
    
  
    public void resultadoPesquisa() throws IOException, Exception {
       
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        
        resultadoPesquisa = this.sendMyDatabase(this.nomeTrollPesquisa);
        
        if(resultadoPesquisa == null || " ".equals(resultadoPesquisa)){

            this.nomeTrollPesquisado = sendAPIRequest(this.nomeTrollPesquisa);
            this.pontuacaoTrollPesquisado = 0;
            
        }
        
       
        response.sendRedirect("resultadoPesquisa.xhtml");
    
    }
    
    
     public void reportarTroll() throws IOException, Exception {
       
     
         TrollDao trollDao = new TrollDao(); 
         
         trollDao.reportar(this.nomeTrollPesquisado, this.getIdUsuario());
        
         resultadoPesquisa();
        
    
    }

    
}


