package hr.java.web.zavrsnizadatak3.dto;

import hr.java.web.zavrsnizadatak3.domain.Polaznik;

public record PolaznikDTO(
        Integer id,
        String ime,
        String prezime
) {

    public PolaznikDTO(Polaznik polaznik) {
        this(
                polaznik.getId(),
                polaznik.getIme(),
                polaznik.getPrezime()
        );
    }
}