<%@page import="SistemaGestorActivos.Logic.Solicitud"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="SistemaGestorActivos.Logic.Usuario"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
        <base href="http://localhost:8080/SistemaGestorActivos/">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="css/loginCss.css" rel="stylesheet" type="text/css"/>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="presentation/js/login.js" type="text/javascript"></script>
    </head>
    <body onLoad="camposLogin()">

        <%@ include file="/presentation/header.jsp" %>

        <% Usuario model = (Usuario) request.getAttribute("model"); %>
        <% Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");%>
        <% Map<String, String[]> values = (errors == null) ? this.getValues(model) : request.getParameterMap();%>

        <script type="text/javascript" language="JavaScript">camposLogin();</script>

        <div class="wrapper fadeInDown">
            <div id="formContent">
                <!-- Tabs Titles -->

                <!-- Icon -->
                <div class="fadeIn first">
                    <img src="https://png.pngtree.com/svg/20161229/e7a5cf5c9e.svg" id="icon" alt="User Icon" />
                </div>

                <!-- Login Form -->
                <form method="POST" name="logIn" action="presentation/login">
                    <input type="text" id="idUsuario" name="idUsuario" class="fadeIn second <%=validity("idUsuario", errors)%>" 
                           placeholder="Nombre de usuario" value="<%=value("idUsuario", values)%>">

                    <input type="password" id="clave" name="clave" class="fadeIn third <%=validity("clave", errors)%>"  
                           placeholder="Contraseña" value="<%=value("clave", values)%>">

                    <input type="submit" class="fadeIn fourth" value="Ingresar">
                </form>

                <!-- Remind Passowrd -->
                <div id="formFooter">
                    <a class="underlineHover" href="#">Regresar al menú anterior</a>
                </div>

            </div>
        </div>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>


<%!    private String validity(String field, Map<String, String> errors) {
        if ((errors != null) && (errors.get(field) != null)) {
            return "is-invalid";
        } else {
            return "";
        }
    }

    private String value(String field, Map<String, String[]> values) {
        return values.get(field)[0];
    }

    private Map<String, String[]> getValues(Usuario model) {
        Map<String, String[]> values = new HashMap<String, String[]>();
        values.put("idUsuario", new String[]{model.getId()});
        values.put("clave", new String[]{model.getClave()});
        return values;
    }

%>