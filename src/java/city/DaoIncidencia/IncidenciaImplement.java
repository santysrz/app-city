/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.DaoIncidencia;

import city.modelo.ActivacionId;
import city.modelo.Incidencia;
import city.modelo.IncidenciaId;
import city.persistencia.Conexion;
import city.utils.ObjectIncidencia;
import city.utils.ObjectUser;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sfsuarez
 */
public class IncidenciaImplement implements DaoIncidencia{

    private Session session = null ;
    private Transaction tx;
    ObjectIncidencia handIncidencia = null;
    
    
    
  /*
    public ObjectIncidencia getIncidenciasbyUserid(Object userid) {
        
      
        try{
            
            session = Conexion.getSessionFactory().openSession();
            //tx = session.beginTransaction();
            List<Usuario> usuarios = session.createCriteria(Usuario.class).add( Restrictions.like("idUser", userid) ).list();
            
            //List<Incidencia> incidencias = usuarios.get(0).getIncidencias().stream().collect( Collectors.toList() );
            
             handIncidencia = new ObjectIncidencia("OK","Se han encontrado "+usuarios.get(0).getIncidencias().size()+" del usuario",null,usuarios.get(0).getIncidencias());
           
              //tx.commit();

           
        }catch( Exception ex ){
              
              handIncidencia = new ObjectIncidencia("ERROR",ex.getMessage(),null,null);
         
        }finally{
         
          session.close();
        }
         
         
         return handIncidencia;
    }
*/
    
    public ObjectIncidencia creaIncidencia(Incidencia incidencia) {
         Integer info = -1;

        try{
            //incidencia.getId().setIdIncidencia(1);
            
            session = Conexion.getSessionFactory().openSession();

           tx = session.beginTransaction();
           //IncidenciaId aux  = incidencia.getId();
         //aux.setIdIncidencia( generateId());
           
           //incidencia.setId(aux);
           
           //info = (int) session.save(incidencia);
          session.save(incidencia);
           //IncidenciaId aux = incidencia.getId();
           //aux.setIdIncidencia(info);
           
           //incidencia.setId(aux);
           
           handIncidencia = new ObjectIncidencia("OK","Incidencia creada correctamente",incidencia,null);
            
            tx.commit();

           
            
        }catch( Exception ex ){
            System.out.println(ex);
           handIncidencia = new ObjectIncidencia("ERROR234",ex.getMessage(),null,null);
        }finally{
             session.close();
        }
        
        
        return handIncidencia;
    }

    public ObjectIncidencia updIncidencia(Incidencia incidencia) {

          
        try{
           
            
            session = Conexion.getSessionFactory().openSession();
            tx = session.beginTransaction();
   
          
           session.update(incidencia);

            
            handIncidencia = new ObjectIncidencia("OK","Incidencia actualizada correctamente",incidencia,null);
            
             tx.commit();

           
            
        }catch( Exception ex ){
            //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
            handIncidencia = new ObjectIncidencia("ERROR",ex.getMessage(),null,null);
        }finally{
             session.close();
        }
          
            return handIncidencia;
    }

    
    
   
    public ObjectIncidencia getIncidenciaById(IncidenciaId incidenciaId) {
       
      
         try{
            
            session = Conexion.getSessionFactory().openSession();
            //tx = session.beginTransaction();
            List<Incidencia> incidencias = session.createCriteria(Incidencia.class).add( Restrictions.like("id", incidenciaId) ).list();
            
         
             handIncidencia = new ObjectIncidencia("OK","Incidencia consultada correctamente",incidencias.get(0),null);
           
              //tx.commit();

           
         }catch( Exception ex ){
              //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
             handIncidencia = new ObjectIncidencia("ERROR",ex.getMessage(),null,null);
         
         }finally{
         
          session.close();
         }
         
         
         return handIncidencia;
    }
    
    
    public int generateId (){
        
        boolean sigue = true;
        
        int no = -1;
         session = Conexion.getSessionFactory().openSession();

           //tx = session.beginTransaction();
           
           

           
        
        while(sigue){
            
            
            no =   (int) (Math.random()*10000+1);
        
            List<IncidenciaId> acts = session.createCriteria(IncidenciaId.class).add( Restrictions.eq("idIncidencia", no) ).list();
            
            if( acts.size() < 1 ){
                sigue = false;
            }
            
        }
        
        // tx.commit();

            session.close();
          
    
        return no;
    
    }
}
