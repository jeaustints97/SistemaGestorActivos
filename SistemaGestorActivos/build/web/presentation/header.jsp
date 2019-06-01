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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
        <img id="engranaje" src="images/engranajes.jpg" alt="engranajes"> &nbsp;&nbsp;&nbsp;&nbsp;
        <p class="navbar-brand">Activos</p>
        <div class="container">
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <% if (logged != null) { %>

                    <li class="nav-item"><a class="nav-link" href="presentation/users/Lobby">Principal</a> </li>

                    <% if (request.isUserInRole("Admin")) { %>
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Administrador
                        </a>  
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="presentation/users/Admin/Lista">Mostrar Solicitudes</a>
                            <a class="dropdown-item" href="presentation/users/Admin/nuevaSolicitud">Realizar Solicitud</a>
                        </div>
                    </li>               
                    <% }%>

                    <% if (request.isUserInRole("SOCCB")) { %>
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Secretaria
                        </a>  
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="presentation/users/Secretaria/Lista">Ver Solicitudes</a>
                        </div>
                    </li> 
                    <% }%>
                    <% if (request.isUserInRole("JOCCB")) { %>
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Jefe OCCB
                        </a>  
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="presentation/users/Jefe/Asignacion">Asignar Registrador</a>
                        </div>
                    </li> 
                    <% }%>
                    <% if (request.isUserInRole("Registrador")) { %>
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Registrador
                        </a>  
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="presentation/users/Registrador/AdministrarCategorias">Administrar categorias</a>
                            <a class="dropdown-item" href="presentation/users/Registrador/verSolicitudes">Incorporar Bienes</a>
                        </div>
                    </li> 
                    <% }%>
                    <li class="nav-item dropdown"> 
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <%=session.getAttribute("funcActual")%>
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="presentation/logout">Cerrar sesión</a>
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
