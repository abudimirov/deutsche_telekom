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

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Autowired
    public void setAlertDAO(AlertDAO alertDAO) {this.alertDAO = alertDAO; }

    @Autowired
    JmsTemplate jmsTemplate;

    @Transactional(readOnly = true)
    public List<PatientDTO> allPatients() {
        List<PatientDTO> patients = new ArrayList<>();
        for (Patient patient : patientDAO.allPatients()) {
            patients.add(DtoUtils.convertToDto(patient, PatientDTO.class));
        }
        return patients;
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> allPatients(int page) {
        List<PatientDTO> patients = new ArrayList<>();
        for (Patient patient : patientDAO.allPatients(page)) {
            patients.add(DtoUtils.convertToDto(patient, PatientDTO.class));
        }
        return patients;
    }

    @Transactional
    public void add(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        patientDAO.add(patient);

        Alert alert = new Alert("Dr. John Dorian", " added new patient " + patientDTO.getName() + " " + patientDTO.getSurname());
        alertDAO.add(alert);
    }


    @Transactional
    public void edit(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        patientDAO.edit(patient);

        Alert alert = new Alert("Dr. John Dorian", " edited profile of " + patientDTO.getName() + " " + patientDTO.getSurname());
        alertDAO.add(alert);
    }

    @Transactional
    public PatientDTO getById(int id) {
        Patient patient = patientDAO.getById(id);
        return DtoUtils.convertToDto(patient, PatientDTO.class);
    }

    @Transactional
    public void discharge(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        patientDAO.discharge(patient);

        Alert alert = new Alert("Dr. John Dorian", " discharged patient " + patientDTO.getName() + " " + patientDTO.getSurname());
        alertDAO.add(alert);

    }

    @Transactional(readOnly = true)
    public int patientsCount() {
        return patientDAO.patientsCount();
    }

    @Transactional(readOnly = true)
    public int patientsInTreatmentCount() {
        return patientDAO.patientsInTreatmentCount();
    }
}
