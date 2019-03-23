function camposLogin() {
    if (document.getElementById("idUsuario").value === "null") {
        document.getElementById("idUsuario").value = "";
    }
    if (document.getElementById("clave").value === "null") {
        document.getElementById("clave").value = "";
    }
}