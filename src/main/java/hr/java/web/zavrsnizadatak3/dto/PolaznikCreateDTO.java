package hr.java.web.zavrsnizadatak3.dto;

import jakarta.validation.constraints.NotBlank;

public record PolaznikCreateDTO(

        @NotBlank(message = "Ime je obavezno")
        String ime,

        @NotBlank(message = "Prezime je obavezno")
        String prezime
) {
}