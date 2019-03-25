/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaGestorActivos.Presentation.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Usuario;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

@WebServlet(name = "presentation.login", urlPatterns = {"/presentation/prepareLogin", "/presentation/login", "/presentation/logout"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/presentation/prepareLogin")) {
            this.prepareLogin(request, response);
        }

        if (request.getServletPath().equals("/presentation/login")) {
            this.login(request, response);
        }

        if (request.getServletPath().equals("/presentation/logout")) {
            this.logout(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.verificar(request)) {
            Map<String, String> errors = this.validar(request);
            if (errors.isEmpty()) {
                Usuario model = new Usuario();
                updateModel(model, request);
                request.setAttribute("model", model);
                Usuario logged = null;
                try {
                    logged = Model.instance().getUsuarioDAO().auntenticar(model.getId(), model.getClave());
                    request.getSession(true).setAttribute("logged", logged);
                    String rol = Model.instance().getUsuarioDAO().busquedaRol(model.getId());
                    switch (rol) {
                        case "Admin":
                            request.getRequestDispatcher("/presentation/users/Admin/Admin.jsp").forward(request, response);
                            break;
                        case "JefeRH":
                            request.getRequestDispatcher("/presentation/users/JefeRH/JefeRH.jsp").forward(request, response);
                            break;
                        case "SOCCB":
                            request.getRequestDispatcher("/presentation/users/Secretaria/Secretaria.jsp").forward(request, response);
                            break;
                        case "JOCCB":
                            request.getRequestDispatcher("/presentation/users/Jefe/Jefe.jsp").forward(request, response);
                            break;
                        case "Registrador":
                            request.getRequestDispatcher("/presentation/users/Registrador/Registrador.jsp").forward(request, response);
                            break;
                    }
                } catch (Exception ex) {
                    request.getRequestDispatcher("/presentation/login/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/presentation/login/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/presentation/login/login.jsp").forward(request, response);
        }
    }

    protected void prepareLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario model = new Usuario();
        request.setAttribute("model", model);
        request.getRequestDispatcher("/presentation/login/login.jsp").forward(request, response);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("logged");
        session.invalidate();
        request.getRequestDispatcher("/presentation/index.jsp").forward(request, response);
    }

    void updateModel(Usuario model, HttpServletRequest request) {
        model.setId(request.getParameter("idUsuario"));
        model.setClave(request.getParameter("clave"));
    }

    boolean verificar(HttpServletRequest request) {
        if (request.getParameter("idUsuario") == null) {
            return false;
        }
        if (request.getParameter("clave") == null) {
            return false;
        }
        return true;
    }

    Map<String, String> validar(HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        if (request.getParameter("idUsuario").isEmpty()) {
            errores.put("idUsuario", "Id requerido");
        }

        if (request.getParameter("clave").isEmpty()) {
            errores.put("clave", "Clave requerida");
        }
        return errores;
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