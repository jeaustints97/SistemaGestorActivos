package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Dependencia;
import SistemaGestorActivos.Logic.Estado;
import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class SolicitudDAO extends HibernateUtil implements IBaseDao<Solicitud, Integer> {

    @Override
    public void save(Solicitud o) {
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

    public Integer saveAndGet(Solicitud o) {
        int id;
        try {
            iniciaOperacion();
            getSesion().save(o);
            id = o.getId();
            getTransc().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return id;
    }

    @Override
    public Solicitud merge(Solicitud o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Solicitud) getSesion().merge(o);
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
    public void delete(Solicitud o) {
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
    public Solicitud findById(Integer o) {
        Solicitud solicitud = null;
        try {
            iniciaOperacion();
            solicitud = (Solicitud) getSesion().get(Solicitud.class, o);
        } finally {
            getSesion().close();
        }
        return solicitud;
    }

    @Override
    public List<Solicitud> findAll() {
        List<Solicitud> solicitudes = null;
        String hql = "from Solicitud";
        try {
            iniciaOperacion();
            solicitudes = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return solicitudes;
    }

    public List<Solicitud> getSolicitud(Integer o) {
        List<Solicitud> listaRaw = null;
        List<Solicitud> listaFinal = new ArrayList<>();
        String sql = "select distinct s.id, s.comprobante, s.fecha, s.tipo,s.cantidad,"
                + "s.total,s.estado,s.dependencia\n"
                + "from Solicitud s\n"
                + "where id=" + o + ";";

        try {
            iniciaOperacion();
            listaRaw = (List<Solicitud>) getSesion().createSQLQuery(sql).list();
            Iterator itr = listaRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Solicitud soli = new Solicitud();
                soli.setId(Integer.parseInt(String.valueOf(obj[0])));
                soli.setComprobante(String.valueOf(obj[1]));

                String fecha = String.valueOf(obj[2]);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(fecha);
                soli.setFecha(date1);

                soli.setTipo(String.valueOf(obj[3]));
                soli.setCantidad(Integer.parseInt(String.valueOf(obj[4])));
                soli.setTotal(Float.parseFloat(String.valueOf(obj[5])));

                Estado est = new Estado();
                est.setId(Integer.parseInt(String.valueOf(obj[6])));
                soli.setEstado(est);

                Dependencia dep = new Dependencia();
                dep.setId(Integer.parseInt(String.valueOf(obj[7])));
                soli.setDependencia(dep);

                listaFinal.add(soli);
            }

        } catch (Exception ex) {
        } finally {
            getSesion().close();
        }
        return listaFinal;
    }
}
