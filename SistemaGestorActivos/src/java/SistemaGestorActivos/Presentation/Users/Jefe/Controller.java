package SistemaGestorActivos.Presentation.Users.Jefe;

import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Solicitud;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerJefe",
        urlPatterns = {"/presentation/users/Jefe/Asignacion",
            "/presentation/users/Jefe/comenzar_filtrado",
            "/presentation/users/Jefe/asignar_registrador"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/presentation/users/Jefe/Asignacion")) {
            this.verListaCompleta(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Jefe/comenzar_filtrado")) {
            this.filtrarListaSolicitudes(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Jefe/asignar_registrador")) {
            this.asignarRegistrador(request, response);
        }

    }

    protected void verListaCompleta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("listaSol", this.obtenerListaSolicitudes());
        request.getSession().setAttribute("listaReg", this.obtenerListaRegistradores());
        request.getRequestDispatcher("/presentation/users/Jefe/Asignacion.jsp").forward(request, response);
    }

    protected void asignarRegistrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idSol = Integer.parseInt(request.getParameter("idSolicitud"));
        String combobox = request.getParameter("idSolicitud");
        String idReg = (String) request.getParameter(combobox);

        Model.instance().asignacionDeRegistrador(idSol, idReg);
        request.getSession().setAttribute("listaSol", this.obtenerListaSolicitudes());
        request.getRequestDispatcher("/presentation/users/Jefe/Asignacion.jsp").forward(request, response);
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
        request.getRequestDispatcher("/presentation/users/Jefe/Asignacion.jsp").forward(request, response);
    }

    protected List<Solicitud> obtenerListaSolicitudes() {
        return Model.instance().obtenerSolicitudesPorVerificar();
    }

    protected List<Funcionario> obtenerListaRegistradores() {
        return Model.instance().obtenerRegistradores();
    }

    protected List<Solicitud> obtenerSolicitudesPorComprobante(String comprobante) {
        return Model.instance().obtenerSolicitudesXComprobanteJefe(comprobante);
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
