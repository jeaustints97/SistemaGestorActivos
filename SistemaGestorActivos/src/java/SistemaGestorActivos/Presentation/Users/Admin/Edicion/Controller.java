/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaGestorActivos.Presentation.Users.Admin.Edicion;

import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Dependencia;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Solicitud;
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

@WebServlet(name = "ControllerEdicion", urlPatterns = {"/presentation/users/Admin/Regresar", "/presentation/users/Admin/GuardarCambiosEdicion", "/presentation/users/Admin/BorrarBien", "/presentation/users/Admin/verSolicitud", "/presentation/users/Admin/Edicion", "/presentation/users/Admin/agregarBienEdicion"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/presentation/users/Admin/verSolicitud")) {
            this.cargarSolicitud(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Admin/Edicion")) {
            this.edicionSolicitud(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Admin/agregarBienEdicion")) {
            this.agregarBienEdicion(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Admin/BorrarBien")) {
            this.eliminarBien(request, response);
        }
        if (request.getServletPath().equals("/presentation/users/Admin/GuardarCambiosEdicion")) {
            this.GuardarCambios(request, response);
        }

        if (request.getServletPath().equals("/presentation/users/Admin/Regresar")) {
            this.Regresar(request, response);
        }
    }

    protected void Regresar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/presentation/users/Admin/Lista").forward(request, response);
    }

    protected void edicionSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/presentation/users/Admin/Edicion.jsp").forward(request, response);
    }

    protected void cargarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Solicitud solicitudBD = new Solicitud();
        updateModelSolicitudId(solicitudBD, request);
        Solicitud solicitudFinal = null;
        List<Bien> lista = new ArrayList<>();
        try {
            solicitudFinal = Model.instance().obtenerSolicitudCompleta(solicitudBD.getId());
            lista = Model.instance().obtenerBienesPorSolicitud(obtenerDependencia(request).getId(), solicitudFinal.getId());
        } catch (Exception ex) {
        }
        request.getSession().setAttribute("solicitud", solicitudFinal);
        request.getSession().setAttribute("bienes", lista);
        request.getRequestDispatcher("/presentation/users/Admin/Edicion").forward(request, response);
    }

    protected void updateModelSolicitudId(Solicitud model, HttpServletRequest request) {
        model.setId(Integer.parseInt(request.getParameter("idSolicitud")));
    }

    protected void eliminarBien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bien> listaAntes = (List<Bien>) request.getSession().getAttribute("bienes");
        Bien bien = listaAntes.get(Integer.parseInt(request.getParameter("consecutivo")));

        //Si el id es nulo, entonces el bien no esta en la BD, y solo se elimina en la sesion...
        if (bien.getId() == null) {
            try {
                listaAntes.remove(Integer.parseInt(request.getParameter("consecutivo")));
                request.getSession().setAttribute("bienes", listaAntes);
                request.getRequestDispatcher("/presentation/users/Admin/Edicion").forward(request, response);
            } catch (Exception e) {
            }
        } //Si el id no es nulo, entonces el bien sí está en la BD, y se debe eliminar tanto de ahi como en la sesion...
        //        if (bien.getId() != null) {
        else {
            Model.instance().getBienDAO().delete(bien);
            listaAntes.remove(Integer.parseInt(request.getParameter("consecutivo")));
            request.getSession().setAttribute("bienes", listaAntes);
            request.getRequestDispatcher("/presentation/users/Admin/Edicion").forward(request, response);
        }
    }

    protected void agregarBienEdicion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud solicitud = (Solicitud) request.getSession().getAttribute("solicitud");
        Bien bien = new Bien();

        this.updateEncabezado(solicitud, request);
        this.updateModelBien(bien, request);

        request.getSession().setAttribute("solicitud", solicitud);
        List<Bien> lista = (List<Bien>) request.getSession().getAttribute("bienes");
        lista.add(bien);
        request.getSession().setAttribute("bienes", lista);

        request.getRequestDispatcher("/presentation/users/Admin/Edicion.jsp").forward(request, response);
    }

    protected void GuardarCambios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solicitud solSes = (Solicitud) request.getSession().getAttribute("solicitud");
        List<Bien> bienesSes = (ArrayList<Bien>) request.getSession().getAttribute("bienes");

        //Datos de encabezado...
        this.updateEncabezado(solSes, request);

        //Datos derivados...
        solSes.setCantidad(this.calcularCantidadTotal(bienesSes));
        solSes.setTotal(this.calcularMontoTotal(bienesSes));

        try {
            Model.instance().getSolicitudDAO().merge(solSes);
            for (Bien b : bienesSes) {

                //Si el id es nulo, entonces el bien no esta en la BD, y se debe insertar en esta...
                if (b.getId() == null) {
                    try {
                        b.setSolicitud(solSes);
                        Model.instance().getBienDAO().save(b);
                    } catch (Exception e) {
                    }
                }
                //Si el id no es nulo, entonces el bien sí está en la BD, y no se hace nada más...
            }
        } catch (Exception ex) {
        }
        request.getRequestDispatcher("/presentation/users/Admin/Lista").forward(request, response);
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
