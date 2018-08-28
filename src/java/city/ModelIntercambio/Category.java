/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.ModelIntercambio;

import java.util.Date;

/**
 *
 * @author sfsuarez
 */
public class Category {
    
     private Integer idCategorias;
     private Date fechaCreacion;
     private Date fechaModificacion;
     private String nombreCategoria;
     private String descripcion;
    

    public Category () {
    }

    public Category(Integer idCategorias, Date fechaCreacion, Date fechaModificacion, String nombreCategoria, String descripcion) {
        this.idCategorias = idCategorias;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
    }

   
 
    public Integer getIdCategorias() {
        return this.idCategorias;
    }
    
    public void setIdCategorias(Integer idCategorias) {
        this.idCategorias = idCategorias;
    }

  
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

   
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

 
    public String getNombreCategoria() {
        return this.nombreCategoria;
    }
    
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    
   
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
