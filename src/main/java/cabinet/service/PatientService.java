package cabinet.service;

import cabinet.dao.AlertDAO;
import cabinet.dao.PatientDAO;
import cabinet.model.Alert;
import cabinet.model.Patient;
import cabinet.model.dto.PatientDTO;
import cabinet.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private PatientDAO patientDAO;
    private AlertDAO alertDAO;

    private static final String DOCTOR = "Dr. John Dorian";

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Autowired
    public void setAlertDAO(AlertDAO alertDAO) {this.alertDAO = alertDAO; }

    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * Method for getting all patients from the DB
     *
     * @return List<PatientDTO>
     */
    @Transactional(readOnly = true)
    public List<PatientDTO> allPatients() {
        List<PatientDTO> patients = new ArrayList<>();
        for (Patient patient : patientDAO.allPatients()) {
            patients.add(DtoUtils.convertToDto(patient, PatientDTO.class));
        }
        return patients;
    }

    /**
     * Method for getting all patients from the DB with pagination (10 patients at a time)
     *
     * @param page
     * @return List<PatientDTO>
     */

    @Transactional(readOnly = true)
    public List<PatientDTO> allPatients(int page) {
        List<PatientDTO> patients = new ArrayList<>();
        for (Patient patient : patientDAO.allPatients(page)) {
            patients.add(DtoUtils.convertToDto(patient, PatientDTO.class));
        }
        return patients;
    }

    /**
     * Method for add new patient to the DB. At the end adds an alert to the Alerts table
     *
     * @param patientDTO
     */
    @Transactional
    public void add(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        patientDAO.add(patient);

        Alert alert = new Alert(DOCTOR, " added new patient " + patientDTO.getName() + " " + patientDTO.getSurname());
        alertDAO.add(alert);
    }


    /**
     * Method for edit an existing patient. At the end adds an alert to the Alerts table
     *
     * @param patientDTO
     */
    @Transactional
    public void edit(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        patientDAO.edit(patient);

        Alert alert = new Alert(DOCTOR, " edited profile of " + patientDTO.getName() + " " + patientDTO.getSurname());
        alertDAO.add(alert);
    }

    /**
     * Method to get patient by his ID
     *
     * @param id
     * @return PatientDTO
     */
    @Transactional
    public PatientDTO getById(int id) {
        Patient patient = patientDAO.getById(id);
        return DtoUtils.convertToDto(patient, PatientDTO.class);
    }

    /**
     * Method to discharge an existing patient. At the end adds an alert to the Alerts table
     *
     * @param patientDTO
     */
    @Transactional
    public void discharge(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        patientDAO.discharge(patient);

        Alert alert = new Alert(DOCTOR, " discharged patient " + patientDTO.getName() + " " + patientDTO.getSurname());
        alertDAO.add(alert);

    }

    /**
     * Method to count all existing patients in the DB
     *
     * @return int
     */
    @Transactional(readOnly = true)
    public int patientsCount() {
        return patientDAO.patientsCount();
    }

    /**
     * Method to count all patients with status 'not cured' in the DB
     *
     * @return int
     */
    @Transactional(readOnly = true)
    public int patientsInTreatmentCount() {
        return patientDAO.patientsInTreatmentCount();
    }
}
