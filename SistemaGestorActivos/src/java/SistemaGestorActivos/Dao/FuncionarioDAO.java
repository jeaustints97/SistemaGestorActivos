package SistemaGestorActivos.Dao;

import java.util.List;
import org.hibernate.HibernateException;
import SistemaGestorActivos.Logic.Funcionario;
import SistemaGestorActivos.Utils.HibernateUtil;
import java.math.BigInteger;

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

}
