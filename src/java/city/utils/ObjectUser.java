/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.utils;

import city.modelo.Categorias;
import city.modelo.Usuario;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sfsuarez
 */
@XmlRootElement

public class ObjectUser {
   
    String status;
    String desc;
    Usuario usuario;
    
    
    
    

    public ObjectUser() {
    }
    
    
    
    
    
    

    public ObjectUser(String status, String desc, Usuario usuario) {
        this.status = status;
        this.desc = desc;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
