package SistemaGestorActivos.Presentation.Users.Admin.Registrar;


import SistemaGestorActivos.Logic.Bien;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import SistemaGestorActivos.Logic.ModelSolicitudBienes;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;

@WebServlet(name = "Controller", urlPatterns = {"/presentation/register/create","/presentation/register/add"})
public class Controller extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getServletPath().equals("/presentation/register/create"))
        {
            this.createsoli(request,response);
        }
        if(request.getServletPath().equals("/presentation/register/add"))
        {   
            HttpSession session =  request.getSession();
            this.add(request,session,response);
        }
       
    }
    
    protected void createsoli(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /*llamar a la sesion
          tomar la lista que tiene la sesion
          
        crear y setear la solicitud 
        la guardo  
        y luego hacer un for para guardar todos los objetos bienes.
        y luego guardarla biendao.save().    
        */
        ModelSolicitudBienes model = new ModelSolicitudBienes();
        List<Bien> o = null;
        Bien bienes = new Bien();
        Solicitud solicitud = new Solicitud();
        
        model.getSolicitudDAO().getSesion();
        
        try{
            model.getSolicitudDAO().iniciaOperacion();
            model.getSolicitudDAO().getSesion().save(solicitud);
            
            model.getBienDAO().iniciaOperacion();
            
            
        }
        catch (HibernateException he) {
            model.getSolicitudDAO().manejaExcepcion(he);
            model.getBienDAO().manejaExcepcion(he);
            throw he;
        } finally{
            model.getSolicitudDAO().getSesion().close();
            model.getBienDAO().getSesion().close();
        }
        
        request.getRequestDispatcher("/presentation/register/create").forward(request, response); 
    
    }
    
    protected void add(HttpServletRequest request ,HttpSession session, HttpServletResponse response) throws ServletException, IOException{
        //crear bien y setearle los datos que vienen del navegador
        //Preguntar si la lista existe en la sesion 
        //si existe tome la lista, agregue el bien y setee de nuevo la lista en la sesion 
        //si no cree una lista, agregue el bien y setee la lista en la sesion
        
        Bien bienes = new Bien();
        String descripcion = request.getParameter("descripcion");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String precio = request.getParameter("precio");
        String cantidad = request.getParameter("cantidad");
        
        bienes.setDescripcion(descripcion);
        bienes.setMarca(marca);
        bienes.setModelo(modelo);
        bienes.setPrecio(Float.parseFloat(precio));
        bienes.setCantidad(Integer.parseInt(cantidad));
        
        if(session.getAttribute("lista") == null){
          List<Bien> listbienes = new ArrayList<>();
          listbienes.add(bienes);
          session.setAttribute("lista", listbienes);
        }
        else{
            List<Bien> listbienes = (List<Bien>)session.getAttribute("lista");
            listbienes.add(bienes);
            session.setAttribute("lista", listbienes);
        }
        
        request.getRequestDispatcher("/presentation/register/add").forward(request, response);
    
    
    }
    
    void updatemodel(ModelSolicitudBienes model){
        
       
    }
    


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
