package cabinet.dao;

import cabinet.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDAOImpl implements PatientDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Patient> allPatients(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Patient").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(patient);
    }

    @Override
    public void delete(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(patient);
    }

    @Override
    public void edit(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.update(patient);
    }

    @Override
    public Patient getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Patient.class, id);
    }
}