package cabinet.service;

import cabinet.dao.PatientDAO;
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

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

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
    }


    @Transactional
    public void edit(PatientDTO patientDTO) {
        Patient patient = DtoUtils.convertToEntity(Patient.class, patientDTO);
        patientDAO.edit(patient);
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
