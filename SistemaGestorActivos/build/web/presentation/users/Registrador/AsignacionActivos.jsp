<%@page import="SistemaGestorActivos.Logic.Activo"%>
<%@page import="SistemaGestorActivos.Logic.Solicitud"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Asignacion de Activos a Funcionarios</title>
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
        <div class="container boxed bg-light text-dark boxed ">
            <br>
            <h3 class="encabezado"> Activos sin Asignar </h3>
            <div class="container">
                <br/>
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-8">
                        <form class="card card-sm" action="presentation/users/Registrador/comenzar_filtrado_activos">
                            <div class="card-body row no-gutters align-items-center">
                                <div class="col">
                                    <input id ="filtrado" name="filtrado" class="form-control form-control-lg form-control-borderless" type="search" placeholder="Id...">
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
                            <th>Id</th>
                            <th>Solicitud <br> al que pertenece</th>
                            <th>Bien</th>
                            <th>Categoria</th>
                            <th>Consecutivo Asignado</th>
                            <th>Funcionario/Dependencia/Puesto</th>
                            <th>Accion</th>
                        </tr>
                        <% for (Activo act : (List<Activo>) session.getAttribute("listaActs")) {%>
                        <tr>
                            <td><%=act.getId()%>
                                <input type="hidden" name="idActivo" value="<%=act.getId()%>">
                            </td>
                            <td><%=act.getBien().getSolicitud().getId()%> </td>
                            <td> <%=act.getBien().getDescripcion()%></td>
                            <td> <%=act.getCategoria().getCodigo()%></td>
                            <td> <%=act.getConsecutivoActual()%></td>
                            <td> 
                                <select name="<%=act.getId()%>">
                                    <% for (Funcionario f : (List<Funcionario>) session.getAttribute("listaFunc")) {%>
                                    <option value="<%=f.getId()%>"><%=f.getNombre() + "/"
                                            + f.getDependencia().getNombre() + "/"
                                            + f.getPuesto().getDescripcion()%></option>
                                        <% }%> 
                                </select>
                            </td>
                            <td><input type="submit" name="asignarFunc" value="Asignar"></td>
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
