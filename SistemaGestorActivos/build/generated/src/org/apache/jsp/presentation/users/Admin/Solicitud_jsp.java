package org.apache.jsp.presentation.users.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Usuario;

public final class Solicitud_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    private Funcionario obtenerFuncionario(Usuario model) {
        Funcionario f = new Funcionario();
        f.setNombre(Model.instance().getUsuarioDAO().busquedaNombre(model.getId()));
        return f;
    }

    private String obtenerRol(Usuario model) {
        String rol = "";
        rol = Model.instance().getUsuarioDAO().busquedaRol(model.getId());
        return rol;
    }

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/presentation/header.jsp");
    _jspx_dependants.add("/presentation/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("   <head>\r\n");
      out.write("    <link href=\"css/Estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"></script>\r\n");
      out.write("        <title>Administración de Solicitudes</title>\r\n");
      out.write("   </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 Usuario logged = (Usuario) session.getAttribute("logged");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Encabezado</title>\r\n");
      out.write("    <base href=\"http://localhost:8080/SistemaGestorActivos/\">\r\n");
      out.write("    <link href=\"css/Estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark static-top\">\r\n");
      out.write("        <img id=\"engranaje\" src=\"images/engranajes.jpg\" alt=\"engranajes\"> &nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("        <p class=\"navbar-brand\">Activos</p>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\r\n");
      out.write("                <ul class=\"navbar-nav ml-auto\">\r\n");
      out.write("\r\n");
      out.write("                    ");
 if (logged != null) { 
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    ");
 if (obtenerRol(logged).equals("Admin") || obtenerRol(logged).equals("SOCCB")
                                || obtenerRol(logged).equals("JOCCB") || obtenerRol(logged).equals("Registrador")
                                || obtenerRol(logged).equals("JefeRH")) { 
      out.write("         \r\n");
      out.write("                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"presentation/users/Admin/Admin.jsp\">Principal</a> </li>\r\n");
      out.write("                        ");
 }
      out.write(" \r\n");
      out.write("\r\n");
      out.write("                    ");
 if (obtenerRol(logged).equals("Admin")) { 
      out.write("        \r\n");
      out.write("                    <li class=\"nav-item dropdown\"> \r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            Administrador\r\n");
      out.write("                        </a>  \r\n");
      out.write("                        <div class=\"dropdown-menu\" aria-labelledby=\"subMenuAdmin\">\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Admin/Listado.jsp\">Mostrar Solicitudes</a>\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"Solicitud.jsp\">Realizar Solicitud</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </li>\r\n");
      out.write("\r\n");
      out.write("                    ");
 }
      out.write("         \r\n");
      out.write("\r\n");
      out.write("                    <li class=\"nav-item dropdown\"> \r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            ");
      out.print(obtenerFuncionario(logged).getNombre());
      out.write("\r\n");
      out.write("                        </a>\r\n");
      out.write("                        <div class=\"dropdown-menu\" aria-labelledby=\"subMenuAdmin\">\r\n");
      out.write("                            <a class=\"dropdown-item\" <a href=\"presentation/logout\">Cerrar sesión</a>\r\n");
      out.write("                        </div>           \r\n");
      out.write("                    </li>\r\n");
      out.write("                    ");
 }
      out.write(" \r\n");
      out.write("\r\n");
      out.write("                    ");
 if (logged == null) { 
      out.write("\r\n");
      out.write("                    <li class=\"nav-item\">\r\n");
      out.write("                        <a class=\"nav-link\" href=\"presentation/index.jsp\">Acerca de</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("\r\n");
      out.write("                    <li class=\"nav-item\">\r\n");
      out.write("                        <a class=\"nav-link\" href=\"presentation/prepareLogin\">Iniciar Sesion</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    ");
 }
      out.write("    \r\n");
      out.write("\r\n");
      out.write("                </ul>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        <div class=\"modal fade\" id=\"myModal\" role=\"dialog\">\r\n");
      out.write("            <div class=\"modal-dialog modal-sm\">\r\n");
      out.write("                <div class=\"modal-content\">\r\n");
      out.write("                    <div class=\"modal-header\">\r\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
      out.write("                        <h4 class=\"modal-title\" id=\"myModalTitle\">Administrar</h4>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"modal-body\" id=\"myModalMessage\">\r\n");
      out.write("                        <p>Bienvenido</p>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- ********************************************************** -->\r\n");
      out.write("        <!-- Modal del BootsTrap formulario del ingreso de conductores                  -->\r\n");
      out.write("\r\n");
      out.write("        <div class=\"modal fade\" id=\"myModalSolicitud\" role=\"dialog\">\r\n");
      out.write("            <div class=\"modal-dialog modal-lg\">\r\n");
      out.write("                <div class=\"modal-content\">\r\n");
      out.write("                    <div class=\"modal-header\">\r\n");
      out.write("                        <h4 class=\"modal-title\" id=\"myModalTitle\">Crear una solicitud </h4>\r\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"modal-body\" id=\"myModalMessage\">\r\n");
      out.write("                        <form role=\"form\" action=\"presentation/register/create\" method=\"POST\">\r\n");
      out.write("                          \r\n");
      out.write("                            <div class=\"form-group\" id=\"groupCedula\">\r\n");
      out.write("                                <label for=\"comprobante\">Comprobante:</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"comprobante\" name=\"comprobante\"autofocus=\"true\" placeholder=\"Comprobante\">\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                            \r\n");
      out.write("                            <div class=\"form-group\" id=\"groupApellidos\">\r\n");
      out.write("                                <label for=\"apellidos\">Fecha:</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"fecha\" name=\"fecha\" placeholder=\"Fecha\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            \r\n");
      out.write("                            <div class=\"form-group\" id=\"groupTipo\">\r\n");
      out.write("                                <label for=\"Tipo\">Tipo:</label>\r\n");
      out.write("                                <select class=\"form-control\" name=\"tipo\" >\r\n");
      out.write("                                    <option value=\"donacion\" name=\"donacion\" selected=\"donacion\">Donación</option>\r\n");
      out.write("                                    <option value=\"compra\" name=\"compra\">Compra</option>\r\n");
      out.write("                                    <option value=\"produccion\" name=\"produccion\">Producción</option>\r\n");
      out.write("                                </select>\r\n");
      out.write("                            \r\n");
      out.write("                            </div>\r\n");
      out.write("                            \r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <input type=\"submit\" class=\"btn btn-info\" value=\"Guardar Solicitud\"  name=\"Guardar\"action=\"SolicitudesAction\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            \r\n");
      out.write("                        </form>\r\n");
      out.write("                        \r\n");
      out.write("                        \r\n");
      out.write("                        <form role=\"form\" action=\"presentation/register/add\" >\r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                            <div class=\"form-group row\"> \r\n");
      out.write("                           &nbsp\r\n");
      out.write("                           <div class=\"col-xs-1\" id=\"groupDescripcion\">\r\n");
      out.write("                                <label for=\"TA\">Descripción:</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"descripcion\" name=\"descripcion\" placeholder=\"Descripción\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            &nbsp\r\n");
      out.write("                            <div class=\"col-xs-1\" id=\"groupMarca\">\r\n");
      out.write("                                <label for=\"Monto\">Marca:</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"monto\" name=\"monto\" placeholder=\"Marca\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            &nbsp     \r\n");
      out.write("                            <div class=\"col-xs-1\" id=\"groupModelo\">\r\n");
      out.write("                                <label for=\"Modelo\">Modelo:</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"modelo\" name=\"modelo\" placeholder=\"Modelo\">\r\n");
      out.write("                            </div>    \r\n");
      out.write("                           &nbsp &nbsp      \r\n");
      out.write("                            <div class=\"col-xs-1\" id=\"groupPrecio\">\r\n");
      out.write("                                <label for=\"Precio\">Precio:</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precio\" placeholder=\"Precio\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                           &nbsp     \r\n");
      out.write("                            <div class=\"col-xs-1\" id=\"groupCantidad\">\r\n");
      out.write("                                <label for=\"Cantidad\">Cantidad:</label>\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Cantidad\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            \r\n");
      out.write("                           <div class=\"form-group\">\r\n");
      out.write("                               <input type=\"hidden\" value=\"agregar Solicitud\" name=\"agregar\" id=\"SolicitudesAction\"/>\r\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-info\" id=\"agregar\">Agregar bien</button>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            \r\n");
      out.write("                            </div>\r\n");
      out.write("                            </div> \r\n");
      out.write("                        </form>\r\n");
      out.write("                        \r\n");
      out.write("                            <table class=\"table table-hover table-condensed\" id=\"tablaSolicitud\" name=\"tablaSolicitud\">\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Descripción\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                     \r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Marca\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Modelo\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Precio Unitario\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Cantidad\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                        \r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>1</td>\r\n");
      out.write("\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>1</td>\r\n");
      out.write("                                           \r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>1</td>\r\n");
      out.write("                                           \r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>1</td>\r\n");
      out.write("                                           \r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>1</td>\r\n");
      out.write("                                           \r\n");
      out.write("                                        </tr>\r\n");
      out.write("\r\n");
      out.write("                                    </table>\r\n");
      out.write("                        \r\n");
      out.write("                            <div class=\"form-group\">\r\n");
      out.write("                                <input type=\"hidden\" value=\"agregarSolicitud\" id=\"ConductoresAction\"/>\r\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-primary\" id=\"enviar\">Guardar</button>\r\n");
      out.write("                                <button type=\"reset\" class=\"btn btn-danger\" id=\"cancelar\">Cancelar</button>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"form-group height25\" >\r\n");
      out.write("                                <div class=\"alert alert-success hiddenDiv\" id=\"mesajeResult\">\r\n");
      out.write("                                    <strong id=\"mesajeResultNeg\">Estás en el área de guardar una solicitud.</strong> \r\n");
      out.write("                                    <span id=\"mesajeResultText\">Por favor guarda una solicitud</span>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- ********************************************************** -->\r\n");
      out.write("        <div id=\"myDiv\">\r\n");
      out.write("           <section id=\"ManVeiculo\" class=\"section-padding wow fadeInUp delay-05s\">\r\n");
      out.write("                <div class=\"container\">\r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("                        <div class=\"container\">\r\n");
      out.write("                            <div class=\"page-header\">\r\n");
      out.write("                                <h1><small><span class=\"logo-dec\"></span>Mantenimiento para la Solicitudes</small></h1>\r\n");
      out.write("                            </div>\r\n");
      out.write("                           \r\n");
      out.write("                            <div class=\"panel panel-primary\">\r\n");
      out.write("                                <div class=\"panel-body\">\r\n");
      out.write("                                    <center>\r\n");
      out.write("                                        <button type=\"button\" class=\"btn btn-primary float-right\" data-toggle=\"modal\" data-target=\"#myModalSolicitud\" id=\"btMostarFormConductor\">Insertar Solicitud</button>\r\n");
      out.write("                                    </center><br>\r\n");
      out.write("                                    <!-- ********************************************************** -->\r\n");
      out.write("                                    <div class=\"col-sm-12\">\r\n");
      out.write("                                        <form role=\"form\" onsubmit=\"return false;\" id=\"formConductor\" class=\"form-horizontal centered\">\r\n");
      out.write("                                            <div class=\"form-group\" id=\"groupFiltroConductor\">\r\n");
      out.write("                                                <div class=\"col-sm-4\" class=\"Centered \">\r\n");
      out.write("                                                    <p><b>Buscar por comprobante de solicitud</b></p>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                                <div class=\"col-sm-4\">\r\n");
      out.write("                                                    <input type=\"text\" class=\"form-control\" id=\"FiltroSolicitud\" placeholder=\"Dígite el comprobante de la solicitud\">\r\n");
      out.write("                                                    <br>\r\n");
      out.write("                                                    <button type=\"button\" class=\"btn btn-info \" id=\"btBuscarSolicitud\">\r\n");
      out.write("                                                        Buscar \r\n");
      out.write("                                                    </button> \r\n");
      out.write("                                                </div>\r\n");
      out.write("                                            \r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            \r\n");
      out.write("                                        </form>\r\n");
      out.write("                                    \r\n");
      out.write("                                    <!-- ********************************************************** -->\r\n");
      out.write("\r\n");
      out.write("                                    <table class=\"table table-hover table-condensed\" id=\"tablaSolicitud\">\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Descripción\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                     \r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Marca\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Modelo\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Precio Unitario\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                        <th>\r\n");
      out.write("                                            Cantidad\r\n");
      out.write("                                        </th>\r\n");
      out.write("                                    </table>\r\n");
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                                \r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div> \r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </section>\r\n");
      out.write("         ");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Pie de Página</title>\r\n");
      out.write("        <base href=\"http://localhost:8080/SistemaGestorActivos/\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.2/css/all.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- Footer -->\r\n");
      out.write("    <footer class=\"page-footer font-small bg-dark pt-4\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Footer Elements -->\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("\r\n");
      out.write("            <!-- Social buttons -->\r\n");
      out.write("            <ul class=\"list-unstyled list-inline text-center\">\r\n");
      out.write("                <li class=\"list-inline-item\">\r\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.facebook.com/\">\r\n");
      out.write("                        <i class=\"fab fa-facebook\"> </i>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"list-inline-item\">\r\n");
      out.write("\r\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.twitter.com/\">\r\n");
      out.write("                        <i class=\"fab fa-twitter\"> </i>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"list-inline-item\">\r\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.instagram.com/\">\r\n");
      out.write("                        <i class=\"fab fa-instagram\"> </i>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"list-inline-item\">\r\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://es.stackoverflow.com/\">\r\n");
      out.write("                        <i class=\"fab fa-stack-overflow\"> </i>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li class=\"list-inline-item\">\r\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.youtube.com/\">\r\n");
      out.write("                        <i class=\"fab fa-youtube\"> </i>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            <!-- Social buttons -->\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- Footer Elements -->\r\n");
      out.write("\r\n");
      out.write("        <!-- Copyright -->\r\n");
      out.write("        <div class=\"footer-copyright text-center text-white py-3\">© 2019 Universidad Nacional:\r\n");
      out.write("            <a href=\"https://www.una.ac.cr/\"> una.ac.cr</a>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- Copyright -->\r\n");
      out.write("\r\n");
      out.write("    </footer>\r\n");
      out.write("    <!-- Footer -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("    </body>\r\n");
      out.write("    \r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
