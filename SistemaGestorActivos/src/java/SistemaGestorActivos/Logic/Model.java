package SistemaGestorActivos.Logic;

import SistemaGestorActivos.Dao.*;

public class Model {

    private UsuarioDAO usuarioDAO;
    private FuncionarioDAO funcionarioDAO;
    private SolicitudDAO solicitudDAO;
    private LaboraDAO laboraDAO;
    private DependenciaDAO dependenciaDAO;
    private CategoriaDAO categoriaDAO;
    private BienDAO bienDAO;
    private ActivoDAO activoDAO;

    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    public Model() {
        usuarioDAO = new UsuarioDAO();
        funcionarioDAO = new FuncionarioDAO();
        solicitudDAO = new SolicitudDAO();
        laboraDAO = new LaboraDAO();
        dependenciaDAO = new DependenciaDAO();
        categoriaDAO = new CategoriaDAO();
        bienDAO = new BienDAO();
        activoDAO = new ActivoDAO();
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public SolicitudDAO getSolicitudDAO() {
        return solicitudDAO;
    }

    public void setSolicitudDAO(SolicitudDAO solicitudDAO) {
        this.solicitudDAO = solicitudDAO;
    }

    public LaboraDAO getLaboraDAO() {
        return laboraDAO;
    }

    public void setLaboraDAO(LaboraDAO laboraDAO) {
        this.laboraDAO = laboraDAO;
    }

    public DependenciaDAO getDependenciaDAO() {
        return dependenciaDAO;
    }

    public void setDependenciaDAO(DependenciaDAO dependenciaDAO) {
        this.dependenciaDAO = dependenciaDAO;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public BienDAO getBienDAO() {
        return bienDAO;
    }

    public void setBienDAO(BienDAO bienDAO) {
        this.bienDAO = bienDAO;
    }

    public ActivoDAO getActivoDAO() {
        return activoDAO;
    }

    public void setActivoDAO(ActivoDAO activoDAO) {
        this.activoDAO = activoDAO;
    }

}
