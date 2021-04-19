package cabinet.dao;

import cabinet.model.Procedure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcedureDAOImpl implements ProcedureDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Procedure> allProcedures(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Procedure").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Procedure procedure) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(procedure);
    }

    @Override
    public void delete(Procedure procedure) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(procedure);
    }

    @Override
    public void edit(Procedure procedure) {
        Session session = sessionFactory.getCurrentSession();
        session.update(procedure);
    }

    @Override
    public Procedure getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Procedure.class, id);
    }
}