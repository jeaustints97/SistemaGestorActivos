/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domRestful.api.puestos;

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
import SistemaGestorActivos.Logic.Puesto;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;


@Path("/puestos")
public class Puestos {
    @Context
    HttpServletRequest request;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    
    public void add(Puesto c){
        try{
            Model.instance().agregarPuesto(c);
        }
        catch (Exception e){
            throw new NotAcceptableException();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Puesto get(@PathParam("id") int id){
        try {
            return Model.instance().ObtenerPuesto(id);
        } catch (Exception e) {
            throw new NotFoundException("Error al encontrar un cliente");
        }
    }
}
