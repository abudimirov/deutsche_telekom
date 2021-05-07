package cabinet.dao;

import cabinet.model.Patient;

import java.util.List;

public interface PatientDAO {
    List<Patient> allPatients();
    List<Patient> allPatients(int page);
    void add(Patient patient);
    void discharge(Patient patient);
    void cancelProcedures(Patient patient);
    void edit(Patient patient);
    Patient getById(int id);
    int patientsCount();
}
