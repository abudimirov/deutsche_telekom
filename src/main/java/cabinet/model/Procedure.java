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

    /*@Column(name = "patient_id")
    private int patient_id;*/

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

    public Procedure() {
    }

    public Procedure(int id, Patient patient, String title, String date, String time, String status) {
        this.id = id;
        this.patient = patient;
        this.title = title;
        this.date = date;
        this.time = time;
        this.status = status;
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

//    @Override
//    public String toString() {
//        return "Procedure{" +
//                "id=" + id +
//                ", patient=" + patient +
//                ", title='" + title + '\'' +
//                ", date='" + date + '\'' +
//                ", time='" + time + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
