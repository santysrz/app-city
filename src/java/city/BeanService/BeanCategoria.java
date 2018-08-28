/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.BeanService;

import city.DaoCategoria.DaoCategoria;
import city.DaoCategoria.DaoCategoriaImplement;
import city.ModelIntercambio.Category;
import city.modelo.Categorias;
import city.utils.ObjectCategoria;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfsuarez
 */
public class BeanCategoria {
    
    DaoCategoria daoCat;
    ObjectCategoria ObjCat = null;
    
   ObjectCategoria response = null;
   
   Gson gson = null;
    

    public BeanCategoria() {
        
        
    }
    
    
    public ObjectCategoria getCategorias() {
        
        try{
        
            daoCat = new DaoCategoriaImplement();

            ObjectCategoria cata = daoCat.consultaCategorias();
            
            if( !cata.getStatus().equals("OK")){//genera un error 
                
                return new ObjectCategoria("ERROR",cata.getDesc(),null,null);
               
            }
            //transformar a objeto distribuible
           //response = new ObjectCategoria(cata.getStatus(),cata.getDesc(),null,null,null,getCategories(cata.getCategorias()) );
           
           response = cata;
            
        
        }catch( Exception ex ){
                
          response = new ObjectCategoria("ERROR",ex.getLocalizedMessage(),null,null );
                
        }    
    
        return response;
    }//getCategorias()
    
    public ObjectCategoria newCategoria( String jsonText )  {
       gson = new Gson();
        try{
        
            
            daoCat = new DaoCategoriaImplement();

           /* ObjectCategoria catal = getCategoria( jsonText,"new");
            
            if( !catal.getStatus().equals("OK")){
            
               return catal;
            }
            */
           
             Categorias categoria = gson.fromJson(jsonText, Categorias.class);
            
            //ObjectCategoria cata = daoCat.CreaCategoria( catal.getCategoria());
            
            ObjectCategoria cata = daoCat.CreaCategoria( categoria );
            
            if( !cata.getStatus().equals("OK")){
            
               return cata;
            }
            
            
             //transformar a objeto distribuible Categories
            //response = new ObjectCategoria(cata.getStatus(),cata.getDesc(),null,null,getCategory(cata.getCategoria()),null );
            response = cata;
        
        }catch( Exception ex ){
                
           response = new ObjectCategoria("ERROR",ex.getLocalizedMessage(),null,null );
                
        }
       
    
        return response;
    }
    
    
    
    
    public ObjectCategoria updCategoria( String jsonText ) {
        gson = new Gson();
        try{
        
            daoCat = new DaoCategoriaImplement();
            
            /*ObjectCategoria catal = getCategoria( jsonText,"upd");
            
            if( !catal.getStatus().equals("OK")){
            
               return catal;
            }*/
            
            Categorias categoria = gson.fromJson(jsonText, Categorias.class);

            //ObjectCategoria cata = daoCat.ActualizaCategoria( catal.getCategoria() );
            
            ObjectCategoria cata = daoCat.ActualizaCategoria( categoria );
            
            if( !cata.getStatus().equals("OK")){
            
               return cata;
            }
            
            
             //transformar a objeto distribuible Categories
            //response = new ObjectCategoria(cata.getStatus(),cata.getDesc(),null,null,getCategory(cata.getCategoria()),null );
            response = cata;
        
        }catch( Exception ex ){
                
           response = new ObjectCategoria("ERROR BEAN updCategoria",ex.getLocalizedMessage(),null,null );
                
        }
       
    
        return response;
    }
    
    
    
    
    
    public ObjectCategoria  getCategoriabyId(int idCat) {
    
        try{
        
            daoCat = new DaoCategoriaImplement();

            ObjectCategoria cata = daoCat.getCategoriabyID(idCat);
            
            if( !cata.getStatus().equals("OK")){
            
               return cata;
            }
            
            
             //transformar a objeto distribuible Categories
            //response = new ObjectCategoria(cata.getStatus(),cata.getDesc(),null,null,getCategory(cata.getCategoria()),null );
            response = cata;
        
        }catch( Exception ex ){
                
           response = new ObjectCategoria("ERROR BEAN updCategoria",ex.getLocalizedMessage(),null,null );
                
        }
       
    
        return response;
    
    
    }//getCategoriabyId
    
     
   public List<Category> getCategories( List<Categorias> categorias ){
   
        List<Category> categorys = new ArrayList<Category>();
        
        if(categorias != null ){
           
            for(Categorias catego : categorias ){
            
               categorys.add( getCategory(catego) );
            
            
            }
           
        }     
   
        return categorys;
   }
   
   
   
   
    public Category getCategory(Categorias categoria){
       
        Category category = null;
        try{
            if(categoria != null ){

                category = new Category(
                        categoria.getIdCategorias(),
                        categoria.getFechaCreacion(),
                        categoria.getFechaModificacion(),
                        categoria.getNombreCategoria(),
                        categoria.getDescripcion()
                );


            }
        }catch( Exception ex){
        
            category = null;
            
        }
        
        return category;
    
    }
    /*
    public ObjectCategoria getCategoria( String jsonText,String modo ){
    
        Categorias categoria = new Categorias();
        
        gson = new Gson();
        
        try{
            
            Category catego = gson.fromJson(jsonText, Category.class);
            if(modo.equals("upd")){
                categoria.setIdCategorias( catego.getIdCategorias());
            }
            
            categoria.setFechaCreacion( catego.getFechaCreacion());
            categoria.setFechaModificacion( catego.getFechaModificacion());
            categoria.setNombreCategoria( catego.getNombreCategoria());
            categoria.setDescripcion( catego.getDescripcion());
            
            response = new ObjectCategoria("OK","OK", categoria,null,null,null );
        
        }catch( Exception ex ){
        
            response = new ObjectCategoria("ERROR",ex.getLocalizedMessage(), null,null,null,null );
        
        }
        
    
        return response;
    }
    */
}
