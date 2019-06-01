package org.apache.jsp.presentation.users.Registrador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Usuario;

public final class AsignacionActivos_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Asignacion de Activos a Funcionarios</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <base href=\"http://localhost:8080/SistemaGestorActivos/\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.1.0/css/all.css\">\n");
      out.write("        <link href=\"css/ListadoCss.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
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
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n");
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
      out.write("                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"presentation/users/Lobby\">Principal</a> </li>\r\n");
      out.write("\r\n");
      out.write("                    ");
 if (request.isUserInRole("Admin")) { 
      out.write("\r\n");
      out.write("                    <li class=\"nav-item dropdown\"> \r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            Administrador\r\n");
      out.write("                        </a>  \r\n");
      out.write("                        <div class=\"dropdown-menu\">\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Admin/Lista\">Mostrar Solicitudes</a>\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Admin/nuevaSolicitud\">Realizar Solicitud</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </li>               \r\n");
      out.write("                    ");
 }
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    ");
 if (request.isUserInRole("SOCCB")) { 
      out.write("\r\n");
      out.write("                    <li class=\"nav-item dropdown\"> \r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            Secretaria\r\n");
      out.write("                        </a>  \r\n");
      out.write("                        <div class=\"dropdown-menu\">\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Secretaria/Lista\">Ver Solicitudes</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </li> \r\n");
      out.write("                    ");
 }
      out.write("\r\n");
      out.write("                    ");
 if (request.isUserInRole("JOCCB")) { 
      out.write("\r\n");
      out.write("                    <li class=\"nav-item dropdown\"> \r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            Jefe OCCB\r\n");
      out.write("                        </a>  \r\n");
      out.write("                        <div class=\"dropdown-menu\">\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Jefe/Asignacion\">Asignar Registrador</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </li> \r\n");
      out.write("                    ");
 }
      out.write("\r\n");
      out.write("                    ");
 if (request.isUserInRole("Registrador")) { 
      out.write("\r\n");
      out.write("                    <li class=\"nav-item dropdown\"> \r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            Registrador\r\n");
      out.write("                        </a>  \r\n");
      out.write("                        <div class=\"dropdown-menu\">\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Registrador/AdministrarCategorias\">Administrar categorias</a>\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Registrador/verSolicitudes\">Incorporar Bienes</a>\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/users/Registrador/asignarActivos\">Asignar Activos</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </li> \r\n");
      out.write("                    ");
 }
      out.write("\r\n");
      out.write("                    <li class=\"nav-item dropdown\"> \r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            ");
      out.print(session.getAttribute("funcActual"));
      out.write("\r\n");
      out.write("                        </a>\r\n");
      out.write("                        <div class=\"dropdown-menu\">\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"presentation/logout\">Cerrar sesión</a>\r\n");
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
      out.write("\n");
      out.write("        <br>\n");
      out.write("\n");
      out.write("        <div class=\"container\" onLoad=\"cargarActivos()\">\n");
      out.write("            <h1 style=\"text-align: center\">Listado de Categorias</h1>\n");
      out.write("            <br>\n");
      out.write("            <div class=\"row justify-content-center\">\n");
      out.write("                <div class=\"col-12 col-md-10 col-lg-8\">\n");
      out.write("                    <div class=\"card card-sm\">\n");
      out.write("                        <div class=\"card-body row no-gutters align-items-center\">\n");
      out.write("                            <div class=\"col\">\n");
      out.write("                                <input id =\"filtrado\" name=\"filtrado\" class=\"form-control form-control-lg form-control-borderless\" type=\"search\" placeholder=\"Id...\">\n");
      out.write("                            </div>\n");
      out.write("                            <!--end of col-->\n");
      out.write("                            &nbsp;&nbsp;&nbsp;\n");
      out.write("                            <div class=\"col-auto\">\n");
      out.write("                                <button type=\"button\" class=\"btn btn-info\" onclick=\"buscar()\">Buscar</button>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"ventanaSolicitud\">\n");
      out.write("            <br>\n");
      out.write("            <table class=\"table table-bordered table-striped mb-0\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr> <th>Id</th>\n");
      out.write("                        <th>Bien</th>\n");
      out.write("                        <th>Categoria</th>\n");
      out.write("                        <th>Consecutivo Asignado</th>\n");
      out.write("                        <th>Funcionario</th>\n");
      out.write("                        <th>Accion</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody id=\"listadoActivos\"></tbody>\n");
      out.write("            </table>\n");
      out.write("            <br>\n");
      out.write("        </div>\n");
      out.write("        ");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <title>Pie de Página</title>\n");
      out.write("        <base href=\"http://localhost:8080/SistemaGestorActivos/\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.2/css/all.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <!-- Footer -->\n");
      out.write("    <footer class=\"page-footer font-small bg-dark pt-4\">\n");
      out.write("\n");
      out.write("        <!-- Footer Elements -->\n");
      out.write("        <div class=\"container\">\n");
      out.write("\n");
      out.write("            <!-- Social buttons -->\n");
      out.write("            <ul class=\"list-unstyled list-inline text-center\">\n");
      out.write("                <li class=\"list-inline-item\">\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.facebook.com/\">\n");
      out.write("                        <i class=\"fab fa-facebook\"> </i>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"list-inline-item\">\n");
      out.write("\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.twitter.com/\">\n");
      out.write("                        <i class=\"fab fa-twitter\"> </i>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"list-inline-item\">\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.instagram.com/\">\n");
      out.write("                        <i class=\"fab fa-instagram\"> </i>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"list-inline-item\">\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://es.stackoverflow.com/\">\n");
      out.write("                        <i class=\"fab fa-stack-overflow\"> </i>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"list-inline-item\">\n");
      out.write("                    <a class=\"btn-floating btn-fb mx-1\" href=\"https://www.youtube.com/\">\n");
      out.write("                        <i class=\"fab fa-youtube\"> </i>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("            <!-- Social buttons -->\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <!-- Footer Elements -->\n");
      out.write("\n");
      out.write("        <!-- Copyright -->\n");
      out.write("        <div class=\"footer-copyright text-center text-white py-3\">© 2019 Universidad Nacional:\n");
      out.write("            <a href=\"https://www.una.ac.cr/\"> una.ac.cr</a>\n");
      out.write("        </div>\n");
      out.write("        <!-- Copyright -->\n");
      out.write("\n");
      out.write("    </footer>\n");
      out.write("    <!-- Footer -->\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            function cargarActivos() {\n");
      out.write("                var id = \"\";\n");
      out.write("                ajax({type: \"GET\", url: \"api/activosSinAsignar?id=\" + id,\n");
      out.write("                    success: function (personas) {list(personas);},\n");
      out.write("                    error: function (status) {alert(errorMessage(status));}\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("            function list(activos) {\n");
      out.write("                var listado = document.getElementById(\"listadoActivos\");\n");
      out.write("                listado.innerHTML = \"\";\n");
      out.write("                activos.forEach((ac) => {row(listado, ac); });\n");
      out.write("            }\n");
      out.write("            function row(listado, activo) {\n");
      out.write("                var tr = document.createElement(\"tr\");\n");
      out.write("                tr.innerHTML = \"<td>\" + activo.id + \"</td>\" +\n");
      out.write("                        \"<td>\" + activo.bien.descripcion + \"</td>\" +\n");
      out.write("                        \"<td>\" + activo.categoria.codigo + \"</td>\" +\n");
      out.write("                        \"<td>\" + activo.consecutivoActual + \"</td>\" +\n");
      out.write("                        \"<td>\" + \"<select onclick=\\\"cargarFunc()\\\"> </select>\" + \"</td>\" +\n");
      out.write("                        \"<td><button type=button class=\\\"btn btn-success\\\" onclick='update(\\\"\" + activo.id + \"\\\");'>Asignar</button></td>\";\n");
      out.write("                        listado.appendChild(tr);\n");
      out.write("            }\n");
      out.write("            function buscar() {\n");
      out.write("                var id = document.getElementById(\"filtrado\").value;\n");
      out.write("                ajax({type: \"GET\", url: \"api/activosSinAsignar?id=\" + id,                    \n");
      out.write("                    success: function (personas) {list(personas);},\n");
      out.write("                    error: function (status) {alert(errorMessage(status));}\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("            function update() {\n");
      out.write("                cat = {id: document.getElementById(\"updId\").value,\n");
      out.write("                    codigo: document.getElementById(\"updCodigo\").value,\n");
      out.write("                    descripcion: document.getElementById(\"updDescripcion\").value,\n");
      out.write("                    consecutivo: document.getElementById(\"updConsecutivo\").value\n");
      out.write("                };\n");
      out.write("                // se envia  al servidor\n");
      out.write("                ajax({type: \"PUT\", url: \"api/categorias\", data: JSON.stringify(cat), contentType: \"application/json\"})\n");
      out.write("                        .then(function () {\n");
      out.write("                            var descrip = \"\";\n");
      out.write("                            ajax({type: \"GET\", url: \"api/categorias?descripcion=\" + descrip})\n");
      out.write("                                    .then(function (personas) {\n");
      out.write("                                        list(personas);\n");
      out.write("                                    },\n");
      out.write("                                            function (status) {\n");
      out.write("                                                alert(errorMessage(status));\n");
      out.write("                                            });\n");
      out.write("                        });\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
