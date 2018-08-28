/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.utils;

import city.modelo.Categorias;
import java.util.List;

/**
 *
 * @author sfsuarez
 */
public class ObjectCategoria {
    
    String status;
    String desc;
    Categorias categoria;
    List<Categorias> categorias;
    //Category categori;
    //List<Category> categories;

    public ObjectCategoria() {
    }

   /* public ObjectCategoria(String status, String desc, Categorias categoria, List<Categorias> categorias, Category categori, List<Category> categories) {
        this.status = status;
        this.desc = desc;
        this.categoria = categoria;
        this.categorias = categorias;
        this.categori = categori;
        this.categories = categories;
    }*/

    public ObjectCategoria(String status, String desc, Categorias categoria, List<Categorias> categorias) {
        this.status = status;
        this.desc = desc;
        this.categoria = categoria;
        this.categorias = categorias;
    }
    
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public List<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categorias> categorias) {
        this.categorias = categorias;
    }
    /*
    public Category getCategori() {
        return categori;
    }

    public void setCategori(Category categori) {
        this.categori = categori;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    */
   
    
}
