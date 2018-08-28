/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.Serialize;

import city.modelo.Activacion;
import city.modelo.Incidencia;
import city.modelo.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sfsuarez
 */
public class servicioSerializador {
    
    public JSONArray serialListIndicencias( Set<Incidencia> incidencias ){
    
       JSONArray objIncidencias = null;
    int i=0;
        if( incidencias.size() != 0 && incidencias != null){
            objIncidencias = new JSONArray();
            for(Incidencia incidencia : incidencias ){
               
                JSONObject incidenObj = serialIncidencia(incidencia);

                objIncidencias.put(incidenObj);

            }
            //obj.put("incidencias",incidencias);
        }
    
    
        return objIncidencias;
    
    }
    
    
     public JSONArray serialListActivaciones( Set<Activacion> activaciones ){
    
       JSONArray objActivaciones = null;
    
        if( activaciones.size() == 0 || activaciones == null){
            objActivaciones = new JSONArray();
            for(Activacion activacion : activaciones ){

                JSONObject incidenObj = serialActivacion( activacion );

                objActivaciones.put(incidenObj);

            }
            //obj.put("incidencias",incidencias);
        }
    
    
        return objActivaciones;
    
    }
    
    public JSONObject serialUsuario( Usuario user ){
    
        JSONObject obj = new JSONObject();
        
        obj.put("idUser",user.getIdUser());
        obj.put("fechaCreacion", getformatDate( user.getFechaCreacion() ));
        obj.put("fechaModificacion", getformatDate( user.getFechaModificacion() ) );
        obj.put("identificacion",user.getIdentificacion());
        obj.put("nombres",user.getNombres());
        obj.put("apellidos",user.getApellidos());
        obj.put("fechaNacimiento",getformatDate( user.getFechaNacimiento() ) );
        obj.put("sexo",user.getSexo());
        obj.put("mail",user.getMail());
        obj.put("clave",user.getClave());
        obj.put("usuarioActivo",user.getUsuarioActivo());
        
        
        
        
        JSONArray activaciones = new JSONArray();
         
        for(Activacion activacion : user.getActivacions()){
            
            JSONObject actiObj = serialActivacion(activacion);
            
            activaciones.put(actiObj);
        
        }
        obj.put("activacions",activaciones);
    
    
        return obj;
    }

    public JSONObject serialIncidencia(Incidencia incidencia) {
        
        JSONObject objIncidencia = new JSONObject();
        
        objIncidencia.put("idIncidencia",incidencia.getId().getIdIncidencia());
        objIncidencia.put("idUser",incidencia.getUsuario().getIdUser());
        objIncidencia.put("categoriaIncidencia",incidencia.getCategorias().getIdCategorias());
        objIncidencia.put("fechaCreacion",getformatDate( incidencia.getFechaCreacion() ));
        objIncidencia.put("fechaModificacion",getformatDate( incidencia.getFechaModificacion() ) );
        objIncidencia.put("titulo",incidencia.getTitulo());
        objIncidencia.put("multimedia",bytes2String ( incidencia.getMultimedia() ));
        objIncidencia.put("latitud",incidencia.getLatitud());
        objIncidencia.put("longitud",incidencia.getLongitud());
        objIncidencia.put("barrioSector",incidencia.getBarrioSector());
        objIncidencia.put("descripcionAdicional",incidencia.getDescripcionAdicional());
        objIncidencia.put("estado",incidencia.getEstado());
        objIncidencia.put("eliminado",incidencia.getEliminado());
        
        
        
        return objIncidencia;
    }
    
    public String bytes2String(byte[] bytesArray){
    
         String mut = "";
    
         mut = DatatypeConverter.printBase64Binary(bytesArray);
         
         return mut;
    }

    public JSONObject serialActivacion(Activacion activacion) {
         JSONObject objAct = new JSONObject();
        
        objAct.put("idActivacion",activacion.getId().getIdActivacion());
        objAct.put("userId",activacion.getId().getUserId());
        objAct.put("tokenActivacion",activacion.getTokenActivacion());
        objAct.put("fechaCreacion",getformatDate( activacion.getFechaCreacion() ));
        objAct.put("fechaCaducidad",getformatDate( activacion.getFechaCaducidad() ));
        
        return objAct;
    }
    
    
    public String getformatDate( Date date ){
    
        String jsonString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
    
    
        return jsonString;
    
    }
    
}
