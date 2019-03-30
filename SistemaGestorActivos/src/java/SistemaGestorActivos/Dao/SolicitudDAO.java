package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;

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
}
