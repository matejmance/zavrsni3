package hr.java.web.zavrsnizadatak3.Service;

import hr.java.web.zavrsnizadatak3.dto.ProgramObrazovanjaCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.ProgramObrazovanjaDTO;

import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaService {

    List<ProgramObrazovanjaDTO> findAll();

    Optional<ProgramObrazovanjaDTO> findById(Integer id);

    ProgramObrazovanjaDTO create(ProgramObrazovanjaCreateDTO dto);
}