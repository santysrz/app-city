/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.BeanService;

import city.DaoUsuario.DaoUser;
import city.DaoUsuario.UsuarioImplement;
import city.modelo.Activacion;
import city.modelo.Usuario;
import city.utils.GeneralActivities;
import city.utils.ObjectHand;
import city.utils.ObjectUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;


/**
 *
 * @author sfsuarez
 */
public class BeanUsuario {
      DaoUser dataUser;
      
     Gson gson ;
      
      GeneralActivities utilitarios;
      
      private String urlActivation = "http://reporteciudad.org/activation-tokener/";
      //private String urlActivation = "http://dspace.utpl.edu.ec/activation-tokener/";
      //private String urlActivation = "http://reporteciudad.org/activation-tokener/";

    public BeanUsuario() {
    }
      
    public ObjectUser loginUsuario( String data ){
         ObjectUser UnserHandle = null;
         gson = new Gson();
         try{
            
             dataUser = new UsuarioImplement();
        
             Usuario userauth = new Usuario();
              
            userauth = gson.fromJson(data, Usuario.class);
           // System.out.println("USUARIO: " + userauth.getMail() + "("+userauth.getClave()+")" );
             
             UnserHandle = dataUser.getUsuario(userauth.getMail().trim(), userauth.getClave().trim());
             
         
         }catch(Exception ex ){
             
              //Logger.getLogger(BeanUsuario.class.getName()).log(Level.WARNING, null, ex);
              UnserHandle = new ObjectUser("ERROR","Hubo un error al consultar usuario",null);
         }
         
         return UnserHandle ;
    }
    
    public ObjectUser crearUsuario( String data ){
        
        ObjectHand ObjHand = new ObjectHand();
        
        ObjectUser UnserHandle = null;
          
        gson = new Gson();
       //  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") .create();
         
         
        try{
            //System.out.println("Llegas " + data);
        
            dataUser = new UsuarioImplement();

        
        Usuario user = gson.fromJson(data, Usuario.class);

           //Controla que el mail no este registrado previamente
           
           //System.out.println("llegas al Bean: " + user.getMail());

           ObjectUser controlUserHandle = dataUser.usuarioExiste( user.getMail().trim() );
            System.out.println("verificaicon mail: " + controlUserHandle.getStatus());
           if( !controlUserHandle.getStatus().equals("OK")){

                 return controlUserHandle;
            }



           // Agrega el usuario a la BD
            ObjectUser userHandle = dataUser.creaUsuario(user);
             System.out.println("agregar BD: " + userHandle.getStatus());
            if( !userHandle.getStatus().equals("OK")){
                 return userHandle;
            }



            // Genera un token de activacion
            ObjectHand activacionHandle = dataUser.generaTokenActivacion(user);
            System.out.println("genera token: " + activacionHandle.getStatus());
           if( !activacionHandle.getStatus().equals("OK") ){

               UnserHandle = new ObjectUser(activacionHandle.getStatus(),activacionHandle.getStatus(),null);

               
               return UnserHandle;
           }


           //enviar mail de activación 


           Activacion act = (Activacion) activacionHandle.getObjeto();
           
           ObjectHand mailHandler = dataUser.enviaMail("Activación cuenta Reportcity", getMensajeActivacion(user.getNombres(),
                   act.getTokenActivacion() ), user.getMail(),act);
           System.out.println("Envia Mail (" + mailHandler.getStatus()+")");
           if( !mailHandler.getStatus().equals("OK") || mailHandler ==null ){
               
               UnserHandle = new ObjectUser(mailHandler.getStatus(),mailHandler.getStatus(),null);
               
               return UnserHandle;
           
           }
           
           if(  mailHandler ==null ){
               
               UnserHandle = new ObjectUser("ERROR","La dirección de correo no es válida",null);
               
               return UnserHandle;
           
           }
           
           
          Usuario suo =  userHandle.getUsuario();
          Set<Activacion> activacions = new HashSet<Activacion>();
          activacions.add(act);
          
          suo.setActivacions(activacions);
           ObjHand =mailHandler;
           
           UnserHandle = new ObjectUser(mailHandler.getStatus(),"Usuario creado correctamente",suo);

        }catch( Exception ex ){
            
            //Logger.getLogger(BeanUsuario.class.getName()).log(Level.WARNING, null, ex);
            UnserHandle = new ObjectUser("ERROR",ex.getLocalizedMessage(),null);
        }

           return UnserHandle;
    
    }//end crearUsuario
    
    
    
