package com.data.penduduk.service;

import com.data.penduduk.model.AgendaWarga;
import com.data.penduduk.repository.AgendaWargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AgendaWargaService {
    @Autowired
    AgendaWargaRepository agendaWargaRepository;

    public List<AgendaWarga> getAllAgenda() {
        return agendaWargaRepository.findAll();
    }

    public AgendaWarga getAgendaById(Long id) {
        return agendaWargaRepository.findById(id).orElse(null);
    }

    public AgendaWarga createAgenda(AgendaWarga agendaWarga) {
        return agendaWargaRepository.save(agendaWarga);
    }

    public AgendaWarga editAgenda(Long id, String nama_kegiatan, String deskripsi, Date tgl_dilaksanakan) {
        AgendaWarga agendaWarga = agendaWargaRepository.findById(id).orElse(null);
        agendaWarga.setNama_kegiatan(nama_kegiatan);
        agendaWarga.setTgl_dilaksanakan(tgl_dilaksanakan);
        agendaWarga.setDeskripsi(deskripsi);
        return agendaWargaRepository.save(agendaWarga);
    }

    public void deleteAgenda(Long id) {
        agendaWargaRepository.deleteById(id);
    }
}
