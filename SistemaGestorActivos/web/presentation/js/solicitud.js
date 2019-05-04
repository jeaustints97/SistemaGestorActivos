function camposSolicitud() {
    if (document.getElementById("fecha").value === "null") {
        document.getElementById("fecha").value = "";
    }
        if (document.getElementById("comprobante").value === "null") {
        document.getElementById("comprobante").value = "";
    }
}


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function agregarbien(){
    
    if(validar()){
    $(document).ready(function(){
       $('#agregarBien').click(function(){
          var descripcion = $("#descripcion").val();
          var marca= $("#marca").val();
          var modelo = $("#modelo").val();
          var precioU = $("#precioU").val();
          var cantidad = $("#cantidad").val();
          
          var markup = "<tr><td><input type='checkbox' name='seleccionar'></td>\n\
            <td>" + descripcion + "</td>\n\
            <td>" + marca + "</td>\n\
            <td>"+modelo+"</td>\n\
            <td>"+precioU+"</td>\n\
            <td>"+cantidad+"</td></tr>";
           $("table tbody").append(markup);
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
    window.alert("Error en la operación");
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
