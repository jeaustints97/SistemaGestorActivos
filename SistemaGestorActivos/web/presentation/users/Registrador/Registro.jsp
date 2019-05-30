<%@page import="SistemaGestorActivos.Logic.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="SistemaGestorActivos.Logic.Bien"%>
<%@page import="SistemaGestorActivos.Logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Registrar Bienes de Solicitud</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <base href="http://localhost:8080/SistemaGestorActivos/">
        <link href="css/RegistradorSol.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>

        <% Solicitud solTemp = (Solicitud) session.getAttribute("solicitud");%>
        <% ArrayList<Bien> bienesTemp = (ArrayList<Bien>) session.getAttribute("bienes");%>

        <div class="ventanaSolicitudReg">
            <form method="POST" name="registroBien" action="presentation/users/Registrador/registrarActivo">
                <br>
                <table border=0 cellpadding=6 cellspacing=8>
                    <tr>
                        <td colspan="7" class="bordeInferior"><h3>Datos de la Solicitud</h3></td>
                    </tr>
                    <tr>
                        <td colspan="7"><br></td>
                    </tr>
                    <tr>
                        <th id="labelComprobante">Comprobante</th>
                        <td><input type="text" id="comprobante" name="comprobante" value="<%= solTemp.getComprobante()%>" disabled></td>
                        <th id="labelFecha">Fecha</th>
                        <td><input type="text" id="fecha" name="fecha" placeholder="DD-MM-YYYY" value="<%= solTemp.getFecha().toLocaleString().substring(0, 11)%>" disabled></td>
                        <th id="labelTipo">Tipo</th>
                        <td>
                            <select name="tipo" disabled>
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
                        <td colspan="7" class="bordeInferior"><h3>Bienes</h3></td>
                    </tr>
                    <tr>
                        <th id="labelDescripcion">Descripcion</th>
                        <th id="labelMarca">Marca</th>
                        <th id="labelModelo">Modelo</th>
                        <th id="labelPrecioU">Precio Unitario</th>
                        <th id="labelCantidad">Cantidad</th>
                        <th id="labelCategoria">Categoria</th>
                        <th id="labelAccion">Accion</th>
                        <td></td>
                    </tr>
                    <% for (int i = 0; i < bienesTemp.size(); i++) {%>
                    <tr class="tablaListado">
                        <td class="ladosTablaListado"><%= bienesTemp.get(i).getDescripcion()%>
                            <input type="hidden" name="idBien" value="<%=bienesTemp.get(i).getId()%>">
                        </td>
                        <td class="ladosTablaListado"> <%=bienesTemp.get(i).getMarca()%></td>
                        <td class="ladosTablaListado"> <%=bienesTemp.get(i).getModelo()%></td>
                        <td class="ladosTablaListado"> <%=bienesTemp.get(i).getPrecio()%></td>
                        <td class="ladosTablaListado"><%=bienesTemp.get(i).getCantidad()%></td>
                        <td class="ladosTablaListado">
                            <select name="<%=bienesTemp.get(i).getId()%>">
                                <% for (Categoria c : (List<Categoria>) session.getAttribute("listaCat")) {%>
                                <option value="<%= c.getId()%>"><%= c.getDescripcion()%></option>
                                <% }%> 
                            </select>
                        </td>
                        <td> <input type="submit" name="registrarBien" value="Registrar"></td>
                    </tr>
                    <% }%> 
                </table>
            </form>
        </div>
        <br><br>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>