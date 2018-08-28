/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.DaoCategoria;

import city.modelo.Categorias;
import city.utils.ObjectCategoria;

/**
 *
 * @author sfsuarez
 */
public interface DaoCategoria {
    
     public ObjectCategoria consultaCategorias(  ) ;
    
    public ObjectCategoria  CreaCategoria(Categorias Categoria ) ;
    
    public ObjectCategoria ActualizaCategoria( Categorias Categoria ) ;
    
    public ObjectCategoria getCategoriabyID( int idCat ) ;
   // public void generaExceptionCategoria (String mensaje ) throws Exception;
    
}
