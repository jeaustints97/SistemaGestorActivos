function camposSolicitud() {
    if (document.getElementById("fecha").value === "null") {
        document.getElementById("fecha").value = "";
    }
        if (document.getElementById("comprobante").value === "null") {
        document.getElementById("comprobante").value = "";
    }
}


function agregarbien(rowData){
    
    if(validar()){
    $(document).ready(function(){
       $('#agregarBien').click(function(){
          var descripcion = $("#descripcion").val();
          var marca= $("#marca").val();
          var modelo = $("#modelo").val();
          var precioU = $("#precioU").val();
          var cantidad = $("#cantidad").val();
          
          var row = $("<tr />");
          $("#tablaListado").append(row); 
          
          row.append($("<tr><td><input type='checkbox' name='seleccionar'></td>"));
          row.append($("<td>" + rowData.descipcion + "</td>"));
          row.append($("<td>" + rowData.marca + "</td>"));
          row.append($("<td>" + rowData.modelo + "</td>"));
          row.append($("<td>" + rowData.precioU + "</td>"));
          row.append($("<td>" + rowData.cantidad + "</td>"));

       });     
    });
    $(".delete-row").click(function(){
        $("table tbody").find('input[name="seleccionar"]').each(function(){
            if($(this).is(":checked")){
                $(this).parents("tr").remove();
            }
        });
    });
}else{
        //mostrarMensaje("alert alert-danger", "Debe digitar los campos del formulario", "Error!");
        window.alert("Debe digitar los campos del formulario");
        //mostrarModal("myModal", "Error", "Datos equivocados");
}

}




function validar(){
    
    var validacion = true;
    
    //validar campos llenos
    if ($("#descripcion").val() === "" || $("#marca").val() === "" || $("#modelo").val() === "" || $("#precioU").val() === "" || $("#cantidad").val() === ""){
        $("#descripcion").addClass("has-error");
        $("#marca").addClass("has-error");
        $("#modelo").addClass("has-error");
        $("#precioU").addClass("has-error");
        $("#cantidad").addClass("has-error");
        
        validacion = false;
    }
    // validar numeros
    if ( isNaN($("#precioU").val())  || isNaN($("#cantidad").val())){
        $("#precioU").addClass("has-error");
        $("#cantidad").addClass("has-error");
        
        validacion = false;
    }
    
    
    // 
    return validacion;
}

function mostrarMensaje(classCss, msg, neg) {
    //se le eliminan los estilos al mensaje
    $("#mesajeResult").removeClass();

    //se setean los estilos
    $("#mesajeResult").addClass(classCss);

    //se muestra la capa del mensaje con los parametros del metodo
    $("#mesajeResult").fadeIn("slow");
    $("#mesajeResultNeg").html(neg);
    $("#mesajeResultText").html(msg);
    $("#mesajeResultText").html(msg);
}