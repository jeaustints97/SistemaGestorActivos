package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class BienDAO extends HibernateUtil implements IBaseDao<Bien, Integer> {

    @Override
    public void save(Bien o) {
        try {
            iniciaOperacion();
            getSesion().save(o);
            getTransc().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
    }

    @Override
    public Bien merge(Bien o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Bien) getSesion().merge(o);
            getTransc().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return o;
    }

    @Override
    public void delete(Bien o) {
        try {
            iniciaOperacion();
            getSesion().delete(o);
            getTransc().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }

    }

    @Override
    public Bien findById(Integer o) {
        Bien bien = null;
        try {
            iniciaOperacion();
            bien = (Bien) getSesion().get(Bien.class, o);
        } finally {
            getSesion().close();
        }
        return bien;
    }

    @Override
    public List<Bien> findAll() {
        List<Bien> bienes = null;
        String hql = "from Bien";
        try {
            iniciaOperacion();
            bienes = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return bienes;
    }

    public List<Bien> getBienesBySolicitud(int idDependencia, int idSolicitud) {
        List<Bien> listaRaw = null;
        List<Bien> listaFinal = new ArrayList<>();
        String sql = "select distinct b.id, b.descripcion, b. marca,b.modelo,b.precio,b.cantidad,b.solicitud\n"
                + "from Dependencia d, Bien b,Solicitud s\n"
                + "where " + idDependencia + " =s.dependencia and b.solicitud= " + idSolicitud + ";";

        try {
            iniciaOperacion();
            listaRaw = (List<Bien>) getSesion().createSQLQuery(sql).list();
            Iterator itr = listaRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Bien bien = new Bien();
                bien.setId(Integer.parseInt(String.valueOf(obj[0])));
                bien.setDescripcion(String.valueOf(obj[1]));
                bien.setMarca(String.valueOf(obj[2]));
                bien.setModelo(String.valueOf(obj[3]));
                bien.setPrecio(Float.parseFloat(String.valueOf(obj[4])));
                bien.setCantidad(Integer.parseInt(String.valueOf(obj[5])));
                
                Solicitud s= new Solicitud();
                s.setId(Integer.parseInt(String.valueOf(obj[6])));
                bien.setSolicitud(s);

                listaFinal.add(bien);
            }

        } catch (Exception ex) {
        } finally {
            getSesion().close();
        }
        return listaFinal;
    }
}
