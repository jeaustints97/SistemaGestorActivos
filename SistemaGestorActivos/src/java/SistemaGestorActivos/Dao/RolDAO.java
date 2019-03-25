package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Rol;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.HibernateException;

public class RolDAO extends HibernateUtil implements IBaseDao<Rol, java.math.BigInteger> {

    @Override
    public void save(Rol o) {
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
    public Rol merge(Rol o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Rol) getSesion().merge(o);
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
    public void delete(Rol o) {
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
    public Rol findById(BigInteger o) {
        Rol rol = null;
        try {
            iniciaOperacion();
            rol = (Rol) getSesion().get(Rol.class, o);
        } finally {
            getSesion().close();
        }
        return rol;
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> roles = null;
        String hql = "from Rol";
        try {
            iniciaOperacion();
            roles = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return roles;
    }

}
