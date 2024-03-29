package SistemaGestorActivos.Logic;
// Generated 31-may-2019 22:51:55 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Solicitud generated by hbm2java
 */
public class Solicitud  implements java.io.Serializable {


     private Integer id;
     private Dependencia dependencia;
     private Estado estado;
     private Funcionario funcionario;
     private String comprobante;
     private Date fecha;
     private String tipo;
     private int cantidad;
     private float total;
     private Set<Bien> biens = new HashSet<Bien>(0);
     private Erazon erazon;

    public Solicitud() {
    }

	
    public Solicitud(Dependencia dependencia, Estado estado, String comprobante, Date fecha, String tipo, int cantidad, float total) {
        this.dependencia = dependencia;
        this.estado = estado;
        this.comprobante = comprobante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.total = total;
    }
    public Solicitud(Dependencia dependencia, Estado estado, Funcionario funcionario, String comprobante, Date fecha, String tipo, int cantidad, float total, Set<Bien> biens, Erazon erazon) {
       this.dependencia = dependencia;
       this.estado = estado;
       this.funcionario = funcionario;
       this.comprobante = comprobante;
       this.fecha = fecha;
       this.tipo = tipo;
       this.cantidad = cantidad;
       this.total = total;
       this.biens = biens;
       this.erazon = erazon;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Dependencia getDependencia() {
        return this.dependencia;
    }
    
    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }
    public Estado getEstado() {
        return this.estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public Funcionario getFuncionario() {
        return this.funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public String getComprobante() {
        return this.comprobante;
    }
    
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public float getTotal() {
        return this.total;
    }
    
    public void setTotal(float total) {
        this.total = total;
    }
    public Set<Bien> getBiens() {
        return this.biens;
    }
    
    public void setBiens(Set<Bien> biens) {
        this.biens = biens;
    }
    public Erazon getErazon() {
        return this.erazon;
    }
    
    public void setErazon(Erazon erazon) {
        this.erazon = erazon;
    }




}


