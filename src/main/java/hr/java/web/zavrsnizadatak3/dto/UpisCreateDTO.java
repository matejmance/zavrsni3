package hr.java.web.zavrsnizadatak3.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpisCreateDTO(

        @NotNull(message = "ID polaznika je obavezan")
        @Positive(message = "ID polaznika mora biti veći od 0")
        Integer polaznikId,

        @NotNull(message = "ID programa je obavezan")
        @Positive(message = "ID programa mora biti veći od 0")
        Integer programObrazovanjaId
) {
}