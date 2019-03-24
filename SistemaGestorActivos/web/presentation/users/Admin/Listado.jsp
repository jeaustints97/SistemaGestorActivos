<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="boxed">
            <br>

            <div class="container">
                <br/>
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-8">
                        <form class="card card-sm">
                            <div class="card-body row no-gutters align-items-center">
                                <div class="col-auto">
                                    <i class="fas fa-search h4 text-body"></i>
                                </div>
                                <!--end of col-->
                                <div class="col">
                                    <input class="form-control form-control-lg form-control-borderless" type="search" placeholder="Search topics or keywords">
                                </div>
                                <!--end of col-->
                                <div class="col-auto">
                                    <button class="btn btn-lg btn-success" type="submit">Search</button>
                                </div>
                                <!--end of col-->
                            </div>
                        </form>
                    </div>
                    <!--end of col-->
                </div>
            </div>

            <table>
                <tr>
                    <th>Numero</th>
                    <th>Comprobante</th>
                    <th>Fecha</th>
                    <th>Tipo</th>
                    <th>Cantidad</th>
                    <th>Monto</th>
                    <th>estado</th>
                    <th>Realizar Cambios</th>
                </tr>
                <tr>
                    <td>01</td>
                    <td>A1</td>
                    <td>23/03/2019</td>
                    <td>Compra</td>
                    <td>1</td>
                    <td>1000</td>
                    <td>Recibida</td>
                    <td>Realizar Cambios</td>
                </tr>
                <tr>
                    <td>02</td>
                    <td>A1</td>
                    <td>23/03/2019</td>
                    <td>Compra</td>
                    <td>2</td>
                    <td>1200</td>
                    <td>Recibida</td>
                    <td>Realizar Cambios</td>
                </tr>


            </table>
            <br>
        </div>
        <br>
        <%@ include file="/presentation/footer.jsp" %>
    </body>
</html>
