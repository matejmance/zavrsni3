package hr.java.web.zavrsnizadatak3.Service;

import hr.java.web.zavrsnizadatak3.domain.Polaznik;
import hr.java.web.zavrsnizadatak3.domain.ProgramObrazovanja;
import hr.java.web.zavrsnizadatak3.domain.Upis;
import hr.java.web.zavrsnizadatak3.dto.UpisCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.UpisDTO;
import hr.java.web.zavrsnizadatak3.repository.PolaznikRepository;
import hr.java.web.zavrsnizadatak3.repository.ProgramObrazovanjaRepository;
import hr.java.web.zavrsnizadatak3.repository.UpisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UpisServiceImpl implements UpisService {

    private final UpisRepository upisRepository;
    private final PolaznikRepository polaznikRepository;
    private final ProgramObrazovanjaRepository programObrazovanjaRepository;

    public UpisServiceImpl(
            UpisRepository upisRepository,
            PolaznikRepository polaznikRepository,
            ProgramObrazovanjaRepository programObrazovanjaRepository
    ) {
        this.upisRepository = upisRepository;
        this.polaznikRepository = polaznikRepository;
        this.programObrazovanjaRepository = programObrazovanjaRepository;
    }

    @Override
    public List<UpisDTO> findAll() {
        return upisRepository.findAll()
                .stream()
                .map(UpisDTO::new)
                .toList();
    }

    @Override
    public Optional<UpisDTO> findById(Integer id) {
        return upisRepository.findById(id)
                .map(UpisDTO::new);
    }

    @Override
    @Transactional
    public UpisDTO create(UpisCreateDTO dto) {
        Polaznik polaznik = polaznikRepository
                .findById(dto.polaznikId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Polaznik nije pronađen."
                ));

        ProgramObrazovanja program = programObrazovanjaRepository
                .findById(dto.programObrazovanjaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Program obrazovanja nije pronađen."
                ));

        Upis upis = new Upis();
        upis.setPolaznik(polaznik);
        upis.setProgramObrazovanja(program);

        Upis saved = upisRepository.save(upis);

        return new UpisDTO(saved);
    }
}