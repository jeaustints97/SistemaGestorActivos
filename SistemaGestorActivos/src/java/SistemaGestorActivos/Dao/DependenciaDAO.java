package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Dependencia;
import SistemaGestorActivos.Utils.HibernateUtil;

public class DependenciaDAO extends HibernateUtil implements IBaseDao<Dependencia, String> {

    @Override
    public void save(Dependencia o) {
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
    public Dependencia merge(Dependencia o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Dependencia) getSesion().merge(o);
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
    public void delete(Dependencia o) {
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
    public Dependencia findById(String o) {
        Dependencia dependencia = null;
        try {
            iniciaOperacion();
            dependencia = (Dependencia) getSesion().get(Dependencia.class, o);
        } finally {
            getSesion().close();
        }
        return dependencia;
    }

    @Override
    public List<Dependencia> findAll() {
        List<Dependencia> dependencias = null;
        String hql = "from Dependencia";
        try {
            iniciaOperacion();
            dependencias = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return dependencias;
    }

}
