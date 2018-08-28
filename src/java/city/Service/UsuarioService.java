/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.Service;

import city.BeanService.BeanUsuario;
import city.Serialize.servicioSerializador;
import city.utils.ObjectUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author sfsuarez
 */
@Path("usuario")
public class UsuarioService {
    
    ObjectUser response;
   
    BeanUsuario beanUser;
    
    String jsonresp = "";
    
    Gson gson =null;
    servicioSerializador serialService = null;
    
    
   

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioService
     */
    public UsuarioService() {
    }

    /**
     * Retrieves representation of an instance of city.Service.UsuarioService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{userData}")
    public String get( @PathParam("userData") String jsonTxt ) {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        try{
        
            beanUser = new BeanUsuario();

            response = beanUser.loginUsuario(jsonTxt);
            
            /*if( !response.getStatus().equals("OK")){//genera un error 
              
                 return gson.toJson(response);
            }*/   
            
            //jsonresp = gson.toJson( response );
            
               jsonresp = getJsonString( response );
            
        
        }catch( Exception ex ){
                
          jsonresp = gson.toJson(  new ObjectUser("ERROR",ex.getLocalizedMessage(),null) );
                
        }    
        
        return jsonresp;
    }

    /**
     * PUT method for updating or creating an instance of UsuarioService
     * @param jsonTxt
     * @param content representation for the resource
     */
    @PUT
    @Path("/{userData}")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String put( @PathParam("userData") String jsonTxt ) {
        
       
        
        try{
        
            
            
            beanUser = new BeanUsuario();

            response = beanUser.crearUsuario(jsonTxt);
            System.out.println(response.getStatus());
            /*if( !response.getStatus().equals("OK")){//genera un error 
              
                 return gson.toJson(response);
            
            
            }*/   
            
         //jsonresp = gson.toJson( response );
         
        // if( jsonresp == null || jsonresp.isEmpty()){
         
             jsonresp = getJsonString( response );
         
        // }
            
            
            //jsonresp = gson.toJson( response );
            
        
        }catch( Exception ex ){
                
          jsonresp = gson.toJson(  new ObjectUser("ERROR",ex.getLocalizedMessage(),null) );
                
        }    
        
        return jsonresp;
    }
    
    /**
     * PUT method for updating or creating an instance of UsuarioService
     * @param jsonTxt
     */
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{userData}")
    public String post( @PathParam("userData") String jsonTxt ) {
        
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        try{
        
            beanUser = new BeanUsuario();

            response = beanUser.actualizaUsuario(jsonTxt);
            
            /*if( !response.getStatus().equals("OK")){//genera un error 
              
                 return gson.toJson(response);
            }*/   
            
            //jsonresp = gson.toJson( response );
            
            jsonresp = getJsonString( response );
            
        
        }catch( Exception ex ){
                
          jsonresp = gson.toJson(  new ObjectUser("ERROR",ex.getLocalizedMessage(),null) );
                
        }    
        
        return jsonresp;
    }
    
    
    public String getJsonString( ObjectUser userO ){
        serialService = new servicioSerializador();
        String res = "";
        JSONObject obj = new JSONObject();
        try{
        
        

            obj.put("status",userO.getStatus());
            obj.put("desc",userO.getDesc());
            
            if(userO.getStatus().equals("OK")){
                obj.put("usuario", serialService.serialUsuario( userO.getUsuario()  ) );
                obj.put("incidencias", serialService.serialListIndicencias( userO.getUsuario().getIncidencias() ));
                obj.put("activacions", serialService.serialListActivaciones(userO.getUsuario().getActivacions() ));
                
            }
            
            
            
            res = obj.toString();
            
            System.out.println("Trasnform: " + res);
        }catch( Exception ex  ){
        
             obj.put("status","ERROR");
            obj.put("desc",ex.getLocalizedMessage());
            
            
            res = obj.toString();
        
        }    
        
        return res;
    
    }
    
    
    
    
}
