package cabinet.service;

import cabinet.dao.PatientDAO;
import cabinet.model.Patient;
import cabinet.model.dto.PatientDTO;
import cabinet.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(readOnly = true)
    public List<PatientDTO> allPatients() {
        List<PatientDTO> patients = new ArrayList<>();
        for (Patient patient : patientDAO.allPatients()) {
            patients.add((PatientDTO) new DtoUtils().convertToDto(patient, new PatientDTO()));
        }
        return patients;
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> allPatients(int page) {
        List<PatientDTO> patients = new ArrayList<>();
        for (Patient patient : patientDAO.allPatients(page)) {
            patients.add((PatientDTO) new DtoUtils().convertToDto(patient, new PatientDTO()));
        }
        return patients;
    }

    @Transactional
    public void add(PatientDTO patientDTO) {
        Patient patient = (Patient) new DtoUtils().convertToEntity(new Patient(), patientDTO);
        patientDAO.add(patient);
    }


    @Transactional
    public void edit(PatientDTO patientDTO) {
        Patient patient = (Patient) new DtoUtils().convertToEntity(new Patient(), patientDTO);
        patientDAO.edit(patient);
    }

    @Transactional
    public PatientDTO getById(int id) {
        Patient patient = patientDAO.getById(id);
        return (PatientDTO) new DtoUtils().convertToDto(patient, new PatientDTO());
    }

    @Transactional
    public void discharge(PatientDTO patientDTO) {
        Patient patient = (Patient) new DtoUtils().convertToEntity(new Patient(), patientDTO);
        patientDAO.discharge(patient);
    }

    @Transactional
    public int patientsCount() {
        return patientDAO.patientsCount();
    }
}
