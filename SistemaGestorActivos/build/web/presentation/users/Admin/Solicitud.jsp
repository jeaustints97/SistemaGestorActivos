<%-- 
    Document   : Solicitud
    Created on : Mar 23, 2019, 4:29:40 PM
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <link href="css/Estilos.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <title>Administración de Solicitudes</title>
   </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="myModalTitle">Administrar</h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <p>Bienvenido</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- ********************************************************** -->
        <!-- Modal del BootsTrap formulario del ingreso de conductores                  -->

        <div class="modal fade" id="myModalSolicitud" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalTitle">Crear una solicitud </h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <form role="form" action="presentation/register/create" method="POST">
                          
                            <div class="form-group" id="groupCedula">
                                <label for="comprobante">Comprobante:</label>
                                <input type="text" class="form-control" id="comprobante" name="comprobante"autofocus="true" placeholder="Comprobante">
                            </div>

                            
                            <div class="form-group" id="groupApellidos">
                                <label for="apellidos">Fecha:</label>
                                <input type="text" class="form-control" id="fecha" name="fecha" placeholder="Fecha">
                            </div>
                            
                            <div class="form-group" id="groupTipo">
                                <label for="Tipo">Tipo:</label>
                                <select class="form-control" name="tipo" >
                                    <option value="donacion" name="donacion" selected="donacion">Donación</option>
                                    <option value="compra" name="compra">Compra</option>
                                    <option value="produccion" name="produccion">Producción</option>
                                </select>
                            
                            </div>
                            
                            <div class="form-group">
                                <input type="submit" class="btn btn-info" value="Guardar Solicitud"  name="Guardar"action="SolicitudesAction"/>
                            </div>
                            
                        </form>
                        
                        
                        <form role="form" action="presentation/register/add" >
                            <div class="form-group">
                            <div class="form-group row"> 
                           &nbsp
                           <div class="col-xs-1" id="groupDescripcion">
                                <label for="TA">Descripción:</label>
                                <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción">
                            </div>
                            &nbsp
                            <div class="col-xs-1" id="groupMarca">
                                <label for="Monto">Marca:</label>
                                <input type="text" class="form-control" id="monto" name="monto" placeholder="Marca">
                            </div>
                            &nbsp     
                            <div class="col-xs-1" id="groupModelo">
                                <label for="Modelo">Modelo:</label>
                                <input type="text" class="form-control" id="modelo" name="modelo" placeholder="Modelo">
                            </div>    
                           &nbsp &nbsp      
                            <div class="col-xs-1" id="groupPrecio">
                                <label for="Precio">Precio:</label>
                                <input type="text" class="form-control" id="precio" name="precio" placeholder="Precio">
                            </div>
                           &nbsp     
                            <div class="col-xs-1" id="groupCantidad">
                                <label for="Cantidad">Cantidad:</label>
                                <input type="text" class="form-control" id="cantidad" name="cantidad" placeholder="Cantidad">
                            </div>
                            
                           <div class="form-group">
                               <input type="hidden" value="agregar Solicitud" name="agregar" id="SolicitudesAction"/>
                                <button type="submit" class="btn btn-info" id="agregar">Agregar bien</button>
                            </div>
                            
                            </div>
                            </div> 
                        </form>
                        
                            <table class="table table-hover table-condensed" id="tablaSolicitud" name="tablaSolicitud">
                                        <th>
                                            Descripción
                                        </th>
                                     
                                        <th>
                                            Marca
                                        </th>
                                        <th>
                                            Modelo
                                        </th>
                                        <th>
                                            Precio Unitario
                                        </th>
                                        <th>
                                            Cantidad
                                        </th>
                                        
                                        <tr>
                                            <td>1</td>

                                        </tr>
                                        <tr>
                                            <td>1</td>
                                           
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                           
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                           
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                           
                                        </tr>

                                    </table>
                        
                            <div class="form-group">
                                <input type="hidden" value="agregarSolicitud" id="ConductoresAction"/>
                                <button type="submit" class="btn btn-primary" id="enviar">Guardar</button>
                                <button type="reset" class="btn btn-danger" id="cancelar">Cancelar</button>
                            </div>

                            <div class="form-group height25" >
                                <div class="alert alert-success hiddenDiv" id="mesajeResult">
                                    <strong id="mesajeResultNeg">Estás en el área de guardar una solicitud.</strong> 
                                    <span id="mesajeResultText">Por favor guarda una solicitud</span>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- ********************************************************** -->
        <div id="myDiv">
           <section id="ManVeiculo" class="section-padding wow fadeInUp delay-05s">
                <div class="container">
                    <div class="row">
                        <div class="container">
                            <div class="page-header">
                                <h1><small><span class="logo-dec"></span>Mantenimiento para la Solicitudes</small></h1>
                            </div>
                           
                            <div class="panel panel-primary">
                                <div class="panel-body">
                                    <center>
                                        <button type="button" class="btn btn-primary float-right" data-toggle="modal" data-target="#myModalSolicitud" id="btMostarFormConductor">Insertar Solicitud</button>
                                    </center><br>
                                    <!-- ********************************************************** -->
                                    <div class="col-sm-12">
                                        <form role="form" onsubmit="return false;" id="formConductor" class="form-horizontal centered">
                                            <div class="form-group" id="groupFiltroConductor">
                                                <div class="col-sm-4" class="Centered ">
                                                    <p><b>Buscar por comprobante de solicitud</b></p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" id="FiltroSolicitud" placeholder="Dígite el comprobante de la solicitud">
                                                    <br>
                                                    <button type="button" class="btn btn-info " id="btBuscarSolicitud">
                                                        Buscar 
                                                    </button> 
                                                </div>
                                            
                                            </div>
                                            
                                        </form>
                                    
                                    <!-- ********************************************************** -->

                                    <table class="table table-hover table-condensed" id="tablaSolicitud">
                                        <th>
                                            Descripción
                                        </th>
                                     
                                        <th>
                                            Marca
                                        </th>
                                        <th>
                                            Modelo
                                        </th>
                                        <th>
                                            Precio Unitario
                                        </th>
                                        <th>
                                            Cantidad
                                        </th>
                                    </table>

                                </div>
                                
                            </div>
                        </div> 
                    </div>
                </div>
            </section>
         <%@ include file="/presentation/footer.jsp" %>
        </div>
        
    </body>
    
</html>
