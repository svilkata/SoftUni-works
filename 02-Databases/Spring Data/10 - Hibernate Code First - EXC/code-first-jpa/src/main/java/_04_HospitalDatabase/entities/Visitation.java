package _04_HospitalDatabase.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "_04_visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String comment;

    //няколко визити от един пациент може
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;


    //За всяка визита приемаме, че има диагноза и изписан един или няколко медикаменти
    @OneToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Diagnose diagnose;

    //За всяка визита приемаме, че има изписан един или няколко медикаменти
    @OneToMany
//    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Set<Medicament> medicaments;

    //Но може различни визити на различни хора да имат една и съша диагноза
//    @ManyToMany
//    @JoinTable(name = "visitations_diagnoses",
//            joinColumns = @JoinColumn(name = "visit_id", referencedColumnName = "id"), //текущия клас - //работи с SQL колоните от базата, id реално не е анотирано с Column и с различно име в базата
//            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))   //класът, който реферираме, а именно BasicIngredient
//    private Set<Diagnose> diagnoses;

    public Visitation() {
    }

    public Visitation(Patient patient, LocalDate date, String comment, Diagnose diagnose, Set<Medicament> medicaments) {
        this.date = date;
        this.comment = comment;
        this.diagnose = diagnose;
        this.medicaments = medicaments;
        this.setPatient(patient);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
