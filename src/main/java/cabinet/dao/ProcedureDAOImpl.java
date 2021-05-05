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
import java.util.Date;
import java.util.List;

@Repository
public class ProcedureDAOImpl implements ProcedureDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Procedure> allProcedures() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Procedure p ORDER BY p.date, p.time").list();
    }

    @SuppressWarnings("unchecked")
    public List<Procedure> allProcedures(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Procedure p ORDER BY p.date, p.time").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
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

    @Override
    public List<Procedure> proceduresByPatient(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Procedure p where p.patient.id = :id");
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public List<Procedure> proceduresByDate(String date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Procedure p where p.date = :date ORDER BY p.time");
        query.setParameter("date", date);
        return query.list();
    }

    @Override
    public List<Procedure> proceduresForNextHour() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Procedure p where p.date = :date and p.time between :now and :hourFromNow ORDER BY p.time");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        query.setParameter("date", dateFormat.format(Calendar.getInstance().getTime()));
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        query.setParameter("now", timeFormat.format(Calendar.getInstance().getTime()));
        Date nowTime = Calendar.getInstance().getTime();
        Calendar cal =Calendar.getInstance();
        cal.setTime(nowTime);
        cal.add(cal.HOUR_OF_DAY, 1);
        Date hourFromNow = cal.getTime();
        query.setParameter("hourFromNow", timeFormat.format(hourFromNow));
        return query.list();
    }

    @Override
    public int proceduresCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Procedure", Number.class).getSingleResult().intValue();
    }
}