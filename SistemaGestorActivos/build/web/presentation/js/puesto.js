function list(puestos) {
                var listado = document.getElementById("listadoPuestos");
                listado.innerHTML = "";
                puestos.forEach((c) => {row(listado, c); });
            }

            function row(listado, puesto) {
                var tr = document.createElement("tr");
                tr.innerHTML = "<td>" + puesto.id + "</td>" +
                        "<td>" + puesto.descripcion + "</td>" +
                        "<td><button type=button class=\"btn btn-success\" data-toggle=modal data-target=#ModalUpdPst onclick='edit(\"" + puesto.id + "\");'>Modificar</button></td>" +
                        "<td><button type=button class=\"btn btn-danger\" onclick='del(\"" + puesto.id + "\");'>Eliminar</button></td>";
                listado.appendChild(tr);
            }

            function buscar() {
                var descrip = document.getElementById("filtrado").value;
                ajax({type: "GET", url: "api/puestos?descripcion=" + descrip})
                        .then(function (personas) {list(personas);},
                              function (status) {alert(errorMessage(status));
                    });
            }
            function buscarTodos() {
                var descrip = "";
                ajax({type: "GET", url: "api/puesto?descripcion=" + descrip})
                    .then(function (personas) {list(personas);},
                          function (status) {alert(errorMessage(status));});
            }
            function cleanAdd(){
                $('.modal').on('hidden.bs.modal', function(){
                $(this).find('form')[0].reset();
                });
            }
            function add() {
                var id = document.getElementById("addId");
                var des = document.getElementById("addDescripcion");
                if(id.value.length > 0 && des.value.length > 0 ){      
                    cat = { id: document.getElementById("addId").value,
                            descripcion: document.getElementById("addDescripcion").value
                          };
                    // se envia  al servidor
                ajax({type: "POST", 
                    url: "api/puestos", 
                    data: JSON.stringify(cat), 
                    contentType: "application/json"})
                    .then(function () {
                        var descrip = "";
                        ajax({type: "GET", url: "api/puestos?descripcion=" + descrip})
                            .then(function (personas) {list(personas);},
                                  function (status) {alert(errorMessage(status));})})
                    alert("Puesto agregado correctamente"); 
                    cleanAdd();
                    $('#ModalPuesto').modal('hide');
                    $('.modal-backdrop').remove();
                }
                else{
                    alert("Todos los campos deben estar completos"); 
                }
            }
            function edit(id) {
            // lo trae del servidor
            ajax({type: "GET",
                url: "api/puestos/" + id}) 
                    .then(function (puesto) {show(puesto);},
                          function (status) { alert(errorMessage(status));
                         });
            }
            function show(cat) {
                document.getElementById("updId").value = cat.id;
                document.getElementById("updDescripcion").value = cat.descripcion;
                
            }
            function update(){
                cat = { id:             document.getElementById("updId").value,                        
                        descripcion:    document.getElementById("updDescripcion").value
                      };
                       // se envia  al servidor
                ajax({type: "PUT", 
                    url: "api/puestos", 
                    data: JSON.stringify(cat), 
                    contentType: "application/json"})
                .then(function () {
                   cleanAdd();
                    var descrip = "";
                    ajax({type: "GET", url: "api/puestos?descripcion=" + descrip})
                    .then(function (personas) {list(personas);},
                          function (status) {alert(errorMessage(status));});
                    });
            }
            function del(id) {
                ajax({type: "DELETE",
                    url: "api/puestos/" + id})
                    .then(function () {
                        var descrip = "";
                        ajax({type: "GET", url: "api/puestos?descripcion=" + descrip})
                            .then(function (personas) {list(personas);},
                                  function (status) {alert(errorMessage(status));})},
                              function (status) {alert(errorMessage(status));});
            }