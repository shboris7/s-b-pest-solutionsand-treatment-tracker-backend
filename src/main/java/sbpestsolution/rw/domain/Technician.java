package sbpestsolution.rw.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "technician")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String certificationLevel;

    @ManyToOne
    @JoinColumn(name = "van_id")
    private Van van;

    @ManyToMany
    @JoinTable(
        name = "technician_treatment_type",
        joinColumns = @JoinColumn(name = "technician_id"),
        inverseJoinColumns = @JoinColumn(name = "treatment_type_id")
    )
    private List<TreatmentType> treatmentTypes = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public Van getVan() {
        return van;
    }

    public void setVan(Van van) {
        this.van = van;
    }

    public List<TreatmentType> getTreatmentTypes() {
        return treatmentTypes;
    }

    public void setTreatmentTypes(List<TreatmentType> treatmentTypes) {
        this.treatmentTypes = treatmentTypes;
    }

    
}