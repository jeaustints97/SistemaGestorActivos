            function list(dependencia) {
                var listado = document.getElementById("listadoDependencias");
                listado.innerHTML = "";
                dependencia.forEach((c) => {row(listado, c); });
            }
    
            function row(listado, dependencia) {
                var tr = document.createElement("tr");
                tr.innerHTML = "<td>" + dependencia.id + "</td>" +
                        "<td>" + dependencia.nombre + "</td>" +
                        "<td>" + dependencia.funcionario.nombre + "</td>" +
                        "<td><button type=button class=\"btn btn-success\" data-toggle=modal data-target=#ModalUpdDp onclick='edit(\"" + dependencia.id + "\");'>Modificar</button></td>" +
                        "<td><button type=button class=\"btn btn-danger\" onclick='del(\"" + dependencia.id + "\");'>Eliminar</button></td>";
                listado.appendChild(tr);
            }

            function buscar() {
                var descrip = document.getElementById("filtrado").value;
                ajax({type: "GET", url: "api/dependencias?nombre=" + descrip})
                        .then(function (personas) {list(personas);},
                              function (status) {alert(errorMessage(status));
                    });
            }
            
            function buscarTodos() {
                var descrip = "";
                ajax({type: "GET", url: "api/dependencias?nombre=" + descrip})
                    .then(function (personas) {list(personas);},
                          function (status) {alert(errorMessage(status));});
            }
            
            function cleanAdd(){
                $('.modal').on('hidden.bs.modal', function(){
                $(this).find('form')[0].reset();
                });
            }
            function add() {
                var idDep = document.getElementById("addId").value;
                var nomDep = document.getElementById("addNombre").value;
                var adm = document.getElementById("insadministrador").value;
                if(idDep.length > 0 && nomDep.length > 0 && adm !=="-1"){      
                    cat = { id: idDep,
                            nombre: nomDep,
                            funcionario: [{
                                id: adm
                            }]
                          };
                    // se envia  al servidor
                    ajax({type: "POST", url: "api/dependencias", data: JSON.stringify(cat), contentType: "application/json"})
                    .then(function (){
                        var descrip = "";
                        ajax({type: "GET", url: "api/dependencias?nombre=" + descrip})
                            .then(function (personas) {list(personas);},
                                  function (status) {alert(errorMessage(status));})})
                    alert("Dependencia agregado correctamente"); 
                    $('#ModalDependencia').modal('hide');
                    $('.modal-backdrop').remove();
                }
                else{
                    alert("Todos los campos deben estar completos"); 
                }
            }

            function edit(id) {
            // lo trae del servidor
            ajax({type: "GET",
                url: "api/dependencias/" + id})
                    .then(function (puesto) {show(puesto);},
                          function (status) { alert(errorMessage(status));
                         });
            }
            function show(cat) {
                document.getElementById("updId").value = cat.id;
                document.getElementById("updNombre").value = cat.nombre;
                
            }
            function update(){
                var idDep = document.getElementById("updId").value;
                var nomDep = document.getElementById("updNombre").value;
                var adm = document.getElementById("updadministrador").value;
                if(idDep.length > 0 && nomDep.length > 0 && adm !=="-1"){      
                    cat = { id: idDep,
                            nombre: nomDep,
                            funcionario: [{id: adm}]
                          };
                // se envia  al servidor
                ajax({type: "PUT", url: "api/dependencias", data: JSON.stringify(cat), contentType: "application/json"})
                    .then(function(){
                        var descrip = "";
                        ajax({type: "GET", url: "api/dependencias?nombre=" + descrip})
                            .then(function (personas) {list(personas);},
                            function (status) {alert(errorMessage(status));});
                    });
                }
            }
            function del(id) {
                ajax({type: "DELETE",
                    url: "api/dependencias/" + id})
                    .then(function () {
                        var descrip = "";
                        ajax({type: "GET", url: "api/dependencias?nombre=" + descrip})
                            .then(function (personas) {list(personas);},
                                  function (status) {alert(errorMessage(status));})},
                              function (status) {alert(errorMessage(status));});
            }

            function mostrarFuncionarios(){
                  ajax({type: "GET", url: "api/administradores"})
                        .then(function (funcionarios) {
                            list2(funcionarios);
                        },
                                function (status) {
                                    alert(errorMessage(status));
                                   });

                }
            function row2(listado, funcionario) {
                var option = document.createElement("option");
                option.value = funcionario.id;
                option.text = " ID: "+funcionario.id + " Nombre: " + funcionario.nombre;
                listado.appendChild(option);
            }
                
            
            
            function list2(funcionarios){
                var listado2 = document.getElementById("insadministrador");
                var listado3 = document.getElementById("updadministrador");
                    if(listado2.innerHTML === "" || listado3.innerHTML ==="" ){
                                var option = document.createElement("option");
                                option.value = "-1";
                                option.text = "Seleccione un Administrador";
                                option.setAttribute("selected","");
                                listado2.appendChild(option);
                                listado3.appendChild(option);
                                funcionarios.forEach((d) => {row2(listado2, d);});
                                funcionarios.forEach((d) => {row2(listado3, d);});
                            }


                }    
                
            
            
            
         
            
           
            
            
                
            
                