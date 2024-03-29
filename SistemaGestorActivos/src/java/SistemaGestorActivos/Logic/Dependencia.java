package SistemaGestorActivos.Logic;
// Generated 31-may-2019 22:51:55 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Dependencia generated by hbm2java
 */
public class Dependencia  implements java.io.Serializable {


     private int id;
     private Funcionario funcionario;
     private String nombre;
     private Set<Funcionario> funcionarios = new HashSet<Funcionario>(0);
     private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);

    public Dependencia() {
    }

	
    public Dependencia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Dependencia(int id, Funcionario funcionario, String nombre, Set<Funcionario> funcionarios, Set<Solicitud> solicituds) {
       this.id = id;
       this.funcionario = funcionario;
       this.nombre = nombre;
       this.funcionarios = funcionarios;
       this.solicituds = solicituds;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Funcionario getFuncionario() {
        return this.funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }
    
    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    public Set<Solicitud> getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set<Solicitud> solicituds) {
        this.solicituds = solicituds;
    }




}


