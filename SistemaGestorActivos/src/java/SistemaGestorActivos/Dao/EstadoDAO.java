package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Estado;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.HibernateException;

public class EstadoDAO extends HibernateUtil implements IBaseDao<Estado, Integer> {

    @Override
    public void save(Estado o) {
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
    public Estado merge(Estado o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Estado) getSesion().merge(o);
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
    public void delete(Estado o) {
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
    public Estado findById(Integer o) {
        Estado estado = null;
        try {
            iniciaOperacion();
            estado = (Estado) getSesion().get(Estado.class, o);
        } finally {
            getSesion().close();
        }
        return estado;
    }

    @Override
    public List<Estado> findAll() {
        List<Estado> estados = null;
        String hql = "from Estado";
        try {
            iniciaOperacion();
            estados = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return estados;
    }

}
