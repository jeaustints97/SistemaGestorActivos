<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="SistemaGestorActivos.Logic.Solicitud"%>
<%@page import="SistemaGestorActivos.Logic.Bien"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="myModalTitle">Error</h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <p>No se puede dejar campos vacíos.</p>
                    </div>
                </div>
            </div>
</div>
    <head>
        <title>Ingresar Solicitud</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <base href="http://localhost:8080/SistemaGestorActivos/">
        <script src="presentation/js/solicitud.js" type="text/javascript"></script>
        <link href="css/Estilos.css" rel="stylesheet" type="text/css"/>
    </head>

    <body onLoad="camposSolicitud()">
        <%@ include file="/presentation/header.jsp" %>

        <% Solicitud solTemp = (Solicitud) session.getAttribute("solicitud");%>
        <% ArrayList<Bien> bienesTemp = (ArrayList<Bien>) session.getAttribute("bienes");%>

        <div class="ventanaSolicitud">
            <form method="POST" name="nuevaSolicitud" action="presentation/users/Admin/ingresarSolicitud">
                <br>
                <table border=0 name="tablasolicitud" id="tablasolicitud" cellpadding=6 cellspacing=8>
                    <tr>
                        <td colspan="6" class="bordeInferior"><h3>Nueva Solicitud</h3></td>
                    </tr>
                    <tr>
                        <td colspan="6"><br></td>
                    </tr>
                    <tr>
                        <th id="labelComprobante">Comprobante</th>
                        <td><input type="text" id="comprobante" name="comprobante" value="<%= solTemp.getComprobante()%>"></td>
                        <th id="labelFecha">Fecha</th>
                            <%if (solTemp.getFecha() == null) {%>
                        <td><input type="text" id="fecha" name="fecha" placeholder="DD-MM-YYYY" value="<%= solTemp.getFecha()%>"></td>
                            <% } else {%>
                        <td><input type="text" id="fecha" name="fecha" placeholder="DD-MM-YYYY" value="<%= solTemp.getFecha().toLocaleString().substring(0, 11)%>"></td>
                            <% }%>
                        <th id="labelTipo">Tipo</th>
                        <td>
                            <select name="tipo">
                                <%if (solTemp.getTipo().equals("")) { %>
                                <option value="Seleccionar" selected disabled>Seleccionar</option>
                                <option value="Compra">Compra</option>
                                <option value="Donacion">Donacion</option>
                                <option value="Produccion">Produccion</option>
                                <%}%>
                                <%if (solTemp.getTipo().equals("Compra")) { %>
                                <option value="Compra" selected>Compra</option>
                                <option value="Donacion">Donacion</option>
                                <option value="Produccion">Produccion</option>
                                <%}%>
                                <%if (solTemp.getTipo().equals("Donacion")) { %>
                                <option value="Compra">Compra</option>
                                <option value="Donacion" selected>Donacion</option>
                                <option value="Produccion">Produccion</option>
                                <%}%>
                                <%if (solTemp.getTipo().equals("Produccion")) { %>
                                <option value="Compra">Compra</option>
                                <option value="Donacion">Donacion</option>
                                <option value="Produccion" selected>Produccion</option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bordeInferior"><h3>Bienes</h3></td>
                    </tr>
                    <tr>
                        
                        <th id="labelDescripcion">Descripcion</th>
                        <th id="labelMarca">Marca</th>
                        <th id="labelModelo">Modelo</th>
                        <th id="labelPrecioU">Precio Unitario</th>
                        <th id="labelCantidad">Cantidad</th>
                        <td>
                            
                        </td>
                    </tr>
                    <tr>
                        
                        <td><input size=13 type="text" name="descripcion" id="descripcion" placeholder="Descripcion" value=""></td>
                        <td><input size=13 type="text" name="marca" id="marca" placeholder="Marca" value=""></td>
                        <td><input size=13 type="text" name="modelo" id="modelo" placeholder="Modelo" value=""></td>
                        <td><input type="number" name="precioU" id="precioU" step="0.01" placeholder="0.0" value=""></td>
                        <td><input type="number" name="cantidad" id="cantidad" placeholder="0" value=""></td>
                        <td> <input type="submit" name="agregarBien" id="agregarBien" onclick="agregarbien();"  value="Agregar"> </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bordeInferior"><h3>Listado</h3></td>
                    </tr>
                    <tr>
                        <td colspan="6"><br></td>
                    </tr>
                    <% for (Bien b : bienesTemp) {%>
                    <tr class="tablaListado">
                        <td class="ladosTablaListado"><%=b.getDescripcion()%></td>
                        <td class="ladosTablaListado"> <%=b.getMarca()%></td>
                        <td class="ladosTablaListado"> <%=b.getModelo()%></td>
                        <td class="ladosTablaListado"> <%=b.getPrecio()%></td>
                        <td class="ladosTablaListado"><%=b.getCantidad()%> </td>
                        <td class="ladosTablaListado"></td>
                    </tr>
                    <% }%>
                    <tr>
                        <td colspan="6"> <button type="button" class="delete-row">Borrar bien</button></td>
                    </tr>
                    <tr>
                    <tr>
                        <td height="55" colspan="3" align="center">
                            <input type="submit" name="agregarSolicitud" value="Solicitar">
                        </td>
                        <td height="55" colspan="3" align="center">
                            <input type="submit" name="RegresarEdicion" value="Cancelar" formaction="presentation/users/Admin/Regresar">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
