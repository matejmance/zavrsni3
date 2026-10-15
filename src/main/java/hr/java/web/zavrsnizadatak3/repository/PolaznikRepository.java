package hr.java.web.zavrsnizadatak3.repository;

import hr.java.web.zavrsnizadatak3.domain.Polaznik;

import java.util.List;
import java.util.Optional;

public interface PolaznikRepository {

    List<Polaznik> findAll();

    Optional<Polaznik> findById(Integer id);

    <S extends Polaznik> S save(S polaznik);
}