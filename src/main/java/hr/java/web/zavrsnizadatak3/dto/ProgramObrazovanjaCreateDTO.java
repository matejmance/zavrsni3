package hr.java.web.zavrsnizadatak3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProgramObrazovanjaCreateDTO(

        @NotBlank(message = "Naziv programa je obavezan")
        String naziv,

        @NotNull(message = "CSVET je obavezan")
        @Positive(message = "CSVET mora biti veći od 0")
        Integer csvet
) {
}