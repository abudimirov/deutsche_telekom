package cabinet.dao;

import cabinet.model.Procedure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class ProcedureDAOImpl implements ProcedureDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    /**
     * This method is for getting all procedures from DB.
     *
     * @return List of Procedure entities
     */
    @SuppressWarnings("unchecked")
    public List<Procedure> allProcedures() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Procedure p ORDER BY p.date, p.time").list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * This method is for getting all procedures from DB with pagination.
     *
     * @param page
     * @return List of 10 Procedures entities
     */
    public List<Procedure> allProcedures(int page) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Procedure p ORDER BY p.date, p.time").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Method to add procedure to the DB.
     *
     * @param procedure - Procedure entity
     */
    @Override
    public void add(Procedure procedure) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(procedure);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to delete procedure from DB.
     *
     * @param procedure - Procedure entity
     */
    @Override
    public void delete(Procedure procedure) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(procedure);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to edit an existing procedure in the DB.
     *
     * @param procedure - Procedure entity
     */
    @Override
    public void edit(Procedure procedure) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(procedure);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method for getting an existing procedure in the DB by its ID.
     *
     * @param id - id of the procedure in the DB
     * @return procedure entity
     */
    @Override
    public Procedure getById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Procedure.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Method for getting list of all procedures from the DB by patient ID.
     *
     * @param id - id of the patient in the DB
     * @return list of procedures entity
     */
    @Override
    public List<Procedure> proceduresByPatient(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Procedure> query = session.createQuery("from Procedure p where p.patient.id = :id", Procedure.class);
            query.setParameter("id", id);
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Method to get all procedures on a specified date
     *
     * @param date
     * @return list of procedures entities
     */
    @Override
    public List<Procedure> proceduresByDate(String date) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Procedure> query = session.createQuery("from Procedure p where p.date = :date ORDER BY p.time", Procedure.class);
            query.setParameter("date", date);
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Method to get all procedures for the next hour
     *
     * @return list of procedures entities
     */
    @Override
    public List<Procedure> proceduresForNextHour() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Procedure> query = session.createQuery("from Procedure p where p.date = :date and p.time between :now and :hourFromNow ORDER BY p.time", Procedure.class);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            query.setParameter("date", dateFormat.format(Calendar.getInstance().getTime()));
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            query.setParameter("now", timeFormat.format(Calendar.getInstance().getTime()));
            Date nowTime = Calendar.getInstance().getTime();
            Calendar cal =Calendar.getInstance();
            cal.setTime(nowTime);
            cal.add(Calendar.HOUR_OF_DAY, 1);
            Date hourFromNow = cal.getTime();
            query.setParameter("hourFromNow", timeFormat.format(hourFromNow));
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     *  Method to get total number of procedures in DB
     *
     * @return number of all procedures in DB
     */
    @Override
    public int proceduresCount() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("select count(*) from Procedure", Number.class)
                    .getSingleResult()
                    .intValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

    }
}