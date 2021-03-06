package cabinet.model.dto;

import cabinet.model.Event;
import cabinet.model.Procedure;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class PatientDTO implements DTOEntity {
    private int id;

    @NotNull
    @Size(min = 2, max = 100, message="Name should be 2 to 100 characters long")
    @Pattern(regexp = "[^0-9]*", message = "Name must not contain numbers")
    private String name;

    @NotNull
    @Size(min = 2, max = 100, message="Surname should be 2 to 100 characters long")
    @Pattern(regexp = "[^0-9]*", message = "Surname must not contain numbers")
    private String surname;

    private boolean cured;

    private String diagnosis;

    @NotNull
    @Digits(integer=4, fraction=0, message = "Invalid insurance number. Must be 4 digits")
    private int insuranceNum;

    private String doctor;
    private Set<Procedure> procedures;
    private Set<Event> events;

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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return id == that.id &&
                cured == that.cured &&
                insuranceNum == that.insuranceNum &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(procedures, that.procedures) &&
                Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, cured, diagnosis, insuranceNum, doctor, procedures, events);
    }
}
