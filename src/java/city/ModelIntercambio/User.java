/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.ModelIntercambio;

import city.modelo.Activacion;
import city.modelo.Incidencia;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sfsuarez
 */
public class User {
     private Integer idUser;
     private Date fechaCreacion;
     private Date fechaModificacion;
     private String identificacion;
     private String nombres;
     private String apellidos;
     private Date fechaNacimiento;
     private String sexo;
     private String mail;
     private String clave;
     private String usuarioActivo;
     private Set<Activacion> activacions = new HashSet<Activacion>(0);
     private Set<Incidencia> incidencias = new HashSet<Incidencia>(0);
}
