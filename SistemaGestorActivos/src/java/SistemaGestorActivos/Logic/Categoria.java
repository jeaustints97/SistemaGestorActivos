package SistemaGestorActivos.Logic;
// Generated 29-may-2019 18:47:09 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Categoria generated by hbm2java
 */
public class Categoria  implements java.io.Serializable {


     private Integer id;
     private String codigo;
     private String descripcion;
     private int consecutivo;
     private Set<Activo> activos = new HashSet<Activo>(0);

    public Categoria() {
    }

	
    public Categoria(String codigo, String descripcion, int consecutivo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.consecutivo = consecutivo;
    }
    public Categoria(String codigo, String descripcion, int consecutivo, Set<Activo> activos) {
       this.codigo = codigo;
       this.descripcion = descripcion;
       this.consecutivo = consecutivo;
       this.activos = activos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getConsecutivo() {
        return this.consecutivo;
    }
    
    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }
    public Set<Activo> getActivos() {
        return this.activos;
    }
    
    public void setActivos(Set<Activo> activos) {
        this.activos = activos;
    }




}


