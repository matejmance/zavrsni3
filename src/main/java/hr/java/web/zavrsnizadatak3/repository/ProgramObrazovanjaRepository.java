package hr.java.web.zavrsnizadatak3.repository;

import hr.java.web.zavrsnizadatak3.domain.ProgramObrazovanja;

import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaRepository {

    List<ProgramObrazovanja> findAll();

    Optional<ProgramObrazovanja> findById(Integer id);

    <S extends ProgramObrazovanja> S save(S programObrazovanja);
}