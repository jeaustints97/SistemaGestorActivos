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
