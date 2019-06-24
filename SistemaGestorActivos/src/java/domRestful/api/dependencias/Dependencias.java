/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.dependencias;

/**
 *
 * @author Marco
 */

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import SistemaGestorActivos.Logic.Model;
import SistemaGestorActivos.Logic.Dependencia;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;


@Path("/dependencias")
public class Dependencias {
    
    
    @Context
    HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Dependencia c) {
        try {
            Model.instance().agregarDependencia(c);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Dependencia get(@PathParam("id") int id) {
        try {
            return Model.instance().getDependenciaDAO().find(id).get(0);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Dependencia> del(@PathParam("id") int id) {
        try {
            Model.instance().eliminarDependencia(id);
            String all="";
            return Model.instance().ObtenerDependencias(all);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Dependencia> list(@QueryParam("nombre") String nombre) {
        List<Dependencia> dp;
        dp = Model.instance().ObtenerDependencias(nombre);
        return dp;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Dependencia c) {
        try {
            Model.instance().getDependenciaDAO().merge(c);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
