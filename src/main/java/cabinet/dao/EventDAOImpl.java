package cabinet.dao;

import cabinet.model.Event;
import cabinet.model.Patient;
import cabinet.model.Procedure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class EventDAOImpl implements EventDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Event> allEvents() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Event e ORDER BY e.start_date").list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Event> getEventsByPatient(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Event> query = session.createQuery("from Event e where e.patientEvent.id = :id", Event.class);
            query.setParameter("id", patient.getId());
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void add(Event event) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Event event) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(Event event) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Event getById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Event.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
