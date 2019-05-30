package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Categoria;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

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

    public void delete(int id) {
        try {
            String sql = "delete from categoria where id = " + id;
            iniciaOperacion();
            getSesion().createSQLQuery(sql).executeUpdate();
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

    public Categoria findById(int o) {
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

    public List<Categoria> getCategoriasPorDescripcion(String descripcion) {
        List<Categoria> categoriasRaw = null;
        List<Categoria> categoriasFinal = new ArrayList<>();
        String sql = "select id, codigo, descripcion, consecutivo\n"
                + "from Categoria\n"
                + "where descripcion like '%%" + descripcion + "%';";
        try {
            iniciaOperacion();
            categoriasRaw = (List<Categoria>) getSesion().createSQLQuery(sql).list();
            Iterator itr = categoriasRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Categoria cat = new Categoria();
                cat.setId(Integer.parseInt(String.valueOf(obj[0])));
                cat.setCodigo(String.valueOf(obj[1]));
                cat.setDescripcion(String.valueOf(obj[2]));
                cat.setConsecutivo(Integer.parseInt(String.valueOf(obj[3])));
                categoriasFinal.add(cat);
            }

        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return categoriasFinal;
    }
    public List<Categoria> find(int id) {
        List<Categoria> categoriasRaw = null;
        List<Categoria> categoriasFinal = new ArrayList<>();
        String sql = "select id, codigo, descripcion, consecutivo\n"
                + "from Categoria\n"
                + "where id = " + id + ";";
        try {
            iniciaOperacion();
            categoriasRaw = (List<Categoria>) getSesion().createSQLQuery(sql).list();
            Iterator itr = categoriasRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Categoria cat = new Categoria();
                cat.setId(Integer.parseInt(String.valueOf(obj[0])));
                cat.setCodigo(String.valueOf(obj[1]));
                cat.setDescripcion(String.valueOf(obj[2]));
                cat.setConsecutivo(Integer.parseInt(String.valueOf(obj[3])));
                categoriasFinal.add(cat);
            }

        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return categoriasFinal;
    }

}
