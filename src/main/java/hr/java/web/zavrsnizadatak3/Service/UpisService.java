package hr.java.web.zavrsnizadatak3.Service;

import hr.java.web.zavrsnizadatak3.dto.UpisCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.UpisDTO;

import java.util.List;
import java.util.Optional;

public interface UpisService {

    List<UpisDTO> findAll();

    Optional<UpisDTO> findById(Integer id);

    UpisDTO create(UpisCreateDTO dto);
}