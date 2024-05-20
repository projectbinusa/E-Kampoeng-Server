package com.e_kampoeng.controller;

import com.e_kampoeng.model.e_kas.PemasukanModel;
import com.e_kampoeng.model.e_kas.PengeluaranModel;
import com.e_kampoeng.model.e_kas.SaldoModel;
import com.e_kampoeng.request.PemasukanDTO;
import com.e_kampoeng.request.PengeluaranDTO;
import com.e_kampoeng.service.e_kas_service.PemasukanService;
import com.e_kampoeng.service.e_kas_service.PengeluaranService;
import com.e_kampoeng.service.e_kas_service.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/e-kas")
public class EKasController {

    @Autowired
    private PemasukanService pemasukanService;

    @Autowired
    private PengeluaranService pengeluaranService;

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/saldo")
    public SaldoModel getSaldo() {
        return saldoService.getSaldo();
    }

    @PostMapping("/pemasukan")
    public void addPemasukan(@RequestBody PemasukanDTO pemasukanDto) {
        pemasukanService.addPemasukan(pemasukanDto);
    }

    @GetMapping("/pemasukan")
    public Page<PemasukanModel> getAllPemasukan(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return pemasukanService.getAllPemasukan(page, size);
    }

    @GetMapping("/pemasukan/{id}")
    public PemasukanModel getDetailPemasukan(@PathVariable Long id) {
        return pemasukanService.getDetailPemasukan(id);
    }

    @PutMapping("/pemasukan/{id}")
    public PemasukanModel updatePemasukan(@PathVariable Long id, @RequestBody PemasukanDTO pemasukanDto) {
        return pemasukanService.updatePemasukan(id, pemasukanDto);
    }

    @DeleteMapping("/pemasukan/{id}")
    public void deletePemasukan(@PathVariable Long id) {
        pemasukanService.deletePemasukan(id);
    }

    @PostMapping("/pengeluaran")
    public void addPengeluaran(@RequestBody PengeluaranDTO pengeluaranDto) {
        pengeluaranService.addPengeluaran(pengeluaranDto);
    }

    @GetMapping("/pengeluaran")
    public Page<PengeluaranModel> getAllPengeluaran(Pageable pageable) {
        return pengeluaranService.getAllPengeluaran(pageable);
    }

    @GetMapping("/pengeluaran/{id}")
    public PengeluaranModel getDetailPengeluaran(@PathVariable Long id) {
        return pengeluaranService.getDetailPengeluaran(id);
    }

    @PutMapping("/pengeluaran/{id}")
    public PengeluaranModel updatePengeluaran(@PathVariable Long id, @RequestBody PengeluaranDTO pengeluaranDto) {
        return pengeluaranService.updatePengeluaran(id, pengeluaranDto);
    }

    @DeleteMapping("/pengeluaran/{id}")
    public void deletePengeluaran(@PathVariable Long id) {
        pengeluaranService.deletePengeluaran(id);
    }
}
