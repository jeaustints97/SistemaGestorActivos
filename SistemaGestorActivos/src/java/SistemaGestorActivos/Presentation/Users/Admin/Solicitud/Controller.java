package SistemaGestorActivos.Presentation.Users.Admin.Solicitud;

import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Dependencia;
import SistemaGestorActivos.Logic.Estado;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Usuario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerSolicitud", urlPatterns = {"/presentation/users/Admin/nuevaSolicitud", "/presentation/users/Admin/agregarBien", "/presentation/users/Admin/ingresarSolicitud"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/presentation/users/Admin/nuevaSolicitud")) {
            this.SolicitudVacia(request, response);
        }

        if (request.getServletPath().equals("/presentation/users/Admin/agregarBien")) {
            this.agregarBien(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Admin/ingresarSolicitud")) {
            ingresarNuevaSolicitud(request, response);
        }
    }

    protected void SolicitudVacia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud sol = new Solicitud();
        sol.setTipo("Compra");
        request.getSession().setAttribute("solicitud", sol);

        List<Bien> lb = new ArrayList<>();
        request.getSession().setAttribute("bienes", lb);

        request.getRequestDispatcher("/presentation/users/Admin/Solicitud.jsp").forward(request, response);
    }

    protected void agregarBien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud solicitud = new Solicitud();
        Bien bien = new Bien();

        this.updateEncabezado(solicitud, request);
        this.updateModelBien(bien, request);

        request.getSession().setAttribute("solicitud", solicitud);

        List<Bien> lista = (List<Bien>) request.getSession().getAttribute("bienes");
        lista.add(bien);
        request.getSession().setAttribute("bienes", lista);

        request.getRequestDispatcher("/presentation/users/Admin/Solicitud.jsp").forward(request, response);
    }

    protected void ingresarNuevaSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud model = new Solicitud();

        Solicitud solSes = (Solicitud) request.getSession().getAttribute("solicitud");
        List<Bien> bienesSes = (ArrayList<Bien>) request.getSession().getAttribute("bienes");

        //Datos de encabezado...
        model.setComprobante(solSes.getComprobante());
        model.setFecha(solSes.getFecha());
        model.setTipo(solSes.getTipo());

        //Datos derivados...
        model.setCantidad(this.calcularCantidadTotal(bienesSes));
        model.setTotal(this.calcularMontoTotal(bienesSes));

        //Asignando estado por defecto...
        model.setEstado(this.obtenerEstadoRecibida());
        model.setDependencia(this.obtenerDependencia(request));

        try {
            int id = Model.instance().getSolicitudDAO().saveAndGet(model);
            Solicitud solIngresada = Model.instance().getSolicitudDAO().findById(id);
            for (Bien b : bienesSes) {
                b.setSolicitud(solIngresada);
                Model.instance().getBienDAO().save(b);
            }
        } catch (Exception ex) {
        }

        request.getRequestDispatcher("/presentation/users/Admin/nuevaSolicitud").forward(request, response);
    }

    protected void updateModelBien(Bien bien, HttpServletRequest request) {
        bien.setDescripcion(request.getParameter("descripcion"));
        bien.setMarca(request.getParameter("marca"));
        bien.setModelo(request.getParameter("modelo"));
        bien.setPrecio(Float.valueOf(request.getParameter("precioU")));
        bien.setCantidad(Integer.valueOf(request.getParameter("cantidad")));
    }

    protected void updateEncabezado(Solicitud solicitud, HttpServletRequest request) {
        solicitud.setComprobante(request.getParameter("comprobante"));
        solicitud.setFecha(parseStringToDate(request.getParameter("fecha")));
        solicitud.setTipo(request.getParameter("tipo"));
    }

    private Date parseStringToDate(String date) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        Date trueDate = null;
        try {
            trueDate = formatter1.parse(date);
        } catch (Exception e) {
        }
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            trueDate = formatter2.parse(date);
        } catch (Exception e) {
        }
        return trueDate;
    }

    private int calcularCantidadTotal(List<Bien> lista) {
        int cantidad = 0;
        for (Bien b : lista) {
            cantidad = cantidad + b.getCantidad();
        }
        return cantidad;
    }

    private float calcularMontoTotal(List<Bien> lista) {
        float monto = 0;
        for (Bien b : lista) {
            monto = monto + b.getCantidad() * b.getPrecio();
        }
        return monto;
    }

    private Dependencia obtenerDependencia(HttpServletRequest request) {
        Usuario user = (Usuario) request.getSession().getAttribute("logged");
        return Model.instance().obtenerDependenciaPorUsuario(user.getId());
    }

    private Estado obtenerEstadoRecibida() {
        return Model.instance().obtenerEstado(1);
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
