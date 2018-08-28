/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.DaoUsuario;

import city.modelo.Activacion;
import city.modelo.ActivacionId;
import city.modelo.Usuario;
import city.persistencia.Conexion;
import city.utils.Mailer;
import city.utils.ObjectHand;
import city.utils.ObjectUser;
import java.io.StringWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author personal
 */
public class UsuarioImplement implements DaoUser {
    
      private Session session = null ;
    private Transaction tx;
    ObjectHand handler = null;

    
    public ObjectUser creaUsuario(Usuario user) {
        Integer info = -1;
        
        ObjectUser userHand = null;
        
        
        try{
            session = Conexion.getSessionFactory().openSession();

           tx = session.beginTransaction();
           
           info = (int) session.save(user);
           user.setIdUser(info);
           
           userHand = new ObjectUser("OK","Usuario Ingresado correctamente",user);
            
            tx.commit();

           
            
        }catch( Exception ex ){
            System.out.println( ex.getMessage() );
            //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
             userHand = new ObjectUser("Error",ex.getMessage(),user);
            //info = ex.getMessage();
            //info = -1;
        }finally{
             session.close();
        }
        
        
        return userHand;
    }
    
    public ObjectUser getUsuario(String mail, String clave) {
          Usuario userAutenticated = null;
          
          ObjectUser handlerU = null;
            
          List<Usuario> usuarios  = new ArrayList<Usuario>();
        try{
          
            session = Conexion.getSessionFactory().openSession();

           //tx = session.beginTransaction();
           
            usuarios = session.createCriteria(Usuario.class).setMaxResults(1)
                    .add( Restrictions.eq("mail", mail) ).add( Restrictions.eq("clave", clave) )
                    .add(Restrictions.eq("usuarioActivo", "S")).list();
           
                   
          
            Iterator<Usuario> it = usuarios.iterator();
            
            if( !it.hasNext()){//SI no hay resultados
         
                handlerU = new ObjectUser("ERROR","Mail o contraseña  incorrectas", userAutenticated);
                
                return handlerU;
            }
          
          
          while( it.hasNext()){
          
               userAutenticated = it.next();
          }

           
             
             handlerU = new ObjectUser("OK","Usuario autenticado correctamente!!", userAutenticated);
           

            
            
            
            
        }catch( Exception ex ){
            //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
            handlerU = new ObjectUser("ERROR",ex.getLocalizedMessage() ,null);
        }finally{
                session.close();
        }
          
            return handlerU;
    }//Login
    
    
    
    public ObjectUser getUsuariobyID(int idUser) {
          
          ObjectUser handlerU = null;
            
          List<Usuario> usuarios  = new ArrayList<Usuario>();
        try{
          
            session = Conexion.getSessionFactory().openSession();

           //tx = session.beginTransaction();
           
            usuarios = session.createCriteria(Usuario.class).setMaxResults(1)
                    .add( Restrictions.eq("idUser", idUser) ).list();
           
                   
          
            if(usuarios.size() == 0 ){
            
                 handlerU = new ObjectUser("ERROR","No existen usuarios con ese ID", null);
            
            }

           
             
             handlerU = new ObjectUser("OK","Usuario autenticado correctamente!!", usuarios.get(0));
           

            
            
            
            
        }catch( Exception ex ){
            Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
            handlerU = new ObjectUser("ERROR",ex.getLocalizedMessage() ,null);
        }finally{
                session.close();
        }
          
            return handlerU;
    }//Login
    
    
    public ObjectUser actualizaUsuario(Usuario user) {
        
          ObjectUser handlerU = null;
         
          
        try{
           
            
            session = Conexion.getSessionFactory().openSession();
            tx = session.beginTransaction();
            

           //tx = session.beginTransaction();
           
           //Criteria cri = session.createCriteria(Usuario.class);
           //cri.add( Restrictions.eq(propertyName, cri) );
           
          
           session.update(user);
           
           
            
            
            handlerU = new ObjectUser("OK","Usuario actualizado correctamente",user);
            
             tx.commit();

           
            
        }catch( Exception ex ){
            //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
            handlerU = new ObjectUser("OK","No se pudo actualizar el usuario",user);
        }finally{
             session.close();
        }
          
            return handlerU;
    }

   
    public ObjectUser usuarioExiste(String mail) {
       ObjectHand handler1 = new ObjectHand("OK","Iniciado",null);
       
       List<Usuario> users_bd = new ArrayList<Usuario>();
       
       ObjectUser userHandle = null;
        
        try{
            session = Conexion.getSessionFactory().openSession();
            
           users_bd = session.createCriteria(Usuario.class).add( Restrictions.eq("mail", mail) ).list();
                   //.add(Restrictions.eq("usuarioActivo", "S")).list();
           
            if( !esNuevoUser( users_bd ) ){// Si hay usuarios registrados con ese mail
           
                 userHandle = new ObjectUser("ERROR","Este mail: "+mail+" ya esta registrado en el sistema",null);
            
            }else{
               
                
                 userHandle = new ObjectUser("OK","Este mail aún no se encuentra en el sistema",null);
                 
                 
            }

             

            
            
        }catch( Exception ex ){
            //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
            
            userHandle = new ObjectUser("ERROR","Error en la consulta: " + ex.getLocalizedMessage(),null);

        }finally{
            session.close();
        
        }
      
        return userHandle;
        
    }
    
    
    public boolean esNuevoUser( List<Usuario> users_bd ){
        boolean ban = false;
        
        try{
            if( users_bd.size() > 0){
                ban = false;
            }else{
                ban = true;
            }
        }catch(Exception x){
            System.out.println("Error en la comprobacion de usuarios existentes");
            
            ban = false;
        }
        return ban;
    }

   
    public ObjectUser getUserbyID(Object iduser) {
        ObjectUser handlerU = null;
      
        List<Usuario> users_upd = new ArrayList<Usuario>();
         try{
            
            session = Conexion.getSessionFactory().openSession();
            //tx = session.beginTransaction();
             users_upd = session.createCriteria(Usuario.class).add( Restrictions.eq("idUser", iduser) ).list();
            
         
            if(users_upd.size() == 0 ){
                return new ObjectUser("ERROR","No se encontró ningún usuario con este id: "+ iduser,null);
            }
            
             handlerU = new ObjectUser("OK","User Encontrado",users_upd.get(0));
           
              //tx.commit();

           
         }catch( Exception ex ){
              //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
              System.out.println("Que error tienes: " + ex.getLocalizedMessage());
              handler = new ObjectHand("ERROR",ex.getLocalizedMessage(),null);
         
         }finally{
         
          session.close();
         }
         
          // System.out.println("Que devuelvo:" + handlerU.getUsuario().getNombres());
         return handlerU;
    }
    
