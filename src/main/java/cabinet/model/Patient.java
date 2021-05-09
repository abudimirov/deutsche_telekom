package cabinet.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Model of object Patient
 *
 * @author Aleksandr Budimirov
 * @version 0.1
 * */

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "cured")
    private boolean cured;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "insuranceNum")
    private int insuranceNum;

    @Column(name = "doctor")
    private String doctor;


    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Procedure> procedures;

    public Patient() {
    }

    public Patient(int id, String name, String surname, boolean cured, String diagnosis, int insuranceNum, String doctor) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cured = cured;
        this.diagnosis = diagnosis;
        this.insuranceNum = insuranceNum;
        this.doctor = doctor;
    }

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
