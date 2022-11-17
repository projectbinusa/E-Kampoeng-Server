package com.data.penduduk.controller;

import com.data.penduduk.model.AgendaWarga;
import com.data.penduduk.service.AgendaWargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AgendaWargaController {
    @Autowired
    AgendaWargaService agendaWargaService;

    @GetMapping("/agenda-warga")
    public ResponseEntity<?> getAllAgenda() {
        List<AgendaWarga> agendaWarga = agendaWargaService.getAllAgenda();
        return new ResponseEntity<>(agendaWarga, HttpStatus.OK);
    }

    @GetMapping("/agenda-warga-{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        AgendaWarga agendaWarga = agendaWargaService.getAgendaById(id);
        return new ResponseEntity<>(agendaWarga, HttpStatus.OK);
    }

    @PostMapping("/add-agenda-warga")
    public ResponseEntity<?> addAgenda(@RequestBody AgendaWarga agendaWarga) {
        AgendaWarga agendaWarga1 = agendaWargaService.createAgenda(agendaWarga);
        return new ResponseEntity<>(agendaWarga1, HttpStatus.CREATED);
    }

    @PutMapping("/agenda-warga-{id}")
    public ResponseEntity<?> updateAgenda(@PathVariable("id") Long id, @RequestBody AgendaWarga agendaWarga) {
        AgendaWarga agendaWarga1 = agendaWargaService.editAgenda(id, agendaWarga.getNama_kegiatan(), agendaWarga.getDeskripsi(), agendaWarga.getTgl_dilaksanakan());
        return new ResponseEntity<>(agendaWarga1, HttpStatus.OK);
    }

    @DeleteMapping("/agenda-warga-{id}")
    public ResponseEntity<?> deleteAgenda(@PathVariable("id") Long id) {
        agendaWargaService.deleteAgenda(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
}
