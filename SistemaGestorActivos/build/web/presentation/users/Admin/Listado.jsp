<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="SistemaGestorActivos.Logic.Solicitud"%>
<%@page import="java.util.List"%>
<%@page import="SistemaGestorActivos.Logic.Model"%>
<%@page import="SistemaGestorActivos.Logic.Usuario"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Listado</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <base href="http://localhost:8080/SistemaGestorActivos/">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css">
        <link href="css/ListadoCss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <br>
        <div class="boxed bg-light text-dark boxed">
            <br>

            <h3 class="encabezado">
                Solicitudes - <%= session.getAttribute("depActual")%>
            </h3>

            <div class="container">
                <br/>
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-8">
                        <form class="card card-sm" action="presentation/users/Admin/comenzar_filtrado">
                            <div class="card-body row no-gutters align-items-center">
                                <div class="col">
                                    <input id ="filtrado" name="filtrado" class="form-control form-control-lg form-control-borderless" type="search" placeholder="Comprobante">
                                </div>
                                <!--end of col-->
                                &nbsp;&nbsp;&nbsp;
                                <div class="col-auto">
                                    <button class="btn btn-lg btn-success" type="submit">Buscar</button>
                                </div>
                                <!--end of col-->
                            </div>
                        </form>
                    </div>
                    <!--end of col-->
                </div>
            </div>

            <br>


            <div class="table-wrapper-scroll-y my-custom-scrollbar">
                <table class="table table-bordered table-striped mb-0">
                    <tr>                
                        <th>Numero</th>
                        <th>Comprobante</th>
                        <th>Fecha</th>
                        <th>Tipo</th>
                        <th>Cantidad</th>
                        <th>Monto</th>
                        <th>Estado</th>
                        <th>Realizar Cambios</th>
                    </tr>
                    <% for (Solicitud s : (List<Solicitud>)session.getAttribute("listaSol")) {%>
                    <tr>
                        <td><%=s.getId()%></td>
                        <td> <%=s.getComprobante()%></td>
                        <td> <%=s.getFecha().toLocaleString().substring(0, 11) %></td>
                        <td> <%=s.getTipo()%></td>
                        <td><%=s.getCantidad()%> </td>
                        <td><%=s.getTotal()%> </td>
                        <td> <%=s.getEstado().getDescripcion()%></td>
                        <td> <a href="presentation/users/Admin/verSolicitud?idSolicitud=<%=s.getId()%>">Ver</a></td>
                    </tr>
                    <% }%> 
                </table>
            </div>
            <br>
        </div>
        <br>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>