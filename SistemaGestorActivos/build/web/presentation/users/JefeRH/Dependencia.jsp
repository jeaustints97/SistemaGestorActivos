<%-- 
    Document   : Dependencia
    Created on : Jun 2, 2019, 12:42:00 AM
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Bienvenido Jefe de RRHH</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <base href="http://localhost:8080/SistemaGestorActivos/">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="presentation/js/ajaxPromise.js"></script>
        <script type="text/javascript" src="presentation/js/dependencia.js"></script>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
       <!-- Modal Ingreso Dependencia -->
        <div class="modal" id="ModalDependencia">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">

                    <!-- Header del Modal -->
                    <div class="modal-header">
                        <h4 class="modal-title">Ingresar Dependencia</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- Cuerpo del Modal -->
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label" for="sm">Id</label>
                                <div class="col-sm-8"><input id ="addId" class="form-control" type="text" required></div>                     
                                <label class="col-sm-2 control-label" for="sm">Nombre</label>
                                <div class="col-sm-8"><input id ="addNombre" class="form-control" type="text" required></div>
                                <label class="col-sm-2 control-label" for="sm">Admnistrador</label>
                                <select id="insadministrador" class="form-control" onclick="mostrarFuncionarios()"></select>
                              
                            </div>
                        </form>
                    </div>
                    <!-- Footer del Modal -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="add()">Agregar</button>
                        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal Update Categoria  -->
        <div class="modal" id="ModalUpdDp">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Header del Modal -->
                    <div class="modal-header">
                        <h4 class="modal-title">Modificacion de Puestos</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Cuerpo del Modal -->
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label" for="sm" disabled="">Id</label>
                                <div class="col-sm-8"><input id ="updId" class="form-control" type="text" ></div>   
                                <label class="col-sm-2 control-label" for="sm">Nombre</label>
                                <div class="col-sm-8"><input id ="updNombre" class="form-control" type="text"></div>
                               
                                <label class="col-sm-2 control-label" for="sm">Administrador</label>
                                    <select id="updadministrador" class="form-control" onclick="mostrarFuncionarios()"></select>
                                </div>
                       </form>
                        
                        </div>
                    <div class="modal-footer">
                      
                        <button type="button" class="btn btn-success" data-dismiss="modal" onclick="update()">Guardar</button>
                        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                    </div>

                    <!-- Footer del Modal -->
                    

                </div>
            </div>
        </div>

        <div class="container">
            <h1 style="text-align: center">Listado de Dependencias</h1>
            <br>
            <div class="row justify-content-center">
                <div class="col-12 col-md-10 col-lg-8">
                    <div class="card card-sm">
                        <div class="card-body row no-gutters align-items-center">
                            <div class="col">
                                <input id ="filtrado" name="filtrado" class="form-control form-control-lg form-control-borderless" type="search" placeholder="Nombre">
                            </div>
                            <!--end of col-->
                            &nbsp;&nbsp;&nbsp;
                            <div class="col-auto">
                                <button type="button" class="btn btn-info" onclick="buscar()">Buscar</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ModalDependencia">Agregar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="ventanaSolicitud">
            <br>
            <table class="table table-bordered table-striped mb-0">
                <thead>
                    <tr> <th>Id</th>
                        <th>Nombre</th>
                        <th>Administrador</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody id="listadoDependencias"></tbody>
            </table>
            <br>
        </div>
    <%@ include file="/presentation/footer.jsp" %>
    </body>
     
</html>