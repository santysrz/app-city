package city.modelo;
// Generated 22/07/2018 11:11:09 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

/**
 * IncidenciaId generated by hbm2java
 */
@Embeddable
public class IncidenciaId  implements java.io.Serializable {


     private int idIncidencia;
     private int idUser;
     private int categoriaIncidencia;

    public IncidenciaId() {
    }

    public IncidenciaId(int idIncidencia, int idUser, int categoriaIncidencia) {
       this.idIncidencia = idIncidencia;
       this.idUser = idUser;
       this.categoriaIncidencia = categoriaIncidencia;
    }
   

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="idIncidencia", unique=true, nullable=false)
    public int getIdIncidencia() {
        return this.idIncidencia;
    }
    
    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }


    @Column(name="id_user", nullable=false)
    public int getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    @Column(name="categoria_incidencia", nullable=false)
    public int getCategoriaIncidencia() {
        return this.categoriaIncidencia;
    }
    
    public void setCategoriaIncidencia(int categoriaIncidencia) {
        this.categoriaIncidencia = categoriaIncidencia;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof IncidenciaId) ) return false;
		 IncidenciaId castOther = ( IncidenciaId ) other; 
         
		 return (this.getIdIncidencia()==castOther.getIdIncidencia())
 && (this.getIdUser()==castOther.getIdUser())
 && (this.getCategoriaIncidencia()==castOther.getCategoriaIncidencia());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdIncidencia();
         result = 37 * result + this.getIdUser();
         result = 37 * result + this.getCategoriaIncidencia();
         return result;
   }   


}

