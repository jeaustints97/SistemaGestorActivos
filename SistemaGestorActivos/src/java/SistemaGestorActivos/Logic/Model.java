package SistemaGestorActivos.Logic;

import SistemaGestorActivos.Dao.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private ActivoDAO activoDAO;
    private BienDAO bienDAO;
    private CategoriaDAO categoriaDAO;
    private DependenciaDAO dependenciaDAO;
    private ERazonDAO eRazonDAO;
    private EstadoDAO estadoDAO;
    private FuncionarioDAO funcionarioDAO;
    private PuestoDAO puestoDAO;
    private RolDAO rolDAO;
    private SolicitudDAO solicitudDAO;
    private UsuarioDAO usuarioDAO;

    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    public Model() {
        activoDAO = new ActivoDAO();
        bienDAO = new BienDAO();
        categoriaDAO = new CategoriaDAO();
        dependenciaDAO = new DependenciaDAO();
        eRazonDAO = new ERazonDAO();
        estadoDAO = new EstadoDAO();
        funcionarioDAO = new FuncionarioDAO();
        puestoDAO = new PuestoDAO();
        rolDAO = new RolDAO();
        solicitudDAO = new SolicitudDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public ActivoDAO getActivoDAO() {
        return activoDAO;
    }

    public BienDAO getBienDAO() {
        return bienDAO;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public DependenciaDAO getDependenciaDAO() {
        return dependenciaDAO;
    }

    public ERazonDAO geteRazonDAO() {
        return eRazonDAO;
    }

    public EstadoDAO getEstadoDAO() {
        return estadoDAO;
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public PuestoDAO getPuestoDAO() {
        return puestoDAO;
    }

    public RolDAO getRolDAO() {
        return rolDAO;
    }

    public SolicitudDAO getSolicitudDAO() {
        return solicitudDAO;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public String obtenerFuncionarioActual(Usuario user) {
        String f = "";
        f = this.getUsuarioDAO().busquedaNombre(user.getId());
        return f;
    }

    public String obtenerRolActual(Usuario user) {
        String rol = "";
        rol = this.getUsuarioDAO().busquedaRol(user.getId());
        return rol;
    }

    public String obtenerDependenciaActual(Usuario user) {
        String dep = "";
        dep = this.getUsuarioDAO().busquedaDependencia(user.getId());
        return dep;
    }

    public List<Solicitud> obtenerTotalSolicitudes(Usuario user) {
        List<Solicitud> sols = new ArrayList<>();
        sols = this.getUsuarioDAO().getSolicitudes(user.getId());
        return sols;
    }
    
    public List<Solicitud> obtenerSolicitudesXComprobante(Usuario user,String comprobante) {
        List<Solicitud> sols = new ArrayList<>();
        sols = this.getUsuarioDAO().getSolicitudesPorComprobante(user.getId(), comprobante);
        return sols;
    }

}
