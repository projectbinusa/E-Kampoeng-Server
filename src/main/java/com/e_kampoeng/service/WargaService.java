package com.e_kampoeng.service;

import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.request.WargaRequestDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class WargaService {
    @Autowired
    private WargaRepository wargaRepository;

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    public List<WargaModel> getAllWarga() {
        return wargaRepository.findAll();
    }

    public WargaModel createWarga(WargaRequestDTO requestDTO) {
        WargaModel warga = new WargaModel();
        warga.setNama(requestDTO.getNama());
        warga.setTempat_lahir(requestDTO.getTempat_lahir());
        warga.setTanggal_lahir(requestDTO.getTanggal_lahir());
        warga.setJenis_kelamin(requestDTO.getJenis_kelamin());
        warga.setAgama(requestDTO.getAgama());
        warga.setNik(requestDTO.getNik());
        warga.setNo_kk(requestDTO.getNo_kk());
        warga.setStatus_dalam_keluarga(requestDTO.getStatus_dalam_keluarga());
        warga.setStatus_kependudukan(requestDTO.getStatus_kependudukan());
        warga.setNo_anak(requestDTO.getNo_anak());
        warga.setPanjang_lahir(requestDTO.getPanjang_lahir());
        warga.setBerat_lahir(requestDTO.getBerat_lahir());
        warga.setNo_passport(requestDTO.getNo_passport());
        warga.setNama_ayah(requestDTO.getNama_ayah());
        warga.setNama_ibu(requestDTO.getNama_ibu());
        warga.setNo_telp(requestDTO.getNo_telp());
        warga.setEmail(requestDTO.getEmail());
        warga.setAlamat(requestDTO.getAlamat());
        warga.setTanggal_perkawinan(requestDTO.getTanggal_perkawinan());
        warga.setAlamat_sebelumnya(requestDTO.getAlamat_sebelumnya());
        warga.setNo_bpjs(requestDTO.getNo_bpjs());
        warga.setPendidikan_tempuh(requestDTO.getPendidikan_tempuh());
        warga.setPendidikan_terakhir(requestDTO.getPendidikan_terakhir());
        warga.setStatus_perkawinan(requestDTO.getStatus_perkawinan());
        warga.setGolongan_darah(requestDTO.getGolongan_darah());
        warga.setJenis_asuransi(requestDTO.getJenis_asuransi());
        warga.setJenis_kb(requestDTO.getJenis_kb());
        warga.setKesesuaian_tempat(requestDTO.getKesesuaian_tempat());
        warga.setSumber_air(requestDTO.getSumber_air());

        // Mengambil data Wilayah_RT dari repositori
        WilayahRTModel wilayahRT = wilayahRTRepository.findById(requestDTO.getWilayahRTId()).orElse(null);
        warga.setWilayahRT(wilayahRT);

        return wargaRepository.save(warga);
    }
    public Page<WargaModel> getWargaByRW(Long rwId, Pageable pageable) {
        return wargaRepository.findByWilayahRT_WilayahRW_Id(rwId, pageable);
    }

    public Page<WargaModel> getWargaByRT(Long rtId, Pageable pageable) {
        return wargaRepository.findByWilayahRT_Id(rtId, pageable);
    }

    public WargaModel updateWarga(Long wargaId, WargaRequestDTO requestDTO) {
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new RuntimeException("Warga not found with id: " + wargaId));

        // Set new values from the request DTO
        warga.setNama(requestDTO.getNama());
        warga.setTempat_lahir(requestDTO.getTempat_lahir());
        warga.setTanggal_lahir(requestDTO.getTanggal_lahir());
        warga.setJenis_kelamin(requestDTO.getJenis_kelamin());
        warga.setAgama(requestDTO.getAgama());
        warga.setNik(requestDTO.getNik());
        warga.setNo_kk(requestDTO.getNo_kk());
        warga.setStatus_dalam_keluarga(requestDTO.getStatus_dalam_keluarga());
        warga.setStatus_kependudukan(requestDTO.getStatus_kependudukan());
        warga.setNo_anak(requestDTO.getNo_anak());
        warga.setPanjang_lahir(requestDTO.getPanjang_lahir());
        warga.setBerat_lahir(requestDTO.getBerat_lahir());
        warga.setNo_passport(requestDTO.getNo_passport());
        warga.setNama_ayah(requestDTO.getNama_ayah());
        warga.setNama_ibu(requestDTO.getNama_ibu());
        warga.setNo_telp(requestDTO.getNo_telp());
        warga.setEmail(requestDTO.getEmail());
        warga.setAlamat(requestDTO.getAlamat());
        warga.setTanggal_perkawinan(requestDTO.getTanggal_perkawinan());
        warga.setAlamat_sebelumnya(requestDTO.getAlamat_sebelumnya());
        warga.setNo_bpjs(requestDTO.getNo_bpjs());
        warga.setPendidikan_tempuh(requestDTO.getPendidikan_tempuh());
        warga.setPendidikan_terakhir(requestDTO.getPendidikan_terakhir());
        warga.setStatus_perkawinan(requestDTO.getStatus_perkawinan());
        warga.setGolongan_darah(requestDTO.getGolongan_darah());
        warga.setJenis_asuransi(requestDTO.getJenis_asuransi());
        warga.setJenis_kb(requestDTO.getJenis_kb());
        warga.setKesesuaian_tempat(requestDTO.getKesesuaian_tempat());
        warga.setSumber_air(requestDTO.getSumber_air());

        // Update WilayahRT only if the ID in the request DTO is different
        if (!Objects.equals(warga.getWilayahRT().getId(), requestDTO.getWilayahRTId())) {
            WilayahRTModel wilayahRT = wilayahRTRepository.findById(requestDTO.getWilayahRTId())
                    .orElseThrow(() -> new RuntimeException("Wilayah RT not found with id: " + requestDTO.getWilayahRTId()));
            warga.setWilayahRT(wilayahRT);
        }

        return wargaRepository.save(warga);
    }

    public ResponseEntity<?> deleteWarga(Long wargaId) {
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new RuntimeException("Warga not found with id: " + wargaId));

        // Delete the warga
        wargaRepository.delete(warga);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Warga with id " + wargaId + " has been deleted successfully");
    }

    public byte[] exportToExcel() throws IOException {
        List<WargaModel> allWarga = wargaRepository.findAll();
        return generateExcel(allWarga);
    }

    public byte[] exportToExcelByRWId(Long rwId) throws IOException {
        Page<WargaModel> wargaByRW = wargaRepository.findByWilayahRT_WilayahRW_Id(rwId, Pageable.unpaged());
        return generateExcel(wargaByRW.getContent());
    }

    public byte[] exportToExcelByRTId(Long rtId) throws IOException {
        Page<WargaModel> wargaByRT = wargaRepository.findByWilayahRT_Id(rtId, Pageable.unpaged());
        return generateExcel(wargaByRT.getContent());
    }


    private byte[] generateExcel(List<WargaModel> wargaList) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Warga Data");

            // Header Row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Nama", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Agama", "NIK", "No KK", "Status dalam Keluarga", "Status Kependudukan", "No Anak", "Panjang Lahir", "Berat Lahir", "No Passport", "Nama Ayah", "Nama Ibu", "No Telp", "Email", "Alamat", "Tanggal Perkawinan", "Alamat Sebelumnya", "No BPJS", "Pendidikan Tempuh", "Pendidikan Terakhir", "Status Perkawinan", "Golongan Darah", "Jenis Asuransi", "Jenis KB", "Kesesuaian Tempat", "Sumber Air"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Data Rows
            int rowNum = 1;
            for (WargaModel warga : wargaList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(warga.getNama());
                row.createCell(1).setCellValue(warga.getTempat_lahir());
                row.createCell(2).setCellValue(warga.getTanggal_lahir().toString()); // Assuming tanggal_lahir is of type java.util.Date
                row.createCell(3).setCellValue(warga.getJenis_kelamin());
                row.createCell(4).setCellValue(warga.getAgama());
                row.createCell(5).setCellValue(warga.getNik());
                row.createCell(6).setCellValue(warga.getNo_kk());
                row.createCell(7).setCellValue(warga.getStatus_dalam_keluarga());
                row.createCell(8).setCellValue(warga.getStatus_kependudukan());
                row.createCell(9).setCellValue(warga.getNo_anak());
                row.createCell(10).setCellValue(warga.getPanjang_lahir());
                row.createCell(11).setCellValue(warga.getBerat_lahir());
                row.createCell(12).setCellValue(warga.getNo_passport());
                row.createCell(13).setCellValue(warga.getNama_ayah());
                row.createCell(14).setCellValue(warga.getNama_ibu());
                row.createCell(15).setCellValue(warga.getNo_telp());
                row.createCell(16).setCellValue(warga.getEmail());
                row.createCell(17).setCellValue(warga.getAlamat());
                row.createCell(18).setCellValue(warga.getTanggal_perkawinan().toString()); // Assuming tanggal_perkawinan is of type java.util.Date
                row.createCell(19).setCellValue(warga.getAlamat_sebelumnya());
                row.createCell(20).setCellValue(warga.getNo_bpjs());
                row.createCell(21).setCellValue(warga.getPendidikan_tempuh());
                row.createCell(22).setCellValue(warga.getPendidikan_terakhir());
                row.createCell(23).setCellValue(warga.getStatus_perkawinan());
                row.createCell(24).setCellValue(warga.getGolongan_darah());
                row.createCell(25).setCellValue(warga.getJenis_asuransi());
                row.createCell(26).setCellValue(warga.getJenis_kb());
                row.createCell(27).setCellValue(warga.getKesesuaian_tempat());
                row.createCell(28).setCellValue(warga.getSumber_air());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }


}
