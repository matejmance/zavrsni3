package hr.java.web.zavrsnizadatak3.Service;

import hr.java.web.zavrsnizadatak3.domain.ProgramObrazovanja;
import hr.java.web.zavrsnizadatak3.dto.ProgramObrazovanjaCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.ProgramObrazovanjaDTO;
import hr.java.web.zavrsnizadatak3.repository.ProgramObrazovanjaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramObrazovanjaServiceImpl
        implements ProgramObrazovanjaService {

    private final ProgramObrazovanjaRepository programObrazovanjaRepository;

    public ProgramObrazovanjaServiceImpl(
            ProgramObrazovanjaRepository programObrazovanjaRepository
    ) {
        this.programObrazovanjaRepository = programObrazovanjaRepository;
    }

    @Override
    public List<ProgramObrazovanjaDTO> findAll() {
        return programObrazovanjaRepository.findAll()
                .stream()
                .map(ProgramObrazovanjaDTO::new)
                .toList();
    }

    @Override
    public Optional<ProgramObrazovanjaDTO> findById(Integer id) {
        return programObrazovanjaRepository.findById(id)
                .map(ProgramObrazovanjaDTO::new);
    }

    @Override
    @Transactional
    public ProgramObrazovanjaDTO create(ProgramObrazovanjaCreateDTO dto) {
        ProgramObrazovanja program = new ProgramObrazovanja();

        program.setNaziv(dto.naziv().strip());
        program.setCsvet(dto.csvet());

        ProgramObrazovanja saved =
                programObrazovanjaRepository.save(program);

        return new ProgramObrazovanjaDTO(saved);
    }
}