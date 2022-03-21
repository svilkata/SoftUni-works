package _04_HospitalDatabase;

import _04_HospitalDatabase.entities.Diagnose;
import _04_HospitalDatabase.entities.Medicament;
import _04_HospitalDatabase.entities.Patient;
import _04_HospitalDatabase.entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _04Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_relations_exc");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Patient patient1 = new Patient("8800554368", "Baj", "Tosho", "Lisitsa str. 8",
                "rakia@abv.bg", LocalDate.of(1988, 8, 28), true);

        Patient patient2 = new Patient("8505972314", "Svill", "Velikov", "Sofia 9",
                "dobro@abv.bg", LocalDate.of(1985, 8, 28), true);

        Patient patientFake = new Patient("8800554368", "Repeated", "Repeated",
                "Repeated", "Repeated", LocalDate.of(1988, 8, 28), true);

        Patient patien3 = new Patient("9000052233", "Ceco", "Ivanov", "Varna 112",
                "zzz@abv.bg", LocalDate.of(1990, 8, 28), true);

        List<Patient> allPatients = Patient.getAllPatients();
        for (Patient patient : allPatients) {
            entityManager.persist(patient);
        }

        Diagnose diagnose1 = new Diagnose("Bolen ot zaples", "Kofti trypka");
        Diagnose diagnose2 = new Diagnose("Bolen ot lubov", "Am siga ko praim");
        Diagnose diagnose3 = new Diagnose("Covid 19", "Kofti Kofti");
        Diagnose diagnose4 = new Diagnose("Rak na debelo chervo", "Seriozna rabota");
        entityManager.persist(diagnose1);
        entityManager.persist(diagnose2);
        entityManager.persist(diagnose3);
        entityManager.persist(diagnose4);

        Medicament medicament1 = new Medicament("Aspirin");
        Medicament medicament2 = new Medicament("Paracetamol");
        Medicament medicament3 = new Medicament("Izoprinuzin");
        Medicament medicament4 = new Medicament("Vit. C");
        Medicament medicament5 = new Medicament("Vit. Kasa Bira B Complex");
        entityManager.persist(medicament1);
        entityManager.persist(medicament2);
        entityManager.persist(medicament3);
        entityManager.persist(medicament4);
        entityManager.persist(medicament5);
        Set<Medicament> medicamentsOfVisit1 = new HashSet<>(Arrays.asList(medicament1));
        Set<Medicament> medicamentsOfVisit2 = new HashSet<>(Arrays.asList(medicament2));
        Set<Medicament> medicamentsOfVisit3 = new HashSet<>(Arrays.asList(medicament3, medicament4, medicament5));


        Visitation visit1 = new Visitation(allPatients.get(0), LocalDate.now(), "Visit 1", diagnose1, medicamentsOfVisit1);
        Visitation visit2 = new Visitation(allPatients.get(1), LocalDate.now(), "Visit 2", diagnose3, medicamentsOfVisit2);
        Visitation visit3 = new Visitation(allPatients.get(2), LocalDate.now(), "Visit 3", diagnose4, medicamentsOfVisit3);
        entityManager.persist(visit1);
        entityManager.persist(visit2);
        entityManager.persist(visit3);

        //I can not fix this
//        Visitation visit4 = new Visitation(allPatients.get(0), LocalDate.now(), "Visit 4 Patient 1 for second time");
//        entityManager.persist(visit4);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}



