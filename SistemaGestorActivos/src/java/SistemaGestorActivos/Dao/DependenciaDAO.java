package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Dependencia;
import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Logic.Puesto;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class DependenciaDAO extends HibernateUtil implements IBaseDao<Dependencia, Integer> {

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
    public Dependencia findById(Integer o) {
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

    public void delete(int id) {
        try {
            String sql = "delete from dependencia where id = " + id;
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

    public void merge2(Dependencia dependencia) {
        try {
            String sql = "update from dependencia set id= " + dependencia.getId() + ", set nombre= " + dependencia.getNombre() 
                    + ", set administrador= "+dependencia.getFuncionario().getId()+" where id = " + dependencia.getId();
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

    public Dependencia findById(int o) {
        Dependencia dependencia = null;
        try {
            iniciaOperacion();
            dependencia = (Dependencia) getSesion().get(Dependencia.class, o);
        } finally {
            getSesion().close();
        }
        return dependencia;
    }

    public List<Dependencia> find(int id) {
        List<Dependencia> dependenciaRaw = null;
        List<Dependencia> dependenciaFinal = new ArrayList<>();
        String sql = "select id, nombre, administrador from dependencia where id = " + id + ";";

        try {
            iniciaOperacion();
            dependenciaRaw = (List<Dependencia>) getSesion().createSQLQuery(sql).list();
            Iterator itr = dependenciaRaw.iterator();

            while (itr.hasNext()) {

                Object[] obj = (Object[]) itr.next();
                Dependencia dpd = new Dependencia();
                Funcionario fun = new Funcionario();
                dpd.setId(Integer.parseInt(String.valueOf(obj[0])));
                dpd.setNombre(String.valueOf(obj[1]));
                fun.setId(String.valueOf(obj[2]));
                dpd.setFuncionario(fun);
                dependenciaFinal.add(dpd);
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return dependenciaFinal;
    }

    public List<Dependencia> getPuestoPorNombre(String nombre) {
        List<Dependencia> dependenciaRaw = null;
        List<Dependencia> dependenciaFinal = new ArrayList<>();
        String sql = "select d.id,d.nombre,f.nombre admin\n"
                + "from dependencia d, funcionario f\n"
                + "where d.administrador=f.id and d.nombre like '%%" + nombre + "%' "
                + "union "
                + "select temp.id,temp.nombre,'Sin Asignar' "
                + "from (select d.id,d.nombre,d.administrador "
                + "from dependencia d  "
                + "where d.administrador is null) temp "
                + ";";

        try {
            iniciaOperacion();
            dependenciaRaw = (List<Dependencia>) getSesion().createSQLQuery(sql).list();
            Iterator itr = dependenciaRaw.iterator();

            while (itr.hasNext()) {

                Object[] obj = (Object[]) itr.next();
                Dependencia dpd = new Dependencia();
                Funcionario fun = new Funcionario();
                dpd.setId(Integer.parseInt(String.valueOf(obj[0])));
                dpd.setNombre(String.valueOf(obj[1]));
                fun.setNombre(String.valueOf(obj[2]));
                dpd.setFuncionario(fun);
                dependenciaFinal.add(dpd);
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return dependenciaFinal;
    }

}
