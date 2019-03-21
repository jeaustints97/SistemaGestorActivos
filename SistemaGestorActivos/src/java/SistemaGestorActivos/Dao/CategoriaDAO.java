package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Categoria;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;

public class CategoriaDAO extends HibernateUtil implements IBaseDao<Categoria, java.math.BigInteger> {

    @Override
    public void save(Categoria o) {
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
    public Categoria merge(Categoria o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Categoria) getSesion().merge(o);
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
    public void delete(Categoria o) {
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
    public Categoria findById(BigInteger o) {
        Categoria categoria = null;
        try {
            iniciaOperacion();
            categoria = (Categoria) getSesion().get(Categoria.class, o);
        } finally {
            getSesion().close();
        }
        return categoria;
    }

    @Override
    public List<Categoria> findAll() {
        List<Categoria> categorias = null;
        String hql = "from Categoria";
        try {
            iniciaOperacion();
            categorias = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return categorias;
    }

}
