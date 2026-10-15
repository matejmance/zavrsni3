package hr.java.web.zavrsnizadatak3.controller;

import hr.java.web.zavrsnizadatak3.dto.ProgramObrazovanjaCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.ProgramObrazovanjaDTO;
import hr.java.web.zavrsnizadatak3.Service.ProgramObrazovanjaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/programi")
public class ProgramObrazovanjaController {

    private final ProgramObrazovanjaService programObrazovanjaService;

    public ProgramObrazovanjaController(
            ProgramObrazovanjaService programObrazovanjaService
    ) {
        this.programObrazovanjaService = programObrazovanjaService;
    }

    @GetMapping
    public List<ProgramObrazovanjaDTO> findAll() {
        return programObrazovanjaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramObrazovanjaDTO> findById(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.of(
                programObrazovanjaService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<ProgramObrazovanjaDTO> create(
            @Valid @RequestBody ProgramObrazovanjaCreateDTO dto
    ) {
        ProgramObrazovanjaDTO created =
                programObrazovanjaService.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }
}