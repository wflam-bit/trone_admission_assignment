package nl.hva.wf.be.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Table(name="dish")
public class Dish {
    @Id
    @SequenceGenerator(
            name= "dish_sequence",
            sequenceName = "dish_Sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dish_sequence"
    )
    private Long id;
    private String name;
    private String country;
    @Enumerated(EnumType.STRING)
    private DishKind kind;
    private int prep_time;
    @JsonProperty("vegetarian")
    private boolean isVegetarian = false;
    @Column(updatable = false,columnDefinition = "timestamp(6)")
    private LocalDateTime creation;
    @Column(columnDefinition = "timestamp(6)")
    private LocalDateTime update;

    public Dish() {
    }


    public Dish(String name, String country, DishKind kind, int prep_time, boolean isVegetarian) {
        this.name = name;
        this.country = country;
        this.kind = kind;
        this.prep_time = prep_time;
        this.isVegetarian = isVegetarian;
    }



    @PrePersist
    public void prePersist() {
        creation = LocalDateTime.now();
        update = LocalDateTime.now();

    }

    @PreUpdate
    public void preUpdate() {
        update = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DishKind getKind() {
        return kind;
    }

    public void setKind(DishKind kind) {
        this.kind = kind;
    }

    public int getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(int prep_time) {
        this.prep_time = prep_time;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalDateTime update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", kind='" + kind + '\'' +
                ", prep_time=" + prep_time +
                ", isVegetarian=" + isVegetarian +
                ", creation=" + creation +
                ", update=" + update +
                '}';
    }
}
