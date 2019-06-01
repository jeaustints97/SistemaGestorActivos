/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.categorias;

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
import SistemaGestorActivos.Logic.Categoria;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;

@Path("/categorias")
public class Categorias {

    @Context
    HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Categoria c) {
        try {
            int consecutivoInicial = 1;
            c.setConsecutivo(consecutivoInicial);
            Model.instance().agregarCategoria(c);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Categoria get(@PathParam("id") int id) {
        try {
            return Model.instance().getCategoriaDAO().find(id).get(0);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> del(@PathParam("id") int id) {
        try {
            Model.instance().eliminarCategoria(id);
            String all="";
            return Model.instance().obtenerCategorias(all);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Categoria> list(@QueryParam("descripcion") String descripcion) {
        List<Categoria> ps;
        ps = Model.instance().obtenerCategorias(descripcion);
        return ps;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Categoria c) {
        try {
            Model.instance().actualizarCategoria(c);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
