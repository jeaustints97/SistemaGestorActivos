package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;

public class BienDAO extends HibernateUtil implements IBaseDao<Bien, java.math.BigInteger> {

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
    public Bien findById(BigInteger o) {
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
}
