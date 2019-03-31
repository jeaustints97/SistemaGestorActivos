package SistemaGestorActivos.Logic;
// Generated 24-mar-2019 18:06:35 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Rol generated by hbm2java
 */
public class Rol  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Set<Funcionario> funcionarios = new HashSet<Funcionario>(0);

    public Rol() {
    }

	
    public Rol(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    public Rol(int id, String descripcion, Set<Funcionario> funcionarios) {
       this.id = id;
       this.descripcion = descripcion;
       this.funcionarios = funcionarios;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }
    
    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }




}

