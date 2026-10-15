package hr.java.web.zavrsnizadatak3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProgramObrazovanja")
public class ProgramObrazovanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgramObrazovanjaID")
    private Integer id;

    @Column(name = "Naziv", nullable = false)
    private String naziv;

    @Column(name = "CSVET", nullable = false)
    private Integer csvet;

    public ProgramObrazovanja() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getCsvet() {
        return csvet;
    }

    public void setCsvet(Integer csvet) {
        this.csvet = csvet;
    }
}