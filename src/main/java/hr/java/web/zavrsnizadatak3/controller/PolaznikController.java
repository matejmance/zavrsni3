package hr.java.web.zavrsnizadatak3.controller;

import hr.java.web.zavrsnizadatak3.dto.PolaznikCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.PolaznikDTO;
import hr.java.web.zavrsnizadatak3.Service.PolaznikService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/polaznici")
public class PolaznikController {

    private final PolaznikService polaznikService;

    public PolaznikController(PolaznikService polaznikService) {
        this.polaznikService = polaznikService;
    }

    @GetMapping
    public List<PolaznikDTO> findAll() {
        return polaznikService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolaznikDTO> findById(
            @PathVariable("id") Integer id) {

        return ResponseEntity.of(polaznikService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PolaznikDTO> create(
            @Valid @RequestBody PolaznikCreateDTO dto) {

        PolaznikDTO saved = polaznikService.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }
}