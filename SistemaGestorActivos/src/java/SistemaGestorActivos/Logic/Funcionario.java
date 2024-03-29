package SistemaGestorActivos.Logic;
// Generated 31-may-2019 22:51:55 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Funcionario generated by hbm2java
 */
public class Funcionario  implements java.io.Serializable {


     private String id;
     private Dependencia dependencia;
     private Puesto puesto;
     private String nombre;
     private Set<Activo> activos = new HashSet<Activo>(0);
     private Usuario usuario;
     private Set<Dependencia> dependencias = new HashSet<Dependencia>(0);
     private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);

    public Funcionario() {
    }

	
    public Funcionario(String id, Dependencia dependencia, Puesto puesto, String nombre) {
        this.id = id;
        this.dependencia = dependencia;
        this.puesto = puesto;
        this.nombre = nombre;
    }
    public Funcionario(String id, Dependencia dependencia, Puesto puesto, String nombre, Set<Activo> activos, Usuario usuario, Set<Dependencia> dependencias, Set<Solicitud> solicituds) {
       this.id = id;
       this.dependencia = dependencia;
       this.puesto = puesto;
       this.nombre = nombre;
       this.activos = activos;
       this.usuario = usuario;
       this.dependencias = dependencias;
       this.solicituds = solicituds;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Dependencia getDependencia() {
        return this.dependencia;
    }
    
    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }
    public Puesto getPuesto() {
        return this.puesto;
    }
    
    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Activo> getActivos() {
        return this.activos;
    }
    
    public void setActivos(Set<Activo> activos) {
        this.activos = activos;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Set<Dependencia> getDependencias() {
        return this.dependencias;
    }
    
    public void setDependencias(Set<Dependencia> dependencias) {
        this.dependencias = dependencias;
    }
    public Set<Solicitud> getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set<Solicitud> solicituds) {
        this.solicituds = solicituds;
    }




}


