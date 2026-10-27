package sbpestsolution.rw.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "van")
public class Van {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int vanNumber;

    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getVanNumber() {
        return vanNumber;
    }

    public void setVanNumber(int vanNumber) {
        this.vanNumber = vanNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}