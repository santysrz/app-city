/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.Service;

import city.BeanService.BeanIncidencia;
import city.Serialize.servicioSerializador;
import city.utils.ObjectIncidencia;
import city.utils.ObjectUser;
import com.google.gson.Gson;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.io.IOUtils;

import org.json.JSONObject;

/**
 *
 * @author sfsuarez
 */

@Path("incidencia")
public class IncidenciaService {
   
   
    BeanIncidencia beanIn = null;
    ObjectIncidencia response = null;
    String jsonresp = "";
    servicioSerializador serialService = null;
    Gson gson =null;
    
     @Context
    private UriInfo context;

    public IncidenciaService() {
    }
     
    
      @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idUser}")
    public String get( @PathParam("idUser") String idUser ) {
        
        
        try{
            System.out.println("LLEGA: " + idUser);
            beanIn = new BeanIncidencia();
            
            response = beanIn.incidenciasByUserId(Integer.parseInt(idUser));
            
           
            
               jsonresp = getJsonString( response );
            
        
        }catch( Exception ex ){
                
          jsonresp = gson.toJson(  new ObjectIncidencia("ERROR1",ex.getLocalizedMessage(),null,null) );
                
        }    
        
        return jsonresp;
    }

    /**
     * PUT method for updating or creating an instance of UsuarioService
     * @param jsonTxt
     * @param content representation for the resource
     */
    @PUT
    @Path("/{incidentData}")
    @Consumes(javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    
    //@Consumes(javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM)
    //@Produces(javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM)
    //public String put( byte[] fileBytes, @PathParam("incidentData") String incidentData ) {
    public String put( InputStream  imagen ,  @PathParam("incidentData") String incidentData ) {
        
       
        
        try{
        
            
            System.out.println("LLEGAS AUQUI");
            beanIn = new BeanIncidencia();
            
               byte[] datos = new byte[1024];
        
            datos = IOUtils.toByteArray( imagen );
            
            ByteArrayInputStream bis = new ByteArrayInputStream(datos);
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "jpg", new File("C:\\Users\\sfsuarez\\Documents\\Chauchas\\Avance4\\imagenServer.jpg") );
            
            //System.out.println( incidentData );
           
            response = beanIn.creaIncidencia(incidentData,  imagen);
            
          
           
         
             //jsonresp = getJsonString( response );
             
             jsonresp = incidentData ;
         
     
            
        
        }catch( Exception ex ){
              System.out.println("Que error tienes: " + ex.getLocalizedMessage());  
          jsonresp = gson.toJson(  new ObjectIncidencia("ERROR",ex.getLocalizedMessage(),null,null) );
                
        }    
        
        return jsonresp;
    }
    
    /**
     * PUT method for updating or creating an instance of UsuarioService
     * @param body
     * @param jsonTxt
     */
    @POST
    @Consumes(javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{incidentData}/")
    public String post( InputStream  imagen ,@PathParam("incidentData") String consulta ) {
        
        
        
        try{
            beanIn = new BeanIncidencia();
            

           // response = beanIn.updIncidencia( incidentData );
            
           System.out.println("--------LLegas aca -------------");
           
           System.out.println( consulta);
           
           System.out.println("LLEgaste Imagen");
           
           System.out.println("Disponible: "+imagen.available()+")");
           
            
            //jsonresp = getJsonString( response );
            
            jsonresp = consulta;
        
        }catch( Exception ex ){
                
         jsonresp = gson.toJson(  new ObjectIncidencia("ERROR",ex.getLocalizedMessage(),null,null) );
                
        }    
        
        return jsonresp;
    }
    
    public String getJsonString( ObjectIncidencia incidencia ){
        serialService = new servicioSerializador();
        String res = "";
        JSONObject obj = new JSONObject();
        try{
        
        

            obj.put("status",incidencia.getStatus());
            obj.put("desc",incidencia.getDesc());
            
            if(incidencia.getStatus().equals("OK")){
               
                if( incidencia.getIncidencia() != null){
                    obj.put("incidencia", serialService.serialIncidencia(incidencia.getIncidencia()));
                }
                
                if( incidencia.getIncidencias() != null){
                    obj.put("incidencias", serialService.serialListIndicencias( incidencia.getIncidencias() ));
                    
                    
                }
                
               
                
                
            }
            
            
            
            res = obj.toString();
            
           // System.out.println("Trasnform: " + res);
        }catch( Exception ex  ){
        
             obj.put("status","ERROR11");
            obj.put("desc",ex.getLocalizedMessage());
            
            
            res = obj.toString();
        
        }    
        
        return res;
    
    }
    
    
}
