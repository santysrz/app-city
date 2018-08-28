/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.DaoUsuario;

import city.modelo.Activacion;
import city.modelo.Usuario;
import city.utils.ObjectHand;
import city.utils.ObjectUser;

/**
 *
 * @author personal
 */
public interface DaoUser  {
    public ObjectUser creaUsuario( Usuario user );
    
    public ObjectHand  enviaMail(String asunto, String mensaje, String mail_destinatario, Activacion activacion );
    
    public ObjectUser getUsuario(String mail, String clave);
    
    public ObjectUser actualizaUsuario( Usuario user);
    
    public ObjectHand generaTokenActivacion( Usuario user );
    
    public ObjectUser usuarioExiste( String mail );
    
    public ObjectUser getUserbyID(Object iduser);
}
