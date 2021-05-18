package cabinet.dao;

import cabinet.model.Event;
import cabinet.model.Patient;
import org.apache.log4j.Logger;
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

    public static final Logger logger = Logger.getLogger(EventDAOImpl.class);

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
            logger.debug("get list of all events");
            logger.error(ex.getMessage(),ex);
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
            logger.debug("get event by patient");
            logger.error(ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    @Override
    public void add(Event event) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(event);
        } catch (Exception ex) {
            logger.debug("add event to the DB");
            logger.error(ex.getMessage(),ex);
        }
    }

    @Override
    public void delete(Event event) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(event);
        } catch (Exception ex) {
            logger.debug("delete event from the DB");
            logger.error(ex.getMessage(),ex);
        }
    }

    @Override
    public void edit(Event event) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(event);
        } catch (Exception ex) {
            logger.debug("edit event in the DB");
            logger.error(ex.getMessage(),ex);
        }
    }

    @Override
    public Event getById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Event.class, id);
        } catch (Exception ex) {
            logger.debug("get event by its ID");
            logger.error(ex.getMessage(),ex);
            return null;
        }
    }


}
