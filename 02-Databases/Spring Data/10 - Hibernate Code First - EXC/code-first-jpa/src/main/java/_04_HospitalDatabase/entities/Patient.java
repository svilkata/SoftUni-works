package _04_HospitalDatabase.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Entity(name = "_04_patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String EGN;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dateOfBirth;
    private boolean isInsured;
    private static List<Patient> patients = new ArrayList<>();

    //Един пациент няколко визити може да има
    @OneToMany(mappedBy = "id", targetEntity = Visitation.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Visitation> visitation;

    private static void addANewPatient(Patient patient) {
        if (!Patient.getAllPatients().stream().map(p -> p.getEGN()).collect(Collectors.toList())
                .contains(patient.getEGN())) {
            Patient.patients.add(patient);
        } else {
            System.out.println("Patient already exists");
        }
    }

    public static List<Patient> getAllPatients() {
        return Collections.unmodifiableList(Patient.patients);
    }

    public Patient() {
    }

    public Patient(String EGN, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, boolean isInsured) {
        this.EGN = EGN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isInsured = isInsured;
        Patient.addANewPatient(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEGN() {
        return this.EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth", nullable = false)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "is_insured", nullable = false)
    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }

    public Set<Visitation> getVisitation() {
        return visitation;
    }

    public void setVisitation(Set<Visitation> visitation) {
        this.visitation = visitation;
    }
}
