package SistemaGestorActivos.Presentation.Users.Secretaria;

import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Logic.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerSecretaria",
        urlPatterns = {"/presentation/users/Secretaria/Lista",
            "/presentation/users/Secretaria/comenzar_filtrado",
            "/presentation/users/Secretaria/verSolicitud",
            "/presentation/users/Secretaria/Valoracion",
            "/presentation/users/Secretaria/AprobarSolicitud",
            "/presentation/users/Secretaria/RechazarSolicitud"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/presentation/users/Secretaria/Lista")) {
            this.verListaCompleta(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Secretaria/comenzar_filtrado")) {
            this.filtrarListaSolicitudes(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Secretaria/verSolicitud")) {
            this.cargarSolicitud(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Secretaria/Valoracion")) {
            this.ValorarSolicitud(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Secretaria/AprobarSolicitud")) {
            this.AceptarSolicitud(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Secretaria/RechazarSolicitud")) {
            this.RechazarSolicitud(request, response);
        }

    }

    protected void cargarSolicitud(HttpServletRequest request, HttpServletResponse response)
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
        request.getRequestDispatcher("/presentation/users/Secretaria/Valoracion").forward(request, response);
    }

    protected void ValorarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/presentation/users/Secretaria/Aprobacion.jsp").forward(request, response);
    }

    protected void verListaCompleta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("listaSol", this.obtenerListaSolicitudes());
        request.getRequestDispatcher("/presentation/users/Secretaria/Listado.jsp").forward(request, response);
    }

    protected void filtrarListaSolicitudes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Solicitud> model = new ArrayList<>();
        String comprobante = (String) request.getParameter("filtrado");
        if (comprobante == null) {
            model = obtenerListaSolicitudes();
        } else {
            model = this.obtenerSolicitudesPorComprobante(request.getParameter("filtrado"));
        }
        request.getSession().setAttribute("listaSol", model);
        request.getRequestDispatcher("/presentation/users/Secretaria/Listado.jsp").forward(request, response);
    }

    protected List<Solicitud> obtenerSolicitudesPorComprobante(String comprobante) {
        return Model.instance().obtenerSolicitudesXComprobante(comprobante);
    }

    protected List<Solicitud> obtenerListaSolicitudes() {
        return Model.instance().obtenerTotalSolicitudes();
    }

    protected void updateModelSolicitudId(Solicitud model, HttpServletRequest request) {
        model.setId(Integer.parseInt(request.getParameter("idSolicitud")));
    }

    protected void AceptarSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud soli = (Solicitud) request.getSession().getAttribute("solicitud");
        Model.instance().AprobarSolicitud(soli.getId());
        request.getRequestDispatcher("/presentation/users/Secretaria/Lista").forward(request, response);
    }

    protected void RechazarSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud soli = (Solicitud) request.getSession().getAttribute("solicitud");
        Model.instance().RechazarSolicitud(soli.getId());
        request.getRequestDispatcher("/presentation/users/Secretaria/Lista").forward(request, response);
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
