package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Activo;
import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Categoria;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class ActivoDAO extends HibernateUtil implements IBaseDao<Activo, java.math.BigInteger> {

    @Override
    public void save(Activo o) {
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

    public Integer saveAndGet(Activo o) {
        int id;
        try {
            iniciaOperacion();
            getSesion().save(o);
            id = o.getId();
            getTransc().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return id;
    }

    @Override
    public Activo merge(Activo o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Activo) getSesion().merge(o);
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
    public void delete(Activo o) {
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
    public Activo findById(BigInteger o) {
        Activo activo = null;
        try {
            iniciaOperacion();
            activo = (Activo) getSesion().get(Activo.class, o);
        } finally {
            getSesion().close();
        }
        return activo;
    }

    @Override
    public List<Activo> findAll() {
        List<Activo> activos = null;
        String hql = "from Activo";
        try {
            iniciaOperacion();
            activos = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return activos;
    }

    public List<Activo> getActivosSinAsignar(String id) {
        List<Activo> activosRaw = null;
        List<Activo> activosFinal = new ArrayList<>();
        String sql = "select a.id, s.id solicitud, b.descripcion, c.codigo, a.consecutivoactual\n"
                + "from activo a, bien b, categoria c, solicitud s\n"
                + "where a.bien=b.id and a.categoria=c.id and b.solicitud=s.id\n"
                + "a.id like '%%" + id + "%' and funcionario is null;";
        try {
            iniciaOperacion();
            activosRaw = (List<Activo>) getSesion().createSQLQuery(sql).list();
            Iterator itr = activosRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Activo act = new Activo();
                act.setId(Integer.parseInt(String.valueOf(obj[0])));

                Solicitud sol = new Solicitud();
                sol.setId(Integer.parseInt(String.valueOf(obj[1])));

                Bien bien = new Bien();
                bien.setDescripcion(String.valueOf(obj[2]));
                bien.setSolicitud(sol);
                act.setBien(bien);

                Categoria categoria = new Categoria();
                categoria.setCodigo(String.valueOf(obj[3]));
                act.setCategoria(categoria);

                act.setConsecutivoActual(Integer.parseInt(String.valueOf(obj[4])));

                activosFinal.add(act);
            }

        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return activosFinal;
    }

    public List<Activo> getActivosSinAsignar() {
        List<Activo> activosRaw = null;
        List<Activo> activosFinal = new ArrayList<>();
        String sql = "select a.id, s.id solicitud, b.descripcion, c.codigo, a.consecutivoactual\n"
                + "from activo a, bien b, categoria c, solicitud s\n"
                + "where a.bien=b.id and a.categoria=c.id and b.solicitud=s.id\n"
                + "and a.id like '%%%' and funcionario is null;";
        try {
            iniciaOperacion();
            activosRaw = (List<Activo>) getSesion().createSQLQuery(sql).list();
            Iterator itr = activosRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Activo act = new Activo();
                act.setId(Integer.parseInt(String.valueOf(obj[0])));

                Solicitud sol = new Solicitud();
                sol.setId(Integer.parseInt(String.valueOf(obj[1])));

                Bien bien = new Bien();
                bien.setDescripcion(String.valueOf(obj[2]));
                bien.setSolicitud(sol);
                act.setBien(bien);

                Categoria categoria = new Categoria();
                categoria.setCodigo(String.valueOf(obj[3]));
                act.setCategoria(categoria);

                act.setConsecutivoActual(Integer.parseInt(String.valueOf(obj[4])));

                activosFinal.add(act);
            }

        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return activosFinal;
    }

}
