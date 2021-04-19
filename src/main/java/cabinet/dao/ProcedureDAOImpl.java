package cabinet.dao;

import cabinet.model.Procedure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProcedureDAOImpl implements ProcedureDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Procedure> allProcedures(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Procedure").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @Transactional
    public void add(Procedure procedure) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(procedure);
    }

    @Override
    @Transactional
    public void delete(Procedure procedure) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(procedure);
    }

    @Override
    @Transactional
    public void edit(Procedure procedure) {
        Session session = sessionFactory.getCurrentSession();
        session.update(procedure);
    }

    @Override
    @Transactional
    public Procedure getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Procedure.class, id);
    }

    @Override
    @Transactional
    public int proceduresCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Procedure", Number.class).getSingleResult().intValue();
    }
}