package SistemaGestorActivos.Presentation.Users.Admin.Listado;

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

@WebServlet(name = "SistemaGestorActivos.Presentation.Users.Admin.Listado", urlPatterns = {"/presentation/users/Admin/comenzar_filtrado"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/presentation/users/Admin/comenzar_filtrado")) {
            this.filtrarListaSolicitudes(request, response);
        }
    }

    protected void filtrarListaSolicitudes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Solicitud> model = new ArrayList<Solicitud>();
        String comprobante = (String) request.getParameter("filtrado");
        Usuario user = (Usuario) request.getSession().getAttribute("logged");

        if (comprobante == null) {
            model = obtenerListaSolicitudes(user);
        } else {
            model = this.obtenerSolicitudesPorComprobante(user, request.getParameter("filtrado"));
        }
        request.getSession().setAttribute("listaSol", model);
        request.getRequestDispatcher("/presentation/users/Admin/Listado.jsp").forward(request, response);
    }

//    void updateModelLista(List<Solicitud> model, HttpServletRequest request) {
//        model.addAll((List<Solicitud>) request.getSession().getAttribute("listSol"));
//    }
    protected List<Solicitud> obtenerSolicitudesPorComprobante(Usuario user, String comprobante) {
        return Model.instance().getUsuarioDAO().getSolicitudesPorComprobante(user.getId(), comprobante);
    }

    protected List<Solicitud> obtenerListaSolicitudes(Usuario model) {
        return Model.instance().obtenerTotalSolicitudes(model);
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
