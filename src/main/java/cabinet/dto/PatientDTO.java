package cabinet.dto;

public class PatientDTO {
    private int id;
    private String name;
    private String surname;
    private boolean cured;
    private String diagnosis;
    private int insuranceNum;
    private String doctor;

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
}
