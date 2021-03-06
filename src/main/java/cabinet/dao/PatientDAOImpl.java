package cabinet.dao;

import cabinet.model.Event;
import cabinet.model.Patient;
import cabinet.model.Procedure;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class PatientDAOImpl implements PatientDAO {

    private SessionFactory sessionFactory;

    private static final String SCHEDULED = "scheduled";
    private static final String CANCELLED = "cancelled";

    public static final Logger logger = Logger.getLogger(PatientDAOImpl.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    /**
     * Method to get all patient entities from the DB in a list
     *
     * @return list of all patients in the DB
     */
    @SuppressWarnings("unchecked")
    public List<Patient> allPatients() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Patient p ORDER BY p.name, p.surname").list();
        } catch (Exception ex) {
            logger.debug("getting list of all patients");
            logger.error(ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    /**
     * Method to get all patient entities from the DB in a list with pagination
     *
     * @return list of 10 patients from the DB
     */
    @SuppressWarnings("unchecked")
    public List<Patient> allPatients(int page) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Patient p").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        } catch (Exception ex) {
            logger.debug("getting list of all patients with pagination");
            logger.error(ex.getMessage(),ex);
            return Collections.emptyList();
        }
    }

    /**
     * Method to add patient to the DB
     *
     * @param patient
     */
    @Override
    public void add(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(patient);
        } catch (Exception ex) {
            logger.debug("add patient to the DB");
            logger.error(ex.getMessage(),ex);
        }
    }

    /**
     * Method to set patients status to "cured" and cancel all his apointments
     *
     * @param patient
     */
    @Override
    public void discharge(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            patient.setCured(true);
            Set<Procedure> procedures = patient.getProcedures();
            for (Procedure procedure : procedures) {
                if (procedure.getStatus().equals(SCHEDULED)) {
                    procedure.setStatus(CANCELLED);
                }
            }
            Set<Event> events = patient.getEvents();
            for (Event event : events) {
                if (event.getStatus().equals(SCHEDULED)) {
                    event.setStatus(CANCELLED);
                }
            }
            session.update(patient);
        } catch (Exception ex) {
            logger.debug("discharging patient");
            logger.error(ex.getMessage(),ex);
        }
    }

    /**
     * Method to cancel all procedures for one patient
     *
     * @param patient
     */
    @Override
    public void cancelProcedures(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Set<Procedure> procedures = patient.getProcedures();
            for (Procedure procedure : procedures) {
                if(procedure.getStatus().equals(SCHEDULED)) {
                    procedure.setStatus(CANCELLED);
                }
            }
            session.update(patient);
        } catch (Exception ex) {
            logger.debug("cancel procedures for patient");
            logger.error(ex.getMessage(),ex);
        }

    }

    /**
     * Method for editing patient in the DB
     *
     * @param patient
     */
    @Override
    public void edit(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(patient);
        } catch (Exception ex) {
            logger.debug("edit patient in the DB");
            logger.error(ex.getMessage(),ex);
        }
    }

    /**
     * Method to get patient entity by his ID in the DB
     *
     * @param id - patient ID
     * @return patient entity
     */
    @Override
    public Patient getById(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Patient.class, id);
        } catch (Exception ex) {
            logger.debug("get patient by ID from DB");
            logger.error(ex.getMessage(),ex);
            return null;
        }
    }

    /**
     * Count all patients in the DB
     *
     * @return int of total patients
     */
    @Override
    public int patientsCount() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("select count(*) from Patient", Number.class).getSingleResult().intValue();
        } catch (Exception ex) {
            logger.debug("count all patients");
            logger.error(ex.getMessage(),ex);
            return 0;
        }
    }

    /**
     * Count all patients in the DB whose status is not cured
     *
     * @return int of patients who is not cured
     */
    @Override
    public int patientsInTreatmentCount() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("select count(*) from Patient where cured = false", Number.class).getSingleResult().intValue();
        } catch (Exception ex) {
            logger.debug("count not cured patients");
            logger.error(ex.getMessage(),ex);
            return 0;
        }
    }
}