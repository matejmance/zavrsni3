package hr.java.web.zavrsnizadatak3.repository;

import hr.java.web.zavrsnizadatak3.domain.Upis;

import java.util.List;
import java.util.Optional;

public interface UpisRepository {

    List<Upis> findAll();

    Optional<Upis> findById(Integer id);

    <S extends Upis> S save(S upis);
}