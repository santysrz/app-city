/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.Service;

import city.BeanService.BeanCategoria;
import city.utils.ObjectCategoria;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sfsuarez
 */
@Path("categorias")
public class CategoriasService {

   ObjectCategoria response;
   
    BeanCategoria beanCat;
    
    String jsonresp = "";
    
    Gson gson =null;
    
    ObjectCategoria cata;
    
    
    
     @Context
    private UriInfo context;

    /**
     * Creates a new instance of CategoriasService
     */
    public CategoriasService() {
    }

    /**
     * Retrieves representation of an instance of city.Service.CategoriasService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
       
        gson = new Gson();
        beanCat = new BeanCategoria();
        
        try{
        
            cata = beanCat.getCategorias();
            
            jsonresp = gson.toJson(cata) ;
            
        
        }catch( Exception ex ){
                
            jsonresp = gson.toJson(  new ObjectCategoria("ERROR",ex.getLocalizedMessage(),null,null) );
                
        }    
        
        return jsonresp;
        
    }

    /**
     * PUT method for updating or creating an instance of CategoriasService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{categoriaData}")
    public String put( @PathParam("categoriaData") String jsonTxt) {
        
        gson = new Gson();
        
        try{
        
            beanCat = new BeanCategoria();

            cata = beanCat.newCategoria(jsonTxt);
            
            if( !cata.getStatus().equals("OK")){//genera un error 
              
                 return gson.toJson(cata);
            }   
            
            jsonresp = gson.toJson( cata );
            
        
        }catch( Exception ex ){
                
          jsonresp = gson.toJson(  new ObjectCategoria("ERROR",ex.getLocalizedMessage(),null,null) );
                
        }    
        
        return jsonresp;
        
        
    }
    
     /**
     * PUT method for updating or creating an instance of CategoriasService
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{categoriaData}")
    public String post( @PathParam("categoriaData") String jsonTxt) {
        
        gson = new Gson();
        
        try{
        
            beanCat = new BeanCategoria();

            cata = beanCat.updCategoria(jsonTxt);
            
            if( !cata.getStatus().equals("OK")){//genera un error 
              
                 return gson.toJson(response);
            }   
            
            jsonresp = gson.toJson( cata );
            
        
        }catch( Exception ex ){
                
          jsonresp = gson.toJson(  new ObjectCategoria("ERROR",ex.getLocalizedMessage(),null,null) );
                
        }    
        
        return jsonresp;
        
        
    }
    
    
    /*public String Object2String( Categories catego ) throws JAXBException{
        String xmlString = "N/D";
        
         
            
                JAXBContext context = JAXBContext.newInstance(Categories.class);
                Marshaller m = context.createMarshaller();

                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

                StringWriter sw = new StringWriter();
                m.marshal(catego, sw);
                xmlString = sw.toString();

                //System.out.println(xmlString);

            
        return xmlString;
    }*/
    
    
}
