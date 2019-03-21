<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <title>Encabezado</title>
    <base href="http://localhost:8080/SistemaGestorActivos/">
    <link href="css/Estilos.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
        <div class="container">
            <img id="engranaje" src="images/engranajes.jpg" alt="engranajes"> &nbsp;&nbsp;&nbsp;&nbsp;
            <p class="navbar-brand">Activos</p>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="presentation/index.jsp">Acerca de
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <!--  <a class="nav-link" href="presentation/login/login.jsp">Iniciar Sesion</a>-->
                        <a class="nav-link" href="presentation/prepareLogin">Iniciar Sesion</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>
