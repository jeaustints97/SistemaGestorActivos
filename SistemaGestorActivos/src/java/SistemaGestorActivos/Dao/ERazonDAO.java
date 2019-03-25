package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Erazon;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.HibernateException;

public class ERazonDAO extends HibernateUtil implements IBaseDao<Erazon, java.math.BigInteger> {

    @Override
    public void save(Erazon o) {
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
    public Erazon merge(Erazon o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Erazon) getSesion().merge(o);
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
    public void delete(Erazon o) {
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
    public Erazon findById(BigInteger o) {
        Erazon erazon = null;
        try {
            iniciaOperacion();
            erazon = (Erazon) getSesion().get(Erazon.class, o);
        } finally {
            getSesion().close();
        }
        return erazon;
    }

    @Override
    public List<Erazon> findAll() {
        List<Erazon> erazons = null;
        String hql = "from Erazon";
        try {
            iniciaOperacion();
            erazons = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return erazons;
    }

}
