/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author liliana
 */
public class GeneralActivities {
    
    
     public Date getFechaEc(){
    
    
        Calendar EcuadorAhora = Calendar.getInstance();
        
       
      EcuadorAhora.setTime(new Date() ); // Configuramos la fecha que se recibe
	
      EcuadorAhora.add(Calendar.HOUR, -5);  // numero de horas a añadir, o restar en caso de horas<0
    
        return EcuadorAhora.getTime();
    }
    
    public Date string2Date( String txtfecha) {
        Date fNac = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
          try {
              fNac = formato.parse(txtfecha);
          } catch (ParseException ex) {
              //Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
              fNac = new Date();
          }
    
        return fNac;
    }
    
    
}
