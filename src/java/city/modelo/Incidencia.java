package city.modelo;
// Generated 22/07/2018 11:11:09 by Hibernate Tools 4.3.1


import com.google.gson.annotations.Expose;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Incidencia generated by hbm2java
 */
@Entity
@Table(name="incidencia"
    ,catalog="cityzen"
)
public class Incidencia  implements java.io.Serializable {

     @Expose
     private IncidenciaId id;
     
     @Expose
     private Categorias categorias;
     
     //@Expose(serialize = false, deserialize = false)
     private transient  Usuario usuario;
     
    @Expose
     private Date fechaCreacion;
    
    @Expose
     private Date fechaModificacion;
    
    @Expose
     private String titulo;
    
    @Expose
     private byte[] multimedia;
    
     @Expose
     private String latitud;
     
     @Expose
     private String longitud;
      
     @Expose
     private String barrioSector;
     
     @Expose
     private String descripcionAdicional;
     
     @Expose
     private String estado;
     
     @Expose
     private String eliminado;

    public Incidencia() {
    }

	
    public Incidencia(IncidenciaId id, Categorias categorias, Usuario usuario) {
        this.id = id;
        this.categorias = categorias;
        this.usuario = usuario;
    }
    public Incidencia(IncidenciaId id, Categorias categorias, Usuario usuario, Date fechaCreacion, Date fechaModificacion, String titulo, byte[] multimedia, String latitud, String longitud, String barrioSector, String descripcionAdicional, String estado, String eliminado) {
       this.id = id;
       this.categorias = categorias;
       this.usuario = usuario;
       this.fechaCreacion = fechaCreacion;
       this.fechaModificacion = fechaModificacion;
       this.titulo = titulo;
       this.multimedia = multimedia;
       this.latitud = latitud;
       this.longitud = longitud;
       this.barrioSector = barrioSector;
       this.descripcionAdicional = descripcionAdicional;
       this.estado = estado;
       this.eliminado = eliminado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idIncidencia", column=@Column(name="idIncidencia", nullable=false) ), 
        @AttributeOverride(name="idUser", column=@Column(name="id_user", nullable=false) ), 
        @AttributeOverride(name="categoriaIncidencia", column=@Column(name="categoria_incidencia", nullable=false) ) } )
    public IncidenciaId getId() {
        return this.id;
    }
    
    public void setId(IncidenciaId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoria_incidencia", nullable=false, insertable=false, updatable=false)
    public Categorias getCategorias() {
        return this.categorias;
    }
    
    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user", nullable=false, insertable=false, updatable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion", length=19)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_modificacion", length=19)
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    
    @Column(name="titulo", length=250)
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    @Column(name="multimedia")
    public byte[] getMultimedia() {
        return this.multimedia;
    }
    
    public void setMultimedia(byte[] multimedia) {
        this.multimedia = multimedia;
    }

    
    @Column(name="latitud", length=250)
    public String getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    
    @Column(name="longitud", length=250)
    public String getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    
    @Column(name="barrio_sector", length=250)
    public String getBarrioSector() {
        return this.barrioSector;
    }
    
    public void setBarrioSector(String barrioSector) {
        this.barrioSector = barrioSector;
    }

    
    @Column(name="descripcion_adicional", length=250)
    public String getDescripcionAdicional() {
        return this.descripcionAdicional;
    }
    
    public void setDescripcionAdicional(String descripcionAdicional) {
        this.descripcionAdicional = descripcionAdicional;
    }

    
    @Column(name="estado", length=11)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Column(name="eliminado", length=3)
    public String getEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }




}


