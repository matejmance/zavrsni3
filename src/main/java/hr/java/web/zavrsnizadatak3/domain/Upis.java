package hr.java.web.zavrsnizadatak3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Upis")
public class Upis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UpisID")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDPolaznik", nullable = false)
    private Polaznik polaznik;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDProgramObrazovanja", nullable = false)
    private ProgramObrazovanja programObrazovanja;

    public Upis() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public ProgramObrazovanja getProgramObrazovanja() {
        return programObrazovanja;
    }

    public void setProgramObrazovanja(
            ProgramObrazovanja programObrazovanja) {
        this.programObrazovanja = programObrazovanja;
    }
}