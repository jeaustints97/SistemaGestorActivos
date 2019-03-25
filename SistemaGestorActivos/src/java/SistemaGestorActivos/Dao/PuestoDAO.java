package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Puesto;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.HibernateException;

public class PuestoDAO extends HibernateUtil implements IBaseDao<Puesto, java.math.BigInteger> {

    @Override
    public void save(Puesto o) {
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
    public Puesto merge(Puesto o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Puesto) getSesion().merge(o);
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
    public void delete(Puesto o) {
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
    public Puesto findById(BigInteger o) {
        Puesto puesto = null;
        try {
            iniciaOperacion();
            puesto = (Puesto) getSesion().get(Puesto.class, o);
        } finally {
            getSesion().close();
        }
        return puesto;
    }

    @Override
    public List<Puesto> findAll() {
        List<Puesto> puestos = null;
        String hql = "from Puesto";
        try {
            iniciaOperacion();
            puestos = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return puestos;
    }

}