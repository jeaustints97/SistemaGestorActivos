package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Puesto;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;

public class PuestoDAO extends HibernateUtil implements IBaseDao<Puesto, java.math.BigInteger> {

    @Override
    public void save(Puesto o) {
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
    public Puesto merge(Puesto o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Puesto) getSesion().merge(o);
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
    public void delete(Puesto o) {
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
            String sql = "delete from puesto where id =" +id;
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
    public Puesto findById(BigInteger o) {
        Puesto puesto = null;
        try {
            iniciaOperacion();
            puesto = (Puesto) getSesion().get(Puesto.class, o);
        } finally {
            getSesion().close();
        }
        return puesto;
    }
    
    
     public Puesto findById(Integer o) {
        Puesto puesto = null;
        try {
            iniciaOperacion();
            puesto = (Puesto) getSesion().get(Puesto.class, o);
        } finally {
            getSesion().close();
        }
        return puesto;
    }

    @Override
    public List<Puesto> findAll() {
        List<Puesto> puestos = null;
        String hql = "from Puesto";
        try {
            iniciaOperacion();
            puestos = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return puestos;
    }
    
    public List<Puesto> getPuestoPorDescripcion( String descripcion){
        List<Puesto> puestoRaw = null;
        List<Puesto> puestoFinal = new ArrayList<>();
        String sql = "select id, descripcion from puesto where descripcion like '%%" + descripcion + "%';";
        
        try {
            iniciaOperacion();
            puestoRaw = (List<Puesto>) getSesion().createSQLQuery(sql).list();
            Iterator itr = puestoRaw.iterator();
            
            while(itr.hasNext()){
            
                Object[] obj = (Object[]) itr.next();
                Puesto pst = new Puesto();
                
                pst.setId(Integer.parseInt(String.valueOf(obj[0])));
                pst.setDescripcion(String.valueOf(obj[1]));
                puestoFinal.add(pst);
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        }
        finally{
            getSesion().close();
        }
        return puestoFinal;
    }
    
    public List<Puesto> find(int id){
    List<Puesto> puestoRaw = null;
    List<Puesto> puestoFinal = new ArrayList<>();
    
    String sql = "select id, descripcion from puesto where id =" +id+";";
    
        try {
            iniciaOperacion();
            puestoRaw = (List<Puesto>) getSesion().createSQLQuery(sql).list();
            Iterator itr = puestoRaw.iterator();
            
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                Puesto pst = new Puesto();
                
                pst.setId(Integer.parseInt(String.valueOf(obj[0])));
                pst.setDescripcion(String.valueOf(obj[1]));
                puestoFinal.add(pst);
                
                
            }
        } catch (HibernateException he) {
            manejaExcepcion (he);
            throw he;
            
        }
        finally{
        getSesion().close();
        }
        return puestoFinal;
    }
    
    
}