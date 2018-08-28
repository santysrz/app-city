/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.DaoCategoria;

import city.modelo.Categorias;
import city.persistencia.Conexion;
import city.utils.ObjectCategoria;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sfsuarez
 */
public class DaoCategoriaImplement  implements DaoCategoria  {
    private Session session = null ;
    private Transaction tx;
    
    ObjectCategoria ObjCat = null;
    
  
   
    public ObjectCategoria consultaCategorias() {
        
        List<Categorias> categorias = new ArrayList<Categorias>();
        
        try{
            session = Conexion.getSessionFactory().openSession();

           
           categorias = session.createCriteria(Categorias.class).list();
           
           ObjCat = new ObjectCategoria("OK","OK",null,categorias);
           
            
        }catch( Exception ex ){
           
             //generaExceptionCategoria( ex.getLocalizedMessage() );
             ObjCat = new ObjectCategoria("ERROR consultaCategorias ",ex.getLocalizedMessage(),null,null);
             //System.out.println("Error DaoCataImpl: "+ex.getLocalizedMessage());
            
        }finally{
             session.close();
        }
        
        
        return ObjCat;
    }

    
    public ObjectCategoria CreaCategoria(Categorias categoria)  {
       
        //Categorias info = new Categorias();
        int noCategoria=-1;
        
        try{
            
           session = Conexion.getSessionFactory().openSession();

           tx = session.beginTransaction();
           
           noCategoria = (int) session.save(categoria);
           
           ObjectCategoria info = getCategoriabyID( noCategoria );
           
           if(!info.equals("OK")){
              return info;
           }
           
           ObjCat = new ObjectCategoria("OK","OK",info.getCategoria(),null);
            
            tx.commit();

           
            
        }catch( Exception ex ){
            
             //generaExceptionCategoria( ex.getLocalizedMessage() );
          ObjCat = new ObjectCategoria("ERROR CreaCategoria ",ex.getLocalizedMessage(),null,null);
          
        }finally{
             session.close();
        }
        
        return ObjCat;
    }

    
    public ObjectCategoria ActualizaCategoria(Categorias Categoria)  {
          
      
        
        try{
            session = Conexion.getSessionFactory().openSession();

           tx = session.beginTransaction();
           
           session.update(Categoria);
           
           ObjectCategoria info = getCategoriabyID( Categoria.getIdCategorias() );
           
           if(!info.equals("OK")){
              return info;
           }
           
           ObjCat = new ObjectCategoria("OK","OK",Categoria,null);
           
            tx.commit();

           
            
        }catch( Exception ex ){
            // generaExceptionCategoria( ex.getLocalizedMessage() );
            
          ObjCat = new ObjectCategoria("ERROR UPD CATEGORIA",ex.getLocalizedMessage(),null,null);
        }finally{
             session.close();
        }
        
        return ObjCat;
    }

    
    public ObjectCategoria getCategoriabyID(int idCat) {
        
        
        List<Categorias> categorias = new ArrayList<Categorias>();
        Categorias categoria = null;
        try{
            session = Conexion.getSessionFactory().openSession();

           
           categorias = session.createCriteria(Categorias.class).add( Restrictions.eq("idCategorias", idCat) ).list();
           
           categoria = categorias.get(0);
           
            ObjCat = new ObjectCategoria("OK","OK",categoria,null);
           
           
            
        }catch( Exception ex ){
           
            //generaExceptionCategoria( ex.getLocalizedMessage() );
            
            
            ObjCat = new ObjectCategoria("ERROR getCategoriabyID ",ex.getLocalizedMessage(),null,null);
          
            
        }finally{
             session.close();
        }
        
        return ObjCat;
    }
    /*
    public void generaExceptionCategoria( String mensaje ) throws Exception{
    
        try {
            
            throw new categoriaException("Categoria Error: "+ mensaje);
            
        } catch (Exception ex1) {
            //Logger.getLogger(DaoCategoriaImplement.class.getName()).log(Level.SEVERE, null, ex1);

            System.out.println("Error en generar la excetpion: "+ex1.getLocalizedMessage());
        } 
    }//generaExceptionCategoria
*/
    
}
