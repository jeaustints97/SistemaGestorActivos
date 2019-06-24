/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.funcionarios;

import SistemaGestorActivos.Logic.Dependencia;
import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Logic.Model;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marco
 */

@Path("/funcionarios")
public class Funcionarios {
    @Context
    HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Funcionario c) {
        try {
            Model.instance().agregarFuncionario(c);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Funcionario get(@PathParam("id") int id) {
        try {
            return Model.instance().getFuncionarioDAO().findFuncionario(id).get(0);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Funcionario> del(@PathParam("id") int id) {
        try {
            Model.instance().eliminarFuncionario(id);
            String all="";
            return Model.instance().ObtenerFuncionario(all);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Funcionario> list(@QueryParam("nombre") String nombre) {
        List<Funcionario> dp;
        dp = Model.instance().ObtenerFuncionario(nombre);
        return dp;
    }
    

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Funcionario c) {
        try {
            Model.instance().actualizarFuncionario(c);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
