/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.BeanService;

import city.DaoIncidencia.DaoIncidencia;
import city.DaoIncidencia.IncidenciaImplement;
import city.DaoCategoria.DaoCategoria;
import city.DaoCategoria.DaoCategoriaImplement;
import city.DaoUsuario.DaoUser;
import city.DaoUsuario.UsuarioImplement;
import city.Deserialize.servicioDeserializador;
import city.modelo.Incidencia;
import city.modelo.IncidenciaId;
import city.utils.ObjectCategoria;
import city.utils.ObjectIncidencia;
import city.utils.ObjectUser;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 *
 * @author sfsuarez
 */
public class BeanIncidencia {
   
      
     Gson gson ;
     
     DaoIncidencia daoIn;
     
     ObjectIncidencia handInc;
     
     ObjectUser objUser;
     
     JSONObject json;
     
     DaoUser dataUser;
     
     DaoCategoria dataCat;
     
     servicioDeserializador decode;

    public BeanIncidencia() {
    }

    public ObjectIncidencia incidenciasByUserId(int idUser) {
        
        try{
            
            dataUser = new UsuarioImplement();
            
            
            objUser  = dataUser.getUserbyID(idUser);
            
            System.out.println("ID que llega:"+ idUser );
            
            if( !objUser.getStatus().equals("OK")){
            
                return new ObjectIncidencia("ERROR",objUser.getDesc(),null,null);
            
            }
            System.out.println("Object User:" + objUser.getDesc() );
            
           
            
            handInc = new ObjectIncidencia("OK","OK",null,objUser.getUsuario().getIncidencias());
        
        }catch( Exception ex ){
             System.out.println(ex);
            handInc = new ObjectIncidencia("ERROR",ex.getLocalizedMessage(),null,null);
            
        }
        
        return handInc;
    }

    public ObjectIncidencia creaIncidencia(String jsonTxt,InputStream imagen ) {
        
        try{
            
           decode = new servicioDeserializador();
           
           Incidencia incidencia = decode.getIncidencia(jsonTxt, "NEW");
           
           try{
                incidencia.setMultimedia(  getByteImage( imagen ) );
           }catch(Exception ex ){
           
               return new ObjectIncidencia("ERROR","ERROR al decodificar la imagen",null,null);
           }
           
           //incidencia.setMultimedia(fileBytes);
           
           if( incidencia == null){
               return  new ObjectIncidencia("ERROR","ERROR al decodificar la incidencia",null,null);
           }
            
            //json = new JSONObject(jsonTxt);
            
            
            //JSONObject idInciob = json.getJSONObject("id");
            
            /*
             GET USUARIO DE LA BD
            */
            
            dataUser = new UsuarioImplement();
            //int idUser = idInciob.getInt("idUser");
            int idUser = incidencia.getId().getIdUser();
            
            objUser  = dataUser.getUserbyID(idUser);
            
            if( !objUser.getStatus().equals("OK")){
            
                return new ObjectIncidencia("ERROR",objUser.getDesc(),null,null);
            
            }
            
            incidencia.setUsuario(objUser.getUsuario());
            
            /*
             GET CATEGORIA DE LA BD
            */
            
             dataCat = new DaoCategoriaImplement();
            //int idcat = idInciob.getInt("categoriaIncidencia");
            int idcat = incidencia.getId().getCategoriaIncidencia();
             
           ObjectCategoria objCat = dataCat.getCategoriabyID( idcat );
           
           if( !objCat.getStatus().equals("OK")){
            
                return new ObjectIncidencia("ERROR",objCat.getDesc(),null,null);
            
            }
           
           incidencia.setCategorias( objCat.getCategoria() );
           
           
           
           /*
           
           CREA INCIDENCIA
           */
            
           
           daoIn = new IncidenciaImplement();
           
           IncidenciaId aux  = incidencia.getId();
           aux.setIdIncidencia( daoIn.generateId());
           
           incidencia.setId(aux);
           
           handInc = daoIn.creaIncidencia(incidencia);
           //System.out.println("Llegas aca: " + handInc.getDesc() );
           //handInc = new ObjectIncidencia(handInc.,"OK",null,objUser.getUsuario().getIncidencias());
        
        }catch( Exception ex ){
            System.out.println(ex);
            handInc = new ObjectIncidencia("ERROR",ex.getLocalizedMessage(),null,null);
        
        }
        
        return handInc;
    }
    
    
    public byte[] getByteImage(InputStream imagen) throws IOException{
    
    
        byte[] datos = null;
        
        datos = IOUtils.toByteArray( imagen );
        
        return datos;
    
    
    }

    public ObjectIncidencia updIncidencia(String jsonTxt) {
        
        try{
            
           decode = new servicioDeserializador();
           
           Incidencia incidencia = decode.getIncidencia(jsonTxt, "UPD");
           
           if( incidencia == null){
               return  new ObjectIncidencia("ERROR","ERROR al decodificar la incidencia",null,null);
           }
            
            
            /*
             GET USUARIO DE LA BD
            */
             
            dataUser = new UsuarioImplement();
            //int idUser = idInciob.getInt("idUser");
            int idUser = incidencia.getId().getIdUser();
            
            objUser  = dataUser.getUserbyID(idUser);
            
            if( !objUser.getStatus().equals("OK")){
            
                return new ObjectIncidencia("ERROR",objUser.getDesc(),null,null);
            
            }
            
            incidencia.setUsuario(objUser.getUsuario());
            
            /*
             GET CATEGORIA DE LA BD
            */
            
             dataCat = new DaoCategoriaImplement();
            //int idcat = idInciob.getInt("categoriaIncidencia");
            int idcat = incidencia.getId().getCategoriaIncidencia();
             
           ObjectCategoria objCat = dataCat.getCategoriabyID( idcat );
           
           if( !objCat.getStatus().equals("OK")){
            
                return new ObjectIncidencia("ERROR",objCat.getDesc(),null,null);
            
            }
           
           incidencia.setCategorias( objCat.getCategoria() );
           
           
           
           /*
           
           UPD INCIDENCIA
           */
            
           
           daoIn = new IncidenciaImplement();
           
           handInc = daoIn.updIncidencia(incidencia);
           
           //handInc = new ObjectIncidencia(handInc.,"OK",null,objUser.getUsuario().getIncidencias());
        
        }catch( Exception ex ){
            System.out.println(ex);
            handInc = new ObjectIncidencia("ERROR",ex.getLocalizedMessage(),null,null);
        
        }
        
        return handInc;
    }
    
    
    
     
     
    
}
