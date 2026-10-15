package hr.java.web.zavrsnizadatak3.dto;

import hr.java.web.zavrsnizadatak3.domain.Upis;

public record UpisDTO(
        Integer id,
        PolaznikDTO polaznik,
        ProgramObrazovanjaDTO programObrazovanja
) {

    public UpisDTO(Upis upis) {
        this(
                upis.getId(),
                new PolaznikDTO(upis.getPolaznik()),
                new ProgramObrazovanjaDTO(
                        upis.getProgramObrazovanja()
                )
        );
    }
}