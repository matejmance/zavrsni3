package hr.java.web.zavrsnizadatak3.Service;

import hr.java.web.zavrsnizadatak3.domain.Polaznik;
import hr.java.web.zavrsnizadatak3.dto.PolaznikCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.PolaznikDTO;
import hr.java.web.zavrsnizadatak3.repository.PolaznikRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PolaznikServiceImpl implements PolaznikService {

    private final PolaznikRepository polaznikRepository;

    public PolaznikServiceImpl(
            PolaznikRepository polaznikRepository) {
        this.polaznikRepository = polaznikRepository;
    }

    @Override
    public List<PolaznikDTO> findAll() {
        return polaznikRepository.findAll()
                .stream()
                .map(PolaznikDTO::new)
                .toList();
    }

    @Override
    public Optional<PolaznikDTO> findById(Integer id) {
        return polaznikRepository.findById(id)
                .map(PolaznikDTO::new);
    }

    @Override
    @Transactional
    public PolaznikDTO create(PolaznikCreateDTO dto) {
        Polaznik polaznik = new Polaznik();
        polaznik.setIme(dto.ime().strip());
        polaznik.setPrezime(dto.prezime().strip());

        Polaznik saved = polaznikRepository.save(polaznik);

        return new PolaznikDTO(saved);
    }
}