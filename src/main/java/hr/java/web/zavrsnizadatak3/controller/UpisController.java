package hr.java.web.zavrsnizadatak3.controller;

import hr.java.web.zavrsnizadatak3.dto.UpisCreateDTO;
import hr.java.web.zavrsnizadatak3.dto.UpisDTO;
import hr.java.web.zavrsnizadatak3.Service.UpisService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/upisi")
public class UpisController {

    private final UpisService upisService;

    public UpisController(UpisService upisService) {
        this.upisService = upisService;
    }

    @GetMapping
    public List<UpisDTO> findAll() {
        return upisService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpisDTO> findById(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.of(upisService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UpisDTO> create(
            @Valid @RequestBody UpisCreateDTO dto
    ) {
        UpisDTO created = upisService.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }
}