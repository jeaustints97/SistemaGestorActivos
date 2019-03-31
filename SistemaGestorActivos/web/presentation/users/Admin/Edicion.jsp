<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="SistemaGestorActivos.Logic.Bien"%>
<%@page import="SistemaGestorActivos.Logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
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
            <form method="POST" name="edicionSolicitud" action="presentation/users/Admin/GuardarCambiosEdicion">
                <br>
                <table border=0 cellpadding=6 cellspacing=8>
                    <tr>
                        <td colspan="6" class="bordeInferior"><h3>Editar Solicitud</h3></td>
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
                            <% } %>
                        <th id="labelTipo">Tipo</th>
                        <td>
                            <select name="tipo">
                                <%if (solTemp.getTipo().equals("")) { %>
                                <option value="inv" selected>Tipo</option>
                                <option value="Compra">Compra</option>
                                <option value="Donacion">Donacion</option>
                                <option value="Produccion">Produccion</option>
                                <% } else {%>
                                <option value="<%=solTemp.getTipo()%>" selected><%=solTemp.getTipo()%></option>
                                <% } %>
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
                        <td></td>
                    </tr>
                    <tr>
                        <td><input size=13 type="text" name="descripcion" placeholder="Descripcion" value=""></td>
                        <td><input size=13 type="text" name="marca" placeholder="Marca" value=""></td>
                        <td><input size=13 type="text" name="modelo" placeholder="Modelo" value=""></td>
                        <td><input type="number" name="precioU" step="0.01" placeholder="0.0" value=""></td>
                        <td><input type="number" name="cantidad" placeholder="0" value=""></td>
                            <%if (solTemp.getEstado().getId() == 1) { %>
                        <td> <input type="submit" name="agregarBien" formaction="presentation/users/Admin/agregarBienEdicion" value="Agregar"> </td>
                            <% } else {%>
                        <td> <input type="submit" name="agregarBien" formaction="presentation/users/Admin/agregarBienEdicion" value="Agregar" disabled></td>
                            <% } %>
                    </tr>
                    <tr>
                        <td colspan="6" class="bordeInferior"><h3>Listado</h3></td>
                    </tr>
                    <tr>
                        <td colspan="6"><br></td>
                    </tr>
                    <% for (int i = 0; i < bienesTemp.size(); i++) {%>
                    <tr class="tablaListado">
                        <td class="ladosTablaListado"><%= bienesTemp.get(i).getDescripcion()%></td>
                        <td class="ladosTablaListado"> <%=bienesTemp.get(i).getMarca()%></td>
                        <td class="ladosTablaListado"> <%=bienesTemp.get(i).getModelo()%></td>
                        <td class="ladosTablaListado"> <%=bienesTemp.get(i).getPrecio()%></td>
                        <td class="ladosTablaListado"><%=bienesTemp.get(i).getCantidad()%> </td>
                        <%if (solTemp.getEstado().getId() == 1) {%>
                        <td class="ladosTablaListado"><input type="submit" formaction="presentation/users/Admin/BorrarBien?consecutivo=<%= i%>" value="Borrar"></td>
                            <% } else {%>
                        <td class="ladosTablaListado"><input type="submit" formaction="presentation/users/Admin/BorrarBien?consecutivo=<%= i%>" value="Borrar" disabled></td>
                            <% } %>
                    </tr>
                    <% }%> 
                    <tr>
                        <td colspan="6"><br></td>
                    </tr>
                    <tr>
                        <td height="55" colspan="3" align="center">
                            <%if (solTemp.getEstado().getId() == 1) { %>
                            <input type="submit" name="agregarSolicitud" value="Guardar Cambios">
                            <% } else {%>
                            <input type="submit" name="agregarSolicitud" value="Guardar Cambios" disabled>
                            <% }%>
                        </td>
                        <td height="55" colspan="3" align="center">
                            <input type="submit" name="RegresarEdicion" value="Regresar" formaction="presentation/users/Admin/Regresar">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>