package SistemaGestorActivos.Dao;

import SistemaGestorActivos.Logic.Dependencia;
import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Logic.Puesto;
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

public class FuncionarioDAO extends HibernateUtil implements IBaseDao<Funcionario, String> {

    @Override
    public void save(Funcionario o) {
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
    public Funcionario merge(Funcionario o) throws HibernateException {
        try {
            iniciaOperacion();
            o = (Funcionario) getSesion().merge(o);
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
    public void delete(Funcionario o) {
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
    public Funcionario findById(String o) {
        Funcionario funcionario = null;
        try {
            iniciaOperacion();
            funcionario = (Funcionario) getSesion().get(Funcionario.class, o);
        } finally {
            getSesion().close();
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> findAll() {
        List<Funcionario> funcionarios = null;
        String hql = "from Funcionario";
        try {
            iniciaOperacion();
            funcionarios = getSesion().createQuery(hql).list();
        } finally {
            getSesion().close();
        }
        return funcionarios;
    }

    public List<Funcionario> getRegistradores() {
        List<Funcionario> funcionariosRaw = null;
        List<Funcionario> funcionariosFinal = new ArrayList<>();
        String sql = "select f.id,f.nombre\n"
                + "from funcionario f, puesto p\n"
                + "where f.puesto=p.id and p.id=3;";
        try {
            iniciaOperacion();
            funcionariosRaw = (List<Funcionario>) getSesion().createSQLQuery(sql).list();
            Iterator itr = funcionariosRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Funcionario fun = new Funcionario();
                fun.setId(String.valueOf(obj[0]));
                fun.setNombre(String.valueOf(obj[1]));

                funcionariosFinal.add(fun);
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return funcionariosFinal;
    }

    public List<Funcionario> getAllFuncionarios() {
        List<Funcionario> funcionariosRaw = null;
        List<Funcionario> funcionariosFinal = new ArrayList<>();
        String sql = "select f.id, f.nombre,d.nombre name, p.descripcion\n"
                + "from funcionario f, dependencia d, puesto p\n"
                + "where f.dependencia=d.id and f.puesto=p.id";
        try {
            iniciaOperacion();
            funcionariosRaw = (List<Funcionario>) getSesion().createSQLQuery(sql).list();
            Iterator itr = funcionariosRaw.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Funcionario fun = new Funcionario();
                fun.setId(String.valueOf(obj[0]));
                fun.setNombre(String.valueOf(obj[1]));
                
                Dependencia dep = new Dependencia();
                dep.setNombre(String.valueOf(obj[2]));
                fun.setDependencia(dep);
                
                Puesto pue = new Puesto();
                pue.setDescripcion(String.valueOf(obj[3]));
                fun.setPuesto(pue);

                funcionariosFinal.add(fun);
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            getSesion().close();
        }
        return funcionariosFinal;
    }

}
