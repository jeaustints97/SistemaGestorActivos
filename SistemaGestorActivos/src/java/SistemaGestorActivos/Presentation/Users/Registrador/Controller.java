package SistemaGestorActivos.Presentation.Users.Registrador;

import SistemaGestorActivos.Logic.Activo;
import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Categoria;
import SistemaGestorActivos.Logic.Estado;
import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Logic.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerRegistrador",
        urlPatterns = {
            "/presentation/users/Registrador/AdministrarCategorias",
            "/presentation/users/Registrador/verSolicitudes",
            "/presentation/users/Registrador/comenzar_filtrado",
            "/presentation/users/Registrador/comenzar_filtrado_activos",
            "/presentation/users/Registrador/inicioRegistro",
            "/presentation/users/Registrador/asignarActivos",
            "/presentation/users/Registrador/registrarActivo"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/presentation/users/Registrador/AdministrarCategorias")) {
            this.verListaCategorias(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Registrador/verSolicitudes")) {
            this.verListaSolicitudes(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Registrador/comenzar_filtrado")) {
            this.filtrarListaSolicitudes(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Registrador/comenzar_filtrado_activos")) {
            this.filtrarListaActivosPorId(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Registrador/inicioRegistro")) {
            this.verSolicitud(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Registrador/registrarActivo")) {
            this.registrarActivo(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Registrador/asignarActivos")) {
            this.asignarActivos(request, response);
        }

    }

    protected void verListaSolicitudes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario user = (Usuario) request.getSession().getAttribute("logged");
        request.getSession().setAttribute("listaSol", this.obtenerListaSolicitudes(user.getId()));
        request.getRequestDispatcher("/presentation/users/Registrador/Listado.jsp").forward(request, response);
    }

    protected void verListaCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/presentation/users/Registrador/Categorias.jsp").forward(request, response);
    }

    protected void asignarActivos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("listaActs", this.obtenerListaActivosSinAsignar());
        request.getSession().setAttribute("listaFunc", this.obtenerListaFuncionarios());
        request.getRequestDispatcher("/presentation/users/Registrador/AsignacionActivos.jsp").forward(request, response);
    }

    protected void verSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Solicitud solicitudBD = new Solicitud();
        updateModelSolicitudId(solicitudBD, request);
        Solicitud solicitudFinal = null;
        List<Bien> lista = new ArrayList<>();

        try {
            solicitudFinal = Model.instance().obtenerSolicitudCompleta(solicitudBD.getId());
            lista = Model.instance().obtenerBienesPorSolicitud(solicitudFinal.getId());
        } catch (Exception ex) {
        }
        request.getSession().setAttribute("solicitud", solicitudFinal);
        request.getSession().setAttribute("bienes", lista);
        request.getSession().setAttribute("listaCat", this.obtenerListaCategorias());
        request.getRequestDispatcher("/presentation/users/Registrador/Registro.jsp").forward(request, response);
    }

    protected void registrarActivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Primera parte: Obtener el id del bien y el id de la categoria seleccionada en el combobox
        int idBien = Integer.parseInt(request.getParameter("idBien"));
        int idCat = Integer.parseInt(request.getParameter(Integer.toString(idBien)));

        //Segunda parte: Obtener dichos objetos de la BD
        Categoria cat = Model.instance().getCategoriaDAO().findById(idCat);
        Bien bien = Model.instance().getBienDAO().findById(idBien);

        //Tercera parte: Obtener el consecutivo actual de la categoria e ir
        //haciendo los respectivos saves en la BD
        int consecutivoCat = cat.getConsecutivo();
        while (consecutivoCat <= bien.getCantidad()) {
            Activo activo = new Activo();
            activo.setBien(bien);
            activo.setCategoria(cat);
            activo.setConsecutivoActual(consecutivoCat);
            Model.instance().getActivoDAO().save(activo);
            consecutivoCat++;
        }

        //Cuarta parte: Actualizar el consecutivo de la categoria en la BD
        cat.setConsecutivo(consecutivoCat);
        Model.instance().getCategoriaDAO().merge(cat);

        //Ultima parte: Sacar de la tabla de bienes el bien que ya ha sido registrado como activo
        //Si la lista no tiene elementos, se redirecciona a la lista de solicitudes
        //Si no, se elimina el bien y se vuelve a cargar la página
        List<Bien> lista = new ArrayList<>();
        lista = (List<Bien>) request.getSession().getAttribute("bienes");
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == bien.getId()) {
                lista.remove(i);
            }
        }
        if (lista.isEmpty()) {
            cambiarEstadoRotulacion(request);
            request.getRequestDispatcher("/presentation/users/Registrador/verSolicitudes").forward(request, response);
        } else {
            request.getSession().setAttribute("bienes", lista);
            request.getRequestDispatcher("/presentation/users/Registrador/Registro.jsp").forward(request, response);
        }

    }

    protected void filtrarListaSolicitudes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Solicitud> model = new ArrayList<>();
        String comprobante = (String) request.getParameter("filtrado");
        Usuario user = (Usuario) request.getSession().getAttribute("logged");

        if (comprobante == null) {
            model = obtenerListaSolicitudes(user.getId());
        } else {
            model = this.obtenerSolicitudesPorComprobante(user, request.getParameter("filtrado"));
        }
        request.getSession().setAttribute("listaSol", model);
        request.getRequestDispatcher("/presentation/users/Registrador/Listado.jsp").forward(request, response);
    }

    protected void filtrarListaActivosPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Activo> model = new ArrayList<>();
        String id = (String) request.getParameter("filtrado");
        if (id == null) {
            model = obtenerListaActivosSinAsignar();
        } else {
            model = obtenerListaActivosSinAsignar(request.getParameter("filtrado"));
        }
        request.getSession().setAttribute("listaActs", model);
        request.getRequestDispatcher("/presentation/users/Registrador/AsignacionActivos.jsp").forward(request, response);
    }

    protected void updateModelSolicitudId(Solicitud model, HttpServletRequest request) {
        model.setId(Integer.parseInt(request.getParameter("idSolicitud")));
    }

    protected List<Solicitud> obtenerSolicitudesPorComprobante(Usuario user, String comprobante) {
        return Model.instance().getSolicitudDAO().getSolicitudesFiltradasPorRegistrador(user.getId(), comprobante);
    }

    protected List<Solicitud> obtenerListaSolicitudes(String idReg) {
        return Model.instance().obtenerTotalSolicitudesXRegistrador(idReg);
    }

    protected List<Categoria> obtenerListaCategorias() {
        return Model.instance().obtenerCategorias();
    }

    protected void cambiarEstadoRotulacion(HttpServletRequest request) {
        Solicitud sol = (Solicitud) request.getSession().getAttribute("solicitud");
        Estado estado = new Estado();
        estado.setId(4);
        sol.setEstado(estado);
        Model.instance().getSolicitudDAO().merge(sol);
    }

    protected List<Activo> obtenerListaActivosSinAsignar() {
        return Model.instance().getActivoDAO().getActivosSinAsignar();
    }

    protected List<Activo> obtenerListaActivosSinAsignar(String id) {
        return Model.instance().getActivoDAO().getActivosSinAsignar(id);
    }

    protected List<Funcionario> obtenerListaFuncionarios() {
        return Model.instance().getFuncionarioDAO().getAllFuncionarios();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
