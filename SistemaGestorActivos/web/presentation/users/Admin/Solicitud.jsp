<%-- 
    Document   : Solicitud
    Created on : Mar 23, 2019, 4:29:40 PM
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <title>Administración de Solicitudes</title>
   </head>
    <body>

        <!-- ********************************************************** -->
        <!-- ********************************************************** -->
        <!-- Modal del BootsTrap para mostrar mensajes                  -->
        <!-- ********************************************************** -->
        <!-- ********************************************************** -->
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
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="myModalTitle">Insertar / Modificar Solicitudes </h4>
                    </div>
                    <div class="modal-body" id="myModalMessage">
                        <form role="form" onsubmit="return false;" id="formConductores">
                          
                            <div class="form-group" id="groupCedula">
                                <label for="comprobante">Comprobante:</label>
                                <input type="text" class="form-control" id="comprobante" autofocus="true" placeholder="Comprobante">
                            </div>

                            <div class="form-group" id="groupestado">
                                <label for="nombre">Estado:</label>
                                <input type="text" class="form-control" id="estado" placeholder="Estado" >
                            </div>
                            
                            <div class="form-group" id="groupApellidos">
                                <label for="apellidos">Fecha:</label>
                                <input type="text" class="form-control" id="fecha" placeholder="Fecha">
                            </div>

                            <div class="form-group" id="groupCantidad">
                                <label for="TA">Cantidad:</label>
                                <input type="text" class="form-control" id="cantidad" placeholder="Cantidad">
                            </div>
                            
                            <div class="form-group" id="groupMonto">
                                <label for="Monto">Monto:</label>
                                <input type="text" class="form-control" id="monto" placeholder="Monto">
                            </div>

                            

                            <div class="form-group">
                                <input type="hidden" value="agregarConductor" id="ConductoresAction"/>
                                <button type="submit" class="btn btn-primary" id="enviar">Guardar</button>
                                <button type="reset" class="btn btn-danger" id="cancelar">Cancelar</button>
                            </div>

                            <div class="form-group height25" >
                                <div class="alert alert-success hiddenDiv" id="mesajeResult">
                                    <strong id="mesajeResultNeg">Info!</strong> 
                                    <span id="mesajeResultText">This alert box could indicate a neutral informative change or action.</span>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- ********************************************************** -->
        <div id="myDiv">
            <!--HEADER-->
            <div class="header">
                <div class="bg-color">
                    <header id="main-header">
                        <nav class="navbar navbar-default navbar-fixed-top">
                            <div class="container">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                    <a class="navbar-brand" href="#">Man<span class="logo-dec">tenimientos</span></a>
                                </div>
                                <div class="collapse navbar-collapse" id="myNavbar">

                                    <ul class="nav navbar-nav navbar-right">
                                        <li class=""><a href="Principal.jsp">Inicio</a></li>
                                        <li class=""><a href="#UsuarioMante.jsp">Usuarios</a></li>
                                        <li class=""><a href="VehiculosMante.jsp">Vehiculos</a></li>
                                        <li class="active"><a href="ConducorMante.jsp">Conductores</a></li>
                                    </ul>

                                </div>
                            </div>
                        </nav>
                    </header>
                    <div class="wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="banner-info text-center wow fadeIn delay-05s">
                                    <h1 class="bnr-title">Bienvenido Administardor</h1>
                                    <h2 class="bnr-sub-title"><%out.print(sesion.getAttribute("Nombre"));%></h2>
                                    <p class="bnr-para">Ingresaste a la zona de mantenimientos de <span class="logo-dec">Cabify</span><br>Seleciona un elemento <br>para modificar o editar</p>
                                    <div class="overlay-detail">
                                        <a href="#feature"><i class="fa fa-angle-down"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/ HEADER-->
            <!---->

            <section id="ManVeiculo" class="section-padding wow fadeInUp delay-05s">
                <div class="container">
                    <div class="row">
                        <div class="container">
                            <div class="page-header">
                                <h1><small><span class="logo-dec"></span>Sistema para la administración</small></h1>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="row">
                                        <!-- ********************************************************** -->
                                        <!-- COLUMNA DEL MENU -->
                                        <!-- ********************************************************** -->
                                        <div class="col-md-4">
                                            <div class="dropdown">
                                                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Mantenimientos
                                                    <span class="caret"></span></button>
                                                <ul class="dropdown-menu">
                                                    <li><a href="#">Mantenimiento Usuarios</a></li>
                                                    <li><a href="#">Mantenimiento Vehiculos</a></li>
                                                    <li><a href="#">Mantenimiento Conductores</a></li>
                                                    <li class="divider"></li>
                                                    <li><a href="Principal.jsp">Cerrar Sesión</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <!-- ********************************************************** -->
                                        <!-- COLUMNA DEL MENU -->
                                        <!-- ********************************************************** -->

                                        <!-- ********************************************************** -->
                                        <!-- COLUMNA DEL BOTON DE CERRAR SESION -->
                                        <!-- ********************************************************** -->
                                        <div class="col-md-4" style="text-align: right;">
                                            <a class="btn btn-warning" href="Principal.jsp" role="button">
                                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                                Cerrar Sesión
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading"><h3>Mantenimiento de Conductores</h3></div>
                                <div class="panel-body">
                                    <center>
                                        <button type="button" class="btn btn-primary centered" data-toggle="modal" data-target="#myModalConductor" id="btMostarFormConductor">Insertar Conductor</button>
                                    </center><br>
                                    <!-- ********************************************************** -->
                                    <div class="col-sm-12">
                                        <form role="form" onsubmit="return false;" id="formConductor" class="form-horizontal centered">
                                            <div class="form-group" id="groupFiltroConductor">
                                                <div class="col-sm-4" style="text-align: right; vertical-align: middle;">
                                                    <p><b>Buscar por id de conductor</b></p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" id="FiltroConductor" placeholder="Digite el id del conductor">
                                                </div>
                                                <div class="col-sm-4">
                                                    <button type="button" class="btn btn-info centered" data-toggle="modal" data-target="" id="btBuscarConductor">
                                                        Buscar <span class="glyphicon glyphicon-search"></span>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- ********************************************************** -->

                                    <table class="table table-hover table-condensed" id="tablaConductor"></table>

                                </div>
                                <div class="panel-footer">Nota: Acciones validas dependeran del rol del usuario</div>
                            </div>
                        </div> 
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>