    public ObjectUser actualizaUsuario( String data ){
        ObjectUser ObjHand = new ObjectUser();
        
        //gson = new Gson();
        
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        try{
            dataUser = new UsuarioImplement();

             utilitarios = new GeneralActivities();
             
            Usuario user = new Usuario();
             // System.out.println(" LO QUE LLEGA DEL CLIENTE: \n "+ data);
            //user = getUser2Xml( data );
            
            user = gson.fromJson(data, Usuario.class);
             
            System.out.println(" User id tranformado: \n "+ user.getIdUser());
            
            ObjectUser userBD = dataUser.getUserbyID(user.getIdUser());
              System.out.println(" COnuslta Usuario: \n "+ userBD.getStatus());
            if(!userBD.getStatus().equals("OK")){
               
                 return userBD ;

             }
             
            
             Usuario userUPD =   userBD.getUsuario();

            if( !getString( user.getNombres() ).equals("N/D")){
              userUPD.setNombres(user.getNombres());
            }
            
            if( !getString( user.getApellidos() ).equals("N/D")){
              userUPD.setApellidos(user.getApellidos());
            }
             
            
            if( !getString( user.getClave() ).equals("N/D")){
              userUPD.setClave(user.getClave());
            }
            
            if( !getString( user.getUsuarioActivo() ).equals("N/D")){
              userUPD.setUsuarioActivo( user.getUsuarioActivo() );
            }
            
            if( !getString( user.getSexo() ).equals("N/D")){
              userUPD.setSexo( user.getSexo() );
            }
            
            
            if( user.getFechaNacimiento() != null ){
                userUPD.setFechaNacimiento(user.getFechaNacimiento());
            
            }
             
             
            // user.setActivacions(userUPD.getActivacions());
            // user.setIncidencias( userUPD.getIncidencias() );
             
          

           ObjectUser userUPDa = dataUser.actualizaUsuario( userUPD );
             System.out.println(" Usuario Actualizado: \n "+ userUPDa.getStatus());
            if( !userBD.getStatus().equals("OK")){
                return  userBD;
            }
            
            ObjHand = userUPDa;
            
                   
            
        }catch( Exception ex ){
            
            //Logger.getLogger(BeanUsuario.class.getName()).log(Level.WARNING, null, ex);
            //System.out.println("Error 12: " + ex.getLocalizedMessage());
            ObjHand = new ObjectUser("ERROR12",ex.getMessage(),null);
            
        }
         
    
        return ObjHand;
    }
    
    
    
    public String getString( String dato ){
        String data = "N/D";
        try{
        
            if(dato != null && !dato.isEmpty()){
                data = dato;
            }
        }catch(Exception ex){
        
            data = "N/D";
        }
    
        return data;
    }
    
    private ObjectHand procesaHandle( ObjectHand userHandle, ObjectHand activacionHandle ){
        ObjectHand handle = null;
        if(userHandle.getStatus().equals("ERROR")){
           handle= userHandle;
        }
        
        if(activacionHandle.getStatus().equals("ERROR")){
           handle= activacionHandle;
        }
        
        return handle;
    }
        
    
    private String getMensajeActivacion(String nombres, String tokener){
    
    
        
        return "Estimad@ "+nombres+": <br><br> Bienvenido a reporcity app, para completar tu "
                + "registro por favor ingresa a la siguiente URL: <a href=\""+urlActivation+"?token="+tokener+"\">"+urlActivation+"?token="+tokener+"</a> ";
    
    
    }
    
     /*   
    public Date formaterFechayHora ( Date fecha){
         Date fdh = new Date();
         SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        
          try {
              fdh = formato.parse( formato.format(fecha) );
          } catch (ParseException ex) {
              Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
          }
        
        return fdh;   
    }
    */
    
    
   public Usuario getUser2Xml(String xmlto){
   
       Usuario usaurio = new Usuario();
       
       
       
        usaurio = JAXB.unmarshal(new StringReader(xmlto), Usuario.class);
   
   
       return usaurio;
   
   }
        
    
}
