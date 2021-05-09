package cabinet.model;

import javax.persistence.*;

/**
 * Класс - модель объекта "Процедура". Содержит поля уникального идентификатора,
 * идентификатора пациента, наименование, дату и время процедуры, статус выполнения.
 *
 * @author Aleksandr Budimirov
 * @version 0.1
 * */

@Entity
@Table(name = "procedures")
public class Procedure {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "dose")
    private int dose;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Procedure() {
    }

    public Procedure(int id, Patient patient, String title, String date, String time, String status, String type, int dose, Event event) {
        this.id = id;
        this.patient = patient;
        this.title = title;
        this.date = date;
        this.time = time;
        this.status = status;
        this.type = type;
        this.dose = dose;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
