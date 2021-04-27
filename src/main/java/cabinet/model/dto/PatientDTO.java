package cabinet.model.dto;

import cabinet.model.Procedure;

import java.util.Set;

public class PatientDTO implements DTOEntity {
    private int id;
    private String name;
    private String surname;
    private boolean cured;
    private String diagnosis;
    private int insuranceNum;
    private String doctor;
    private Set<Procedure> procedures;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isCured() {
        return cured;
    }

    public void setCured(boolean cured) {
        this.cured = cured;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(int insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
}