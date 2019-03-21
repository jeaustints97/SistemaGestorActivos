package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Labora;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;

public class LaboraDAO extends HibernateUtil implements IBaseDao<Labora, java.math.BigInteger> {

    @Override
    public void save(Labora o) {
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
    public Labora merge(Labora o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Labora) getSesion().merge(o);
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
    public void delete(Labora o) {
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
    public Labora findById(BigInteger o) {
        Labora labora = null;
        try {
            iniciaOperacion();
            labora = (Labora) getSesion().get(Labora.class, o);
        } finally {
            getSesion().close();
        }
        return labora;
    }

    @Override
    public List<Labora> findAll() {
        List<Labora> laboras = null;
        String hql = "from Labora";
        try {
            iniciaOperacion();
            laboras = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return laboras;
    }

}
