package entity._04_Hospital;

import entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class GP extends BaseEntity {

   private Set<Patient> patients;
   private Set<Diagnose> diagnoses;
   private Set<Medicament> medicaments;
   private Set<Visitation> visitations;


    public GP() {
    }

    @OneToMany
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @OneToMany
    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @OneToMany
    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    @OneToMany
    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }
}
