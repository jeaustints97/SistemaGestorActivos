package SistemaGestorActivos.Test;

import SistemaGestorActivos.Logic.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
//        List<Usuario> lista = null;
//        lista = Model.instance().getUsuarioDAO().findAll();
//        System.out.println("Lista de Usuarios(ID)");
//        for (Usuario u : lista) {
//            System.out.println(u.getId());
//        }
//
//        System.out.println("Lista de Dependencias(codigo)");
//        List<Dependencia> lista2 = null;
//        lista2 = Model.instance().getDependenciaDAO().findAll();
//        for (Dependencia d : lista2) {
//            System.out.println(d.getCodigo());
//        }
//
//        System.out.println("Lista de Solicitudes(Numero)");
//        List<Solicitud> lista3 = null;
//        lista3 = Model.instance().getSolicitudDAO().findAll();
//        for (Solicitud s : lista3) {
//            System.out.println(s.getNumero());
//        }

        List<Solicitud> lista = null;
        lista = Model.instance().getUsuarioDAO().getSolicitudes("1");
        System.out.println("Lista de Solicitudes del ID 1");
        System.out.println(lista.get(0).getId());
//        for (Solicitud u : lista) {
//            System.out.println(u.getId());
//        }

    }
}
