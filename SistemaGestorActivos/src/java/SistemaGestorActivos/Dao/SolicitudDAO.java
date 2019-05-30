package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Bien;
import SistemaGestorActivos.Logic.Dependencia;
import SistemaGestorActivos.Logic.Estado;
import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Solicitud;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolicitudDAO extends HibernateUtil implements IBaseDao<Solicitud, Integer> {

    @Override
    public void save(Solicitud o) {
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

    public Integer saveAndGet(Solicitud o) {
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
    public Solicitud merge(Solicitud o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Solicitud) getSesion().merge(o);
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
    public void delete(Solicitud o) {
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
    public Solicitud findById(Integer o) {
        Solicitud solicitud = null;
        try {
            iniciaOperacion();
            solicitud = (Solicitud) getSesion().get(Solicitud.class, o);
        } finally {
            getSesion().close();
        }
        return solicitud;
    }

    @Override
    public List<Solicitud> findAll() {
        List<Solicitud> solicitudes = null;
        String hql = "from Solicitud";
        try {
            iniciaOperacion();
            solicitudes = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return solicitudes;
    }

    public List<Solicitud> getSolicitud(Integer o) {
        List<Solicitud> listaRaw = null;
        List<Solicitud> listaFinal = new ArrayList<>();
        String sql = "select distinct s.id, s.comprobante, s.fecha, s.tipo,s.cantidad,"
                + "s.total,s.estado,s.dependencia\n"
                + "from Solicitud s\n"
                + "where id=" + o + ";";

        try {
            iniciaOperacion();
            listaRaw = (List<Solicitud>) getSesion().createSQLQuery(sql).list();
            Iterator itr = listaRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Solicitud soli = new Solicitud();
                soli.setId(Integer.parseInt(String.valueOf(obj[0])));
                soli.setComprobante(String.valueOf(obj[1]));

                String fecha = String.valueOf(obj[2]);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(fecha);
                soli.setFecha(date1);

                soli.setTipo(String.valueOf(obj[3]));
                soli.setCantidad(Integer.parseInt(String.valueOf(obj[4])));
                soli.setTotal(Float.parseFloat(String.valueOf(obj[5])));

                Estado est = new Estado();
                est.setId(Integer.parseInt(String.valueOf(obj[6])));
                soli.setEstado(est);

                Dependencia dep = new Dependencia();
                dep.setId(Integer.parseInt(String.valueOf(obj[7])));
                soli.setDependencia(dep);

                listaFinal.add(soli);
            }

        } catch (Exception ex) {
        } finally {
            getSesion().close();
        }
        return listaFinal;
    }

    public List<Solicitud> getSolicitudes() {
        List<Solicitud> listaRaw = null;
        List<Solicitud> listaFinal = new ArrayList<>();
        String sql = "select distinct s.id, s.comprobante,s.fecha,s.tipo,e.descripcion\n"
                + "from Solicitud s, Estado e\n"
                + "where s.estado=1 and s.estado=e.id;";

        try {
            iniciaOperacion();
            listaRaw = (List<Solicitud>) getSesion().createSQLQuery(sql).list();
            Iterator itr = listaRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Solicitud sol = new Solicitud();
                sol.setId(Integer.parseInt(String.valueOf(obj[0])));
                sol.setComprobante(String.valueOf(obj[1]));

                String fecha = String.valueOf(obj[2]);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(fecha);
                sol.setFecha(date1);

                sol.setTipo(String.valueOf(obj[3]));

                Estado est = new Estado();
                est.setDescripcion(String.valueOf(obj[4]));
                sol.setEstado(est);

                listaFinal.add(sol);
            }

        } catch (Exception ex) {
        } finally {
            getSesion().close();
        }
        return listaFinal;
    }

    public List<Solicitud> getSolicitudesPorVerificar() {
        List<Solicitud> listaRaw = null;
        List<Solicitud> listaFinal = new ArrayList<>();
        String sql = "select distinct s.id, s.comprobante,s.fecha,s.tipo\n"
                + "from Solicitud s\n"
                + "where s.estado=2 and s.registrador is null";
        try {
            iniciaOperacion();
            listaRaw = (List<Solicitud>) getSesion().createSQLQuery(sql).list();
            Iterator itr = listaRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Solicitud sol = new Solicitud();
                sol.setId(Integer.parseInt(String.valueOf(obj[0])));
                sol.setComprobante(String.valueOf(obj[1]));

                String fecha = String.valueOf(obj[2]);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(fecha);
                sol.setFecha(date1);

                sol.setTipo(String.valueOf(obj[3]));

                listaFinal.add(sol);
            }

        } catch (Exception ex) {
        } finally {
            getSesion().close();
        }
        return listaFinal;
    }

    public List<Solicitud> getSolicitudesPorRegistrador(String idReg) {
        List<Solicitud> listaRaw = null;
        List<Solicitud> listaFinal = new ArrayList<>();
        String sql = "select id, comprobante, fecha, tipo\n"
                + "from Solicitud\n"
                + "where estado=2 and registrador=" + idReg;
        try {
            iniciaOperacion();
            listaRaw = (List<Solicitud>) getSesion().createSQLQuery(sql).list();
            Iterator itr = listaRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Solicitud sol = new Solicitud();
                sol.setId(Integer.parseInt(String.valueOf(obj[0])));
                sol.setComprobante(String.valueOf(obj[1]));

                String fecha = String.valueOf(obj[2]);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(fecha);
                sol.setFecha(date1);

                sol.setTipo(String.valueOf(obj[3]));

                listaFinal.add(sol);
            }

        } catch (Exception ex) {
        } finally {
            getSesion().close();
        }
        return listaFinal;
    }

    public List<Solicitud> getSolicitudesPorComprobante(String comprobante) {
        List<Solicitud> solicitudesRaw = null;
        List<Solicitud> solicitudesFinal = new ArrayList<>();
        String sql = "select distinct s.id, s.comprobante,s.fecha,s.tipo,e.descripcion\n"
                + "from Solicitud s, Estado e\n"
                + "where s.estado=1 and s.estado=e.id and s.comprobante like '%%" + comprobante + "%';";
        try {
            iniciaOperacion();
            solicitudesRaw = (List<Solicitud>) getSesion().createSQLQuery(sql).list();
            Iterator itr = solicitudesRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Solicitud sol = new Solicitud();
                sol.setId(Integer.parseInt(String.valueOf(obj[0])));
                sol.setComprobante(String.valueOf(obj[1]));

                String fecha = String.valueOf(obj[2]);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(fecha);
                sol.setFecha(date1);

                sol.setTipo(String.valueOf(obj[3]));
                Estado est = new Estado();
                est.setDescripcion(String.valueOf(obj[4]));
                sol.setEstado(est);
                solicitudesFinal.add(sol);
            }

        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            getSesion().close();
        }
        return solicitudesFinal;
    }

    public List<Solicitud> getSolicitudesPorComprobanteJefe(String comprobante) {
        List<Solicitud> solicitudesRaw = null;
        List<Solicitud> solicitudesFinal = new ArrayList<>();
        String sql = "select distinct id, comprobante, fecha, tipo\n"
                + "from Solicitud\n"
                + "where estado=2 and registrador is null and comprobante like '%%" + comprobante + "%';";
        try {
            iniciaOperacion();
            solicitudesRaw = (List<Solicitud>) getSesion().createSQLQuery(sql).list();
            Iterator itr = solicitudesRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Solicitud sol = new Solicitud();
                sol.setId(Integer.parseInt(String.valueOf(obj[0])));
                sol.setComprobante(String.valueOf(obj[1]));

                String fecha = String.valueOf(obj[2]);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = format.parse(fecha);
                sol.setFecha(date1);

                sol.setTipo(String.valueOf(obj[3]));
                solicitudesFinal.add(sol);
            }

        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            getSesion().close();
        }
        return solicitudesFinal;
    }

    public void AprobarSolicitud(int Solicitud) throws HibernateException {
        String sql = "update solicitud set estado = 2 where id = " + Solicitud;
        try {
            iniciaOperacion();
            int a = getSesion().createSQLQuery(sql).executeUpdate();
            getTransc().commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
    }

    public void RechazarSolicitud(int Solicitud) {
        String sql = "update solicitud set estado = 3 where id = " + Solicitud + ";";
        try {
            iniciaOperacion();
            getSesion().createSQLQuery(sql).executeUpdate();
            getTransc().commit();
        } catch (Exception ex) {
        } finally {
            getSesion().close();
        }
    }

    public void asignacionDeRegistrador(int idSolicitud, String idRegistrador) {
        String sql = "update solicitud set registrador = " + idRegistrador
                + " where id = " + idSolicitud + ";";
        try {
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

    public void registroDeActivo(int idSolicitud, String idRegistrador) {
        String sql = "update solicitud set registrador = " + idRegistrador
                + " where id = " + idSolicitud + ";";
        try {
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
}
