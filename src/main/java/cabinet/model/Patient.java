package cabinet.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс - модель объекта "Пациент". Содержит поля уникального идентификатора,
 * имя пациента, фамилию, год рождения, пол и статус лечения.
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

    @Column(name = "yearOfBirth")
    private int yearOfBirth;

    @Column(name = "sex")
    private String sex;

    @Column(name = "cured")
    private boolean cured;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Procedure> procedures;

    public Patient() {
    }

    public Patient(int id, String name, String surname, int yearOfBirth, String sex, boolean cured, Set<Procedure> procedures) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.sex = sex;
        this.cured = cured;
        this.procedures = procedures;
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isCured() {
        return cured;
    }

    public void setCured(boolean cured) {
        this.cured = cured;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", sex='" + sex + '\'' +
                ", cured=" + cured +
                ", procedures=" + procedures +
                '}';
    }
}
