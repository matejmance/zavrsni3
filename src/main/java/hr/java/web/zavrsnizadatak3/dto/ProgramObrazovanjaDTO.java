package hr.java.web.zavrsnizadatak3.dto;

import hr.java.web.zavrsnizadatak3.domain.ProgramObrazovanja;

public record ProgramObrazovanjaDTO(
        Integer id,
        String naziv,
        Integer csvet
) {

    public ProgramObrazovanjaDTO(
            ProgramObrazovanja programObrazovanja) {
        this(
                programObrazovanja.getId(),
                programObrazovanja.getNaziv(),
                programObrazovanja.getCsvet()
        );
    }
}