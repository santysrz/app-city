/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.Deserialize;

import city.modelo.Incidencia;
import city.modelo.IncidenciaId;
import city.modelo.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;

/**
 *
 * @author sfsuarez
 */
public class servicioDeserializador {
     JSONObject json =null;
    
    public Usuario getUsuario(String jsonUser,String modo){
    
        
        Usuario user = new Usuario();
        
        json = new JSONObject(jsonUser);
        
        if(modo.equals("UPD")){
        
            user.setIdUser( json.getInt("idUser")); 
        
        }
        
        user.setFechaCreacion( getDate2String( json.getString("fechaCreacion")));
        user.setFechaModificacion( getDate2String( json.getString("fechaModificacion")));
        user.setIdentificacion( json.getString("identificacion") );
        user.setNombres( json.getString("nombres") );
        user.setApellidos(json.getString("apellidos") );
        user.setFechaNacimiento( getDate2String( json.getString("fechaNacimiento"))  );
        user.setSexo(json.getString("sexo") );
        user.setMail(json.getString("mail") );
        user.setClave(json.getString("clave") );
        user.setNombres( json.getString("nombres") );
        user.setNombres( json.getString("nombres") );
        user.setNombres( json.getString("nombres") );
        user.setNombres( json.getString("nombres") );
        user.setNombres( json.getString("nombres") );
        user.setNombres( json.getString("nombres") );
        user.setNombres( json.getString("nombres") );
        
    
    
        return user;
    }
    
     public Incidencia getIncidencia(String jsonINC,String modo){
         Incidencia incidencia = null;
         
         try{
            
                incidencia = new Incidencia();

               json = new JSONObject( jsonINC );

               JSONObject IncidenciaIdOBJ = json.getJSONObject("id");

               IncidenciaId id = new IncidenciaId();

               if(modo.equals("UPD")){

                   id.setIdIncidencia( IncidenciaIdOBJ.getInt( "idIncidencia" ) );

               }

               id.setIdUser(  IncidenciaIdOBJ.getInt( "idUser" ) );

               id.setCategoriaIncidencia( IncidenciaIdOBJ.getInt( "categoriaIncidencia" )  );

               incidencia.setId(id);

               incidencia.setFechaCreacion( getDate2String(json.getString("fechaCreacion") ) );

               incidencia.setFechaModificacion( getDate2String(json.getString("fechaModificacion") ) );

               //Revisar
               //incidencia.setMultimedia(String2Byte( json.getString("multimedia") ));

               incidencia.setLatitud(json.getString("latitud") );

               incidencia.setLongitud(json.getString("longitud") );

               incidencia.setBarrioSector(json.getString("barrioSector") );

               incidencia.setDescripcionAdicional(json.getString("descripcionAdicional") );

               incidencia.setEstado(json.getString("estado") );

               incidencia.setEliminado(json.getString("eliminado") );
               
               incidencia.setTitulo(json.getString("titulo") );
               
               
         }catch(Exception ex ){
             System.out.println(ex);
         
             incidencia = null;
         }
         return incidencia;
     }
    
     
     public byte[] String2Byte( String byteString ){
       
         return DatatypeConverter.parseBase64Binary( byteString ); 
     
     }
    
    public Date getDate2String( String fechaTXT ){
        Date fecha = null;
        try{
        
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
           fecha = format.parse(fechaTXT);
            
        }catch( Exception ex ){
        
            System.out.println("ERROR en transformar la fecha: " + fechaTXT + " " + ex.getLocalizedMessage());
        }
        return fecha;
    }
}
