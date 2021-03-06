package cabinet.dao;

import cabinet.model.Alert;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Class for interacting with alerts table in the DB
 *
 */

@Repository
public class AlertDAOImpl implements AlertDAO {
    private SessionFactory sessionFactory;

    public static final Logger logger = Logger.getLogger(AlertDAOImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets all existing alerts in the DB sorted by date and time in DESC order
     *
     * @return List<Alert>
     */
    @Override
    public List<Alert> getAllAlerts() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Alert a ORDER BY a.date DESC, a.time DESC").list();
        } catch (Exception ex) {
            logger.debug("get list of all alerts");
            logger.error(ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    /**
     * Adds a new alert to the DB
     *
     * @param alert
     */
    @Override
    public void add(Alert alert) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(alert);
        } catch (Exception ex) {
            logger.debug("add alert to the DB");
            logger.error(ex.getMessage(),ex);
        }
    }
}
