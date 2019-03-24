<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="SistemaGestorActivos.Logic.Funcionario"%>
<%@page import="SistemaGestorActivos.Logic.Model"%>
<%@page import="SistemaGestorActivos.Logic.Usuario"%>
<% Usuario logged = (Usuario) session.getAttribute("logged");%>
<!DOCTYPE html>

<head>
    <title>Encabezado</title>
    <base href="http://localhost:8080/SistemaGestorActivos/">
    <link href="css/Estilos.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
        <img id="engranaje" src="images/engranajes.jpg" alt="engranajes"> &nbsp;&nbsp;&nbsp;&nbsp;
        <p class="navbar-brand">Activos</p>
        <div class="container">
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <% if (logged != null) { %>

                    <% if (logged.getRol().equals("Admin") || logged.getRol().equals("JefeRH")
                                || logged.getRol().equals("Secretaria") || logged.getRol().equals("Jefe")
                                || logged.getRol().equals("Registrador")) { %>         
                    <li class="nav-item"><a class="nav-link" href="presentation/index.jsp">Acerca de</a> </li>
                        <% }%>

                    <% if (logged.getRol().equals("Admin")) { %>        
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Administrador
                        </a>  
                        <div class="dropdown-menu" aria-labelledby="subMenuAdmin">
                            <a class="dropdown-item" href="presentation/users/Admin/Listado.jsp">Mostrar Solicitudes</a>
                            <a class="dropdown-item" href="#">Realizar Solicitud</a>
                        </div>
                    </li>

                    <% }%>         

                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <%=getFuncionario(logged).getNombre()%>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="subMenuAdmin">
                            <a class="dropdown-item" <a href="presentation/logout">Cerrar sesión</a>
                        </div>           
                    </li>
                    <% }%> 

                    <% if (logged == null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="presentation/index.jsp">Acerca de</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="presentation/prepareLogin">Iniciar Sesion</a>
                    </li>
                    <% }%>    

                </ul>

            </div>
        </div>
    </nav>
</body>
</html>

<%!
    private Funcionario getFuncionario(Usuario model) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(Model.instance().getUsuarioDAO().busquedaNombre(model.getId()));
        return funcionario;
    }
%>
