/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaGestorActivos.Logic;

import SistemaGestorActivos.Dao.SolicitudDAO;
import SistemaGestorActivos.Dao.BienDAO;

/**
 *
 * @author Marco
 */
public class ModelSolicitudBienes {
    
    private SolicitudDAO solicitudDAO;
    private BienDAO bienDAO;

    private static ModelSolicitudBienes uniqueInstance;
    
    public static ModelSolicitudBienes instance(){
        if( uniqueInstance == null){
            uniqueInstance = new ModelSolicitudBienes();
        }
        return uniqueInstance;
    }

    public ModelSolicitudBienes(){
        solicitudDAO = new SolicitudDAO();
        bienDAO = new BienDAO();
    
    }

    public SolicitudDAO getSolicitudDAO() {
        return solicitudDAO;
    }

    public void setSolicitudDAO(SolicitudDAO solicitudDAO) {
        this.solicitudDAO = solicitudDAO;
    }

    public BienDAO getBienDAO() {
        return bienDAO;
    }

    public void setBienDAO(BienDAO bienDAO) {
        this.bienDAO = bienDAO;
    }
    
}
