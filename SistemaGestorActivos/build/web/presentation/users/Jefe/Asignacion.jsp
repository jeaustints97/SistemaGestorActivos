<%@page import="SistemaGestorActivos.Logic.Solicitud"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Bienvenido Jefe</title>
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
            <h3 class="encabezado"> Listado de Solicitudes </h3>
            <div class="container">
                <br/>
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-8">
                        <form class="card card-sm" action="presentation/users/Jefe/comenzar_filtrado">
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
                <form method="POST" name="asignar" action="presentation/users/Jefe/asignar_registrador">
                    <table class="table table-bordered table-striped mb-0">
                        <tr>                
                            <th>Numero</th>
                            <th>Comprobante</th>
                            <th>Fecha</th>
                            <th>Tipo</th>
                            <th>Registradores</th>
                            <th>Acción</th>
                        </tr>
                        <% for (Solicitud s : (List<Solicitud>) session.getAttribute("listaSol")) {%>
                        <tr>
                            <td><%=s.getId()%>
                                <input type="hidden" name="idSolicitud" value="<%=s.getId()%>">
                            </td>
                            <td> <%=s.getComprobante()%></td>
                            <td> <%=s.getFecha().toLocaleString().substring(0, 11)%></td>
                            <td> <%=s.getTipo()%></td>
                            <td> 
                                <select name="<%=s.getId()%>">
                                    <% for (Funcionario f : (List<Funcionario>) session.getAttribute("listaReg")) {%>
                                    <option value="<%=f.getId()%>"><%=f.getNombre()%></option>
                                    <% }%> 
                                </select>
                            </td>
                            <td><input type="submit" name="asignarReg" value="Asignar"></td>
                        </tr>
                        <% }%> 
                    </table>
                </form>
            </div>
            <br>
        </div>
        <br>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
