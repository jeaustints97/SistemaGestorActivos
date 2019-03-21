package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Activo;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;

public class ActivoDAO extends HibernateUtil implements IBaseDao<Activo, java.math.BigInteger> {

    @Override
    public void save(Activo o) {
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
    public Activo merge(Activo o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Activo) getSesion().merge(o);
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
    public void delete(Activo o) {
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
    public Activo findById(BigInteger o) {
        Activo activo = null;
        try {
            iniciaOperacion();
            activo = (Activo) getSesion().get(Activo.class, o);
        } finally {
            getSesion().close();
        }
        return activo;
    }

    @Override
    public List<Activo> findAll() {
        List<Activo> activos = null;
        String hql = "from Activo";
        try {
            iniciaOperacion();
            activos = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return activos;
    }

}
