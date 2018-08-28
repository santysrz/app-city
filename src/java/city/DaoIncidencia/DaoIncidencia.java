/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.DaoIncidencia;

import city.modelo.Incidencia;
import city.modelo.IncidenciaId;
import city.utils.ObjectIncidencia;

/**
 *
 * @author sfsuarez
 */
public interface DaoIncidencia {
    
    
    public ObjectIncidencia creaIncidencia( Incidencia incidencia );
    public ObjectIncidencia updIncidencia( Incidencia incidencia );
    public ObjectIncidencia getIncidenciaById( IncidenciaId incidenciaId );
    
    public int generateId();
}
