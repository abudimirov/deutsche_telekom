package cabinet.dao;

import cabinet.model.Patient;

import java.util.List;

public interface PatientDAO {
    List<Patient> allPatients(int page);
    void add(Patient patient);
    void delete(Patient patient);
    void edit(Patient patient);
    Patient getById(int id);
    int patientsCount();
}
