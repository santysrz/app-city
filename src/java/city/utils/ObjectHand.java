/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author sfsuarez
 */
public class ObjectHand {
    @SerializedName("status")
    @Expose
    String status;
    
    @SerializedName("desc")
    @Expose
    String desc;
    
    
    Object objeto;
    

    public ObjectHand() {
    }

    public ObjectHand(String status, String desc, Object objeto) {
        this.status = status;
        this.desc = desc;
        this.objeto = objeto;
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

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    
    
}
