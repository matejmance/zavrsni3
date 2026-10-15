package hr.java.web.zavrsnizadatak3.Service;

import hr.java.web.zavrsnizadatak3.dto.PolaznikCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.PolaznikDTO;

import java.util.List;
import java.util.Optional;

public interface PolaznikService {

    List<PolaznikDTO> findAll();

    Optional<PolaznikDTO> findById(Integer id);

    PolaznikDTO create(PolaznikCreateDTO dto);
}