    public ObjectHand generaTokenActivacion( Usuario user )  {
        
        
        ActivacionId idact = new ActivacionId(generateId(),user.getIdUser());
       // ActivacionId idact = new ActivacionId(generateId(),user.getIdUser());
        Activacion active = new Activacion(idact,user,getToken(),getFechaEc(),getFechatomorrow());
        
        Long h = Instant.now().getEpochSecond();
         Calendar tomo = Calendar.getInstance();
        tomo.add(Calendar.HOUR,24);
        int ahora = h.intValue();
        
        Long to = tomo.getTimeInMillis();
        int tomorrow = to.intValue();
        //Activacion active = new Activacion(idact,user,getToken(), ahora,tomorrow,"N/D");
        
        Date hoy = getFechaEc();
        
       
        
        ObjectUser userHand = null;
       
        try{
            session = Conexion.getSessionFactory().openSession();

           tx = session.beginTransaction();
           
            session.save( active );
            
            handler = new ObjectHand("OK","Token de Activación generado correctamente",active);
            
            
            tx.commit();

            
            
        }catch( Exception ex ){
            System.out.println("Entras todavía: "+ ex.getMessage() );
            //Logger.getLogger(UsuarioImplement.class.getName()).log(Level.WARNING, null, ex);
            //info = ex.getMessage();
             handler = new ObjectHand("ERROR",ex.getMessage(),null);
          
        }finally{
        
            session.close();
        
        }
        
    
        return handler;
    
    }
    
     public Date getFechaEc(){
    
    
        Calendar EcuadorAhora = Calendar.getInstance();
        
       
      EcuadorAhora.setTime(new Date() ); // Configuramos la fecha que se recibe
	
      EcuadorAhora.add(Calendar.HOUR, -5);  // numero de horas a añadir, o restar en caso de horas<0
        
    
        return EcuadorAhora.getTime();
    }
    
    private int generateId (){
        
        boolean sigue = true;
        
        int no = -1;
         session = Conexion.getSessionFactory().openSession();

           tx = session.beginTransaction();
           
           

           
        
        while(sigue){
            
            
            no =   (int) (Math.random()*10000+1);
        
            List<ActivacionId> acts = session.createCriteria(ActivacionId.class).add( Restrictions.eq("idActivacion", no) ).list();
            
            if( acts.size() < 1 ){
                sigue = false;
            }
            
        }
        
         tx.commit();

            session.close();
          
    
        return no;
    
    }
    private String getToken(){
    
        return RandomStringUtils.randomAlphanumeric(50);
    
    }
    
    
    private Date getFechatomorrow(){//La activacion solo puede durar 24 horas
    
            Calendar ahora = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
            
            ahora.add(Calendar.DATE, 1);
            
            return ahora.getTime();
    
    }

   
   
    public ObjectHand enviaMail(String asunto, String mensaje, String mail_destinatario, Activacion activacion) {
        Mailer mail = new Mailer();
        
        return mail.sendEmail(asunto, mensaje, mail_destinatario, activacion);
        
    }
    
    public String Object2String( Usuario obj ) throws JAXBException{
        String xmlString = "N/D";
        
         
            
                JAXBContext context = JAXBContext.newInstance(Usuario.class);
                Marshaller m = context.createMarshaller();

                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

                StringWriter sw = new StringWriter();
                m.marshal(obj, sw);
                xmlString = sw.toString();

                //System.out.println(xmlString);

            
        return xmlString;
    }

    
    

    
    
   
    
}
