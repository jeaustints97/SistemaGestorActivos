package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Usuario;
import SistemaGestorActivos.Utils.HibernateUtil;

public class UsuarioDAO extends HibernateUtil implements IBaseDao<Usuario, String> {

    @Override
    public void save(Usuario o) {
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
    public Usuario merge(Usuario o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Usuario) getSesion().merge(o);
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
    public void delete(Usuario o) {
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
    public Usuario findById(String o) {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            usuario = (Usuario) getSesion().get(Usuario.class, o);
        } finally {
            getSesion().close();
        }
        return usuario;
    }

    public Usuario auntenticar(String id, String clave) {
        List<Usuario> usuarios = null;
        String hql = "from Usuario where id='" + id + "' and clave='" + clave + "'";
        try {
            iniciaOperacion();
            usuarios = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        if (usuarios != null && usuarios.size() > 0) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = null;
        String hql = "from Usuario";
        try {
            iniciaOperacion();
            usuarios = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return usuarios;
    }

    public List<Usuario> findByRol(String rol) {
        List<Usuario> usuarios = null;
        String hql = "from Usuario where rol= '" + rol + "'";
        try {
            iniciaOperacion();
            usuarios = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return usuarios;
    }

}