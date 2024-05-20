package com.e_kampoeng.service;

import com.e_kampoeng.dto.UserDTO;
import com.e_kampoeng.dto.WargaDTO;
import com.e_kampoeng.exception.InternalErrorException;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.UserModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.model.WilayahRTModel;
import com.e_kampoeng.repository.UserRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.repository.WilayahRTRepository;
import com.e_kampoeng.request.ChangePasswordRequestDTO;
import com.e_kampoeng.request.WargaRequestDTO;
import com.e_kampoeng.request.WargaRequestRoleWargaDTO;
import com.e_kampoeng.request.WargaUpdateRequestDTO;
import com.google.api.pathtemplate.ValidationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;


@Service
public class WargaService {
    @Autowired
    private WargaRepository wargaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WilayahRTRepository wilayahRTRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<WargaModel> getAllWarga() {
        return wargaRepository.findAll();
    }

//    public WargaModel createWarga(WargaRequestDTO requestDTO) {
//        WargaModel warga = new WargaModel();
//        warga.setNama(requestDTO.getNama());
//        warga.setTempat_lahir(requestDTO.getTempat_lahir());
//        warga.setTanggal_lahir(requestDTO.getTanggal_lahir());
//        warga.setJenis_kelamin(requestDTO.getJenis_kelamin());
//        warga.setAgama(requestDTO.getAgama());
//        warga.setNik(requestDTO.getNik());
//        warga.setNo_kk(requestDTO.getNo_kk());
//        warga.setStatus_dalam_keluarga(requestDTO.getStatus_dalam_keluarga());
//        warga.setStatus_kependudukan(requestDTO.getStatus_kependudukan());
//        warga.setNo_anak(requestDTO.getNo_anak());
//        warga.setPanjang_lahir(requestDTO.getPanjang_lahir());
//        warga.setBerat_lahir(requestDTO.getBerat_lahir());
//        warga.setNo_passport(requestDTO.getNo_passport());
//        warga.setNama_ayah(requestDTO.getNama_ayah());
//        warga.setNama_ibu(requestDTO.getNama_ibu());
//        warga.setNo_telp(requestDTO.getNo_telp());
//        warga.setEmail(requestDTO.getEmail());
//        warga.setAlamat(requestDTO.getAlamat());
//        warga.setTanggal_perkawinan(requestDTO.getTanggal_perkawinan());
//        warga.setAlamat_sebelumnya(requestDTO.getAlamat_sebelumnya());
//        warga.setNo_bpjs(requestDTO.getNo_bpjs());
//        warga.setPendidikan_tempuh(requestDTO.getPendidikan_tempuh());
//        warga.setPendidikan_terakhir(requestDTO.getPendidikan_terakhir());
//        warga.setStatus_perkawinan(requestDTO.getStatus_perkawinan());
//        warga.setGolongan_darah(requestDTO.getGolongan_darah());
//        warga.setJenis_asuransi(requestDTO.getJenis_asuransi());
//        warga.setJenis_kb(requestDTO.getJenis_kb());
//        warga.setKesesuaian_tempat(requestDTO.getKesesuaian_tempat());
//        warga.setSumber_air(requestDTO.getSumber_air());
//
//        // Mengambil data Wilayah_RT dari repositori
//        WilayahRTModel wilayahRT = wilayahRTRepository.findById(requestDTO.getWilayahRTId()).orElse(null);
//        warga.setWilayahRT(wilayahRT);
//
//        return wargaRepository.save(warga);
//    }

    public List<WargaModel> getAllWargaByRole(String role) {
        return wargaRepository.findByRole(role);
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
        warga.setPassword(requestDTO.getNik());
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
                row.createCell(5).setCellValue(warga.getPassword());
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

    // Import Excell
    public ResponseEntity<?> importFromExcel(MultipartFile file) {
        try {
            List<WargaRequestDTO> importedWarga = readExcel(file);
            saveImportedData(importedWarga);
            return ResponseEntity.ok("Import berhasil");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal mengimpor data: " + e.getMessage());
        }
    }

    private List<WargaRequestDTO> readExcel(MultipartFile file) throws IOException {
        List<WargaRequestDTO> importedWarga = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            // Skip header row
            if (iterator.hasNext()) {
                iterator.next();
            }
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                WargaRequestDTO warga = new WargaRequestDTO();
                warga.setNama(getStringValueFromCell(currentRow.getCell(0)));
                warga.setTempat_lahir(getStringValueFromCell(currentRow.getCell(1)));
                warga.setTanggal_lahir(getDateValueFromCell(currentRow.getCell(2)));
                warga.setJenis_kelamin(getStringValueFromCell(currentRow.getCell(3)));
                warga.setAgama(getStringValueFromCell(currentRow.getCell(4)));
                warga.setNik(getStringValueFromCell(currentRow.getCell(5)));
                warga.setNo_kk(getStringValueFromCell(currentRow.getCell(6)));
                warga.setStatus_dalam_keluarga(getStringValueFromCell(currentRow.getCell(7)));
                warga.setStatus_kependudukan(getStringValueFromCell(currentRow.getCell(8)));
                warga.setNo_anak(getLongValueFromCell(currentRow.getCell(9)));
                warga.setPanjang_lahir(getDoubleValueFromCell(currentRow.getCell(10)));
                warga.setBerat_lahir(getDoubleValueFromCell(currentRow.getCell(11)));
                warga.setNo_passport(getLongValueFromCell(currentRow.getCell(12)));
                warga.setNama_ayah(getStringValueFromCell(currentRow.getCell(13)));
                warga.setNama_ibu(getStringValueFromCell(currentRow.getCell(14)));
                warga.setNo_telp(getStringValueFromCell(currentRow.getCell(15)));
                warga.setEmail(getStringValueFromCell(currentRow.getCell(16)));
                warga.setAlamat(getStringValueFromCell(currentRow.getCell(17)));
                warga.setTanggal_perkawinan(getDateValueFromCell(currentRow.getCell(18)));
                warga.setAlamat_sebelumnya(getStringValueFromCell(currentRow.getCell(19)));
                warga.setNo_bpjs(getLongValueFromCell(currentRow.getCell(20)));
                warga.setPendidikan_tempuh(getStringValueFromCell(currentRow.getCell(21)));
                warga.setPendidikan_terakhir(getStringValueFromCell(currentRow.getCell(22)));
                warga.setStatus_perkawinan(getStringValueFromCell(currentRow.getCell(23)));
                warga.setGolongan_darah(getStringValueFromCell(currentRow.getCell(24)));
                warga.setJenis_asuransi(getStringValueFromCell(currentRow.getCell(25)));
                warga.setJenis_kb(getStringValueFromCell(currentRow.getCell(26)));
                warga.setKesesuaian_tempat(getStringValueFromCell(currentRow.getCell(27)));
                warga.setSumber_air(getStringValueFromCell(currentRow.getCell(28)));
                warga.setWilayahRTId(getLongValueFromCell(currentRow.getCell(29)));
                importedWarga.add(warga);
            }
        }
        return importedWarga;
    }

    private String getStringValueFromCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    private Integer getIntValueFromCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.NUMERIC);
        return (int) cell.getNumericCellValue();
    }

    private Integer getNikValueFromCell(Cell cell) {
        return getIntValueFromCell(cell);
    }

    private Long getLongValueFromCell(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            double numericValue = cell.getNumericCellValue();
            // Check if the numeric value has a fractional part
            if (numericValue == (long) numericValue) {
                return (long) numericValue; // Return as Long if it's an integer
            } else {
                // Handle decimal values here, such as rounding or throwing an exception
                // For example, rounding to the nearest whole number:
                return Math.round(numericValue);
            }
        } else if (cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getStringCellValue();
            try {
                return Long.valueOf(cellValue);
            } catch (NumberFormatException e) {
                // Handle the parse exception
                e.printStackTrace();
                return null;
            }
        } else {
            // Handle other cell types if needed
            return null;
        }
    }


    private Double getDoubleValueFromCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        return cell.getNumericCellValue();
    }

    private Date getDateValueFromCell(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getDateCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String dateString = cell.getStringCellValue();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(dateString);
            } catch (ParseException e) {
                // Handle the parse exception
                e.printStackTrace();
                return null;
            }
        } else {
            // Handle other cell types if needed
            return null;
        }
    }


    private void saveImportedData(List<WargaRequestDTO> importedWarga) {
        for (WargaRequestDTO wargaDTO : importedWarga) {
            if (wargaDTO.getWilayahRTId() == null) {
                System.out.println("Warning: wilayahRTId is null for warga with nama: " + wargaDTO.getNama());
                continue; // Lanjutkan ke wargaDTO berikutnya jika wilayahRTId kosong
            }

            WargaModel warga = new WargaModel();
            warga.setNama(wargaDTO.getNama());
            warga.setTempat_lahir(wargaDTO.getTempat_lahir());
            warga.setTanggal_lahir(wargaDTO.getTanggal_lahir());
            warga.setJenis_kelamin(wargaDTO.getJenis_kelamin());
            warga.setAgama(wargaDTO.getAgama());
            warga.setPassword(wargaDTO.getNik());
            warga.setNo_kk(wargaDTO.getNo_kk());
            warga.setStatus_dalam_keluarga(wargaDTO.getStatus_dalam_keluarga());
            warga.setStatus_kependudukan(wargaDTO.getStatus_kependudukan());
            warga.setNo_anak(wargaDTO.getNo_anak());
            warga.setPanjang_lahir(wargaDTO.getPanjang_lahir());
            warga.setBerat_lahir(wargaDTO.getBerat_lahir());
            warga.setNo_passport(wargaDTO.getNo_passport());
            warga.setNama_ayah(wargaDTO.getNama_ayah());
            warga.setNama_ibu(wargaDTO.getNama_ibu());
            warga.setNo_telp(wargaDTO.getNo_telp());
            warga.setEmail(wargaDTO.getEmail());
            warga.setAlamat(wargaDTO.getAlamat());
            warga.setTanggal_perkawinan(wargaDTO.getTanggal_perkawinan());
            warga.setAlamat_sebelumnya(wargaDTO.getAlamat_sebelumnya());
            warga.setNo_bpjs(wargaDTO.getNo_bpjs());
            warga.setPendidikan_tempuh(wargaDTO.getPendidikan_tempuh());
            warga.setPendidikan_terakhir(wargaDTO.getPendidikan_terakhir());
            warga.setStatus_perkawinan(wargaDTO.getStatus_perkawinan());
            warga.setGolongan_darah(wargaDTO.getGolongan_darah());
            warga.setJenis_asuransi(wargaDTO.getJenis_asuransi());
            warga.setJenis_kb(wargaDTO.getJenis_kb());
            warga.setKesesuaian_tempat(wargaDTO.getKesesuaian_tempat());
            warga.setSumber_air(wargaDTO.getSumber_air());

            // Mengatur WilayahRT jika wilayahRTId tidak null
            if (wargaDTO.getWilayahRTId() != null) {
                Optional<WilayahRTModel> optionalWilayahRT = wilayahRTRepository.findById(wargaDTO.getWilayahRTId());
                if (optionalWilayahRT.isPresent()) {
                    warga.setWilayahRT(optionalWilayahRT.get());
                } else {
                    throw new EntityNotFoundException("WilayahRTModel not found for id: " + wargaDTO.getWilayahRTId());
                }
            }

            // Menyimpan warga ke database
            wargaRepository.save(warga);
        }
    }

    @Transactional
    public WargaModel saveWargaRoleWarga(WargaRequestRoleWargaDTO wargaDTO, String email) throws Exception {
        // Periksa apakah email sudah ada dalam basis data
        if (wargaRepository.findByEmail(wargaDTO.getEmail()) != null) {
            throw new ValidationException("Email already exists");
        }

        WargaModel warga = new WargaModel();
        // Mengisi data warga dari DTO
        warga.setTempat_lahir(wargaDTO.getTempat_lahir());
        warga.setTanggal_lahir(wargaDTO.getTanggal_lahir());
        warga.setJenis_kelamin(wargaDTO.getJenis_kelamin());
        warga.setAgama(wargaDTO.getAgama());
        warga.setPassword(bcryptEncoder.encode(wargaDTO.getNik()));
        warga.setNo_kk(wargaDTO.getNo_kk());
        warga.setStatus_dalam_keluarga(wargaDTO.getStatus_dalam_keluarga());
        warga.setStatus_kependudukan(wargaDTO.getStatus_kependudukan());
        warga.setNo_anak(wargaDTO.getNo_anak());
        warga.setPanjang_lahir(wargaDTO.getPanjang_lahir());
        warga.setBerat_lahir(wargaDTO.getBerat_lahir());
        warga.setNo_passport(wargaDTO.getNo_passport());
        warga.setNama(wargaDTO.getNama());
        warga.setNama_ayah(wargaDTO.getNama_ayah());
        warga.setNama_ibu(wargaDTO.getNama_ibu());
        warga.setNo_telp(wargaDTO.getNo_telp());
        warga.setAlamat(wargaDTO.getAlamat());
        warga.setTanggal_perkawinan(wargaDTO.getTanggal_perkawinan());
        warga.setAlamat_sebelumnya(wargaDTO.getAlamat_sebelumnya());
        warga.setNo_bpjs(wargaDTO.getNo_bpjs());
        warga.setNo_passport(wargaDTO.getNo_passport());
        warga.setPendidikan_tempuh(wargaDTO.getPendidikan_tempuh());
        warga.setPendidikan_terakhir(wargaDTO.getPendidikan_terakhir());
        warga.setStatus_perkawinan(wargaDTO.getStatus_perkawinan());
        warga.setGolongan_darah(wargaDTO.getGolongan_darah());
        warga.setJenis_asuransi(wargaDTO.getJenis_asuransi());
        warga.setJenis_kb(wargaDTO.getJenis_kb());
        warga.setKesesuaian_tempat(wargaDTO.getKesesuaian_tempat());
        warga.setSumber_air(wargaDTO.getSumber_air());
        warga.setEmail(wargaDTO.getEmail());
        warga.setRole("warga");

        // Mendapatkan informasi wilayah RT dari warga yang sedang login
        WargaModel loggedInWarga = wargaRepository.findByEmail(email);
        if (loggedInWarga == null) {
            throw new NotFoundException("Logged in warga not found");
        }

        // Jika warga yang login adalah kepala RT, maka ambil wilayah RT-nya
        if ("rt".equals(loggedInWarga.getRole())) {
            WilayahRTModel wilayahRT = loggedInWarga.getWilayahRT();
            if (wilayahRT == null) {
                throw new NotFoundException("Wilayah RT not found for the logged in RT");
            }
            warga.setWilayahRT(wilayahRT);
        } else {
            throw new ValidationException("The logged in warga is not a head RT");
        }

        // Simpan data warga
        return wargaRepository.save(warga);
    }

    @Transactional
    public WargaModel saveWargaRoleRt(WargaRequestDTO wargaDTO) throws Exception {
        // Periksa apakah email sudah ada dalam basis data
        if (wargaRepository.findByEmail(wargaDTO.getEmail()) != null) {
            throw new ValidationException("Email already exists");
        }

        WargaModel warga = new WargaModel();
        warga.setTempat_lahir(wargaDTO.getTempat_lahir());
        warga.setTanggal_lahir(wargaDTO.getTanggal_lahir());
        warga.setJenis_kelamin(wargaDTO.getJenis_kelamin());
        warga.setAgama(wargaDTO.getAgama());
        warga.setPassword(bcryptEncoder.encode(wargaDTO.getNik()));
        warga.setNo_kk(wargaDTO.getNo_kk());
        warga.setStatus_dalam_keluarga(wargaDTO.getStatus_dalam_keluarga());
        warga.setStatus_kependudukan(wargaDTO.getStatus_kependudukan());
        warga.setNo_anak(wargaDTO.getNo_anak());
        warga.setPanjang_lahir(wargaDTO.getPanjang_lahir());
        warga.setBerat_lahir(wargaDTO.getBerat_lahir());
        warga.setNo_passport(wargaDTO.getNo_passport());
        warga.setNama_ayah(wargaDTO.getNama_ayah());
        warga.setNama_ibu(wargaDTO.getNama_ibu());
        warga.setNo_telp(wargaDTO.getNo_telp());
        warga.setAlamat(wargaDTO.getAlamat());
        warga.setTanggal_perkawinan(wargaDTO.getTanggal_perkawinan());
        warga.setAlamat_sebelumnya(wargaDTO.getAlamat_sebelumnya());
        warga.setNo_bpjs(wargaDTO.getNo_bpjs());
        warga.setPendidikan_tempuh(wargaDTO.getPendidikan_tempuh());
        warga.setPendidikan_terakhir(wargaDTO.getPendidikan_terakhir());
        warga.setStatus_perkawinan(wargaDTO.getStatus_perkawinan());
        warga.setGolongan_darah(wargaDTO.getGolongan_darah());
        warga.setJenis_asuransi(wargaDTO.getJenis_asuransi());
        warga.setJenis_kb(wargaDTO.getJenis_kb());
        warga.setKesesuaian_tempat(wargaDTO.getKesesuaian_tempat());
        warga.setSumber_air(wargaDTO.getSumber_air());
        warga.setEmail(wargaDTO.getEmail());
        warga.setRole("rt");
        warga.setNama(wargaDTO.getNama());

        // Ambil objek WilayahRTModel
        WilayahRTModel wilayahRT = wilayahRTRepository.findById(wargaDTO.getWilayahRTId()).orElse(null);
        if (wilayahRT == null) {
            throw new NotFoundException("Wilayah RT not found with id: " + wargaDTO.getWilayahRTId());
        }

        // Periksa apakah wilayah RT tersebut sudah memiliki kepala RT
        if (wilayahRT.getKepalaRt() != null) {
            throw new RuntimeException("The specified RT area already has a head RT");
        }

        // Set kepala RT
        wilayahRT.setKepalaRt(warga);
        wilayahRTRepository.save(wilayahRT);

        // Set wilayah RT ke entitas warga
        warga.setWilayahRT(wilayahRT);

        return wargaRepository.save(warga);
    }

    @Transactional
    public void releaseHeadRT(Long rtId) throws NotFoundException {
        // Cari wilayah RT berdasarkan ID
        WilayahRTModel wilayahRT = wilayahRTRepository.findById(rtId)
                .orElseThrow(() -> new NotFoundException("Wilayah RT not found with id: " + rtId));

        // Ambil warga yang menjadi kepala RT
        WargaModel kepalaRt = wilayahRT.getKepalaRt();
        if (kepalaRt == null) {
            throw new NotFoundException("No head RT found for the specified RT");
        }

        // Set kepala RT menjadi null
        wilayahRT.setKepalaRt(null);
        wilayahRTRepository.save(wilayahRT);

        // Set role warga menjadi warga biasa
        kepalaRt.setRole("warga");
        wargaRepository.save(kepalaRt);
    }

    @Transactional
    public void setHeadRT(Long wargaId, Long rtId) throws NotFoundException, ValidationException {
        // Cari entitas warga berdasarkan ID
        WargaModel warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new NotFoundException("Warga not found with id: " + wargaId));

        // Cari wilayah RT berdasarkan ID
        WilayahRTModel wilayahRT = wilayahRTRepository.findById(rtId)
                .orElseThrow(() -> new NotFoundException("Wilayah RT not found with id: " + rtId));

        // Periksa apakah warga berasal dari wilayah RT yang dimaksud
        if (!warga.getWilayahRT().equals(wilayahRT)) {
            throw new ValidationException("Warga bukan berasal dari wilayah RT " + wilayahRT.getNomorRt());
        }

        // Periksa apakah wilayah RT tersebut sudah memiliki kepala RT
        if (wilayahRT.getKepalaRt() != null) {
            throw new ValidationException("Kepala RT sebelumnya masih menjabat. Jika ingin mengganti kepala RT, harap dilepas dulu jabatannya.");
        }

        // Set kepala RT
        wilayahRT.setKepalaRt(warga);
        wilayahRTRepository.save(wilayahRT);

        // Set role warga menjadi kepala RT
        warga.setRole("rt");
        wargaRepository.save(warga);
    }

    public List<WargaModel> getAllWargaByRT(String username) {
        // Dapatkan kepala RT berdasarkan username (misalnya, email)
        WargaModel kepalaRT = wargaRepository.findByEmail(username);

        if (kepalaRT == null || !kepalaRT.getRole().equals("rt")) {
            throw new NotFoundException("Kepala RT not found for the given username");
        }

        // Dapatkan wilayah RT dari kepala RT
        WilayahRTModel wilayahRT = kepalaRT.getWilayahRT();
        if (wilayahRT == null) {
            throw new NotFoundException("Wilayah RT not found for the given kepala RT");
        }

        // Dapatkan semua warga berdasarkan wilayah RT
        return wargaRepository.findAllByWilayahRT(wilayahRT);
    }

    @Transactional
    public WargaModel updateWargaRoleWarga(Long id, WargaRequestRoleWargaDTO wargaDTO, String email) throws Exception {
        // Periksa apakah email sudah ada dalam basis data (kecuali untuk email warga yang sedang login)
        WargaModel existingWargaWithEmail = wargaRepository.findByEmail(wargaDTO.getEmail());
        if (existingWargaWithEmail != null && !existingWargaWithEmail.getEmail().equals(email)) {
            throw new ValidationException("Email already exists");
        }

        // Mendapatkan informasi warga yang sedang login
        WargaModel loggedInWarga = wargaRepository.findByEmail(email);
        if (loggedInWarga == null) {
            throw new NotFoundException("Logged in warga not found");
        }

        // Jika warga yang login adalah kepala RT, maka ambil wilayah RT-nya
        if ("rt".equals(loggedInWarga.getRole())) {
            WilayahRTModel wilayahRT = loggedInWarga.getWilayahRT();
            if (wilayahRT == null) {
                throw new NotFoundException("Wilayah RT not found for the logged in RT");
            }

            // Mengupdate data warga
            WargaModel wargaToUpdate = wargaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Warga not found"));
            // Memastikan bahwa data warga yang ingin diupdate sesuai dengan wilayah RT dari warga yang login
            if (!wargaToUpdate.getWilayahRT().equals(wilayahRT)) {
                throw new ValidationException("Warga does not belong to the same RT as the logged in warga");
            }

            // Mengisi data warga dari DTO
            wargaToUpdate.setTempat_lahir(wargaDTO.getTempat_lahir());
            wargaToUpdate.setTanggal_lahir(wargaDTO.getTanggal_lahir());
            wargaToUpdate.setJenis_kelamin(wargaDTO.getJenis_kelamin());
            wargaToUpdate.setAgama(wargaDTO.getAgama());
            wargaToUpdate.setNo_kk(wargaDTO.getNo_kk());
            wargaToUpdate.setStatus_dalam_keluarga(wargaDTO.getStatus_dalam_keluarga());
            wargaToUpdate.setStatus_kependudukan(wargaDTO.getStatus_kependudukan());
            wargaToUpdate.setNo_anak(wargaDTO.getNo_anak());
            wargaToUpdate.setPanjang_lahir(wargaDTO.getPanjang_lahir());
            wargaToUpdate.setBerat_lahir(wargaDTO.getBerat_lahir());
            wargaToUpdate.setNama(wargaDTO.getNama());
            wargaToUpdate.setNama_ayah(wargaDTO.getNama_ayah());
            wargaToUpdate.setNama_ibu(wargaDTO.getNama_ibu());
            wargaToUpdate.setNo_telp(wargaDTO.getNo_telp());
            wargaToUpdate.setAlamat(wargaDTO.getAlamat());
            wargaToUpdate.setTanggal_perkawinan(wargaDTO.getTanggal_perkawinan());
            wargaToUpdate.setAlamat_sebelumnya(wargaDTO.getAlamat_sebelumnya());
            wargaToUpdate.setNo_bpjs(wargaDTO.getNo_bpjs());
            wargaToUpdate.setPendidikan_tempuh(wargaDTO.getPendidikan_tempuh());
            wargaToUpdate.setPendidikan_terakhir(wargaDTO.getPendidikan_terakhir());
            wargaToUpdate.setStatus_perkawinan(wargaDTO.getStatus_perkawinan());
            wargaToUpdate.setGolongan_darah(wargaDTO.getGolongan_darah());
            wargaToUpdate.setJenis_asuransi(wargaDTO.getJenis_asuransi());
            wargaToUpdate.setJenis_kb(wargaDTO.getJenis_kb());
            wargaToUpdate.setKesesuaian_tempat(wargaDTO.getKesesuaian_tempat());
            wargaToUpdate.setSumber_air(wargaDTO.getSumber_air());
            wargaToUpdate.setEmail(wargaDTO.getEmail());
            wargaToUpdate.setRole("warga");

            // Simpan data warga yang telah diupdate
            return wargaRepository.save(wargaToUpdate);
        } else {
            throw new ValidationException("The logged in warga is not a head RT");
        }
    }

    // Change Password
    @Transactional
    public WargaModel changePasswordRt(ChangePasswordRequestDTO requestDTO) throws Exception {
        // Mendapatkan informasi warga yang sedang login dari Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName(); // Mengambil email dari Authentication

        WargaModel loggedInWarga = wargaRepository.findByEmail(loggedInEmail);
        if (loggedInWarga == null) {
            throw new NotFoundException("Logged in warga not found");
        }

        // Memeriksa kecocokan password lama
        if (!bcryptEncoder.matches(requestDTO.getOldPassword(), loggedInWarga.getPassword())) {
            throw new ValidationException("Old password is incorrect");
        }

        // Memperbarui password dengan yang baru
        String newPasswordEncoded = bcryptEncoder.encode(requestDTO.getNewPassword());
        loggedInWarga.setPassword(newPasswordEncoded);

        // Simpan perubahan
        return wargaRepository.save(loggedInWarga);
    }

    @Transactional
    public WargaModel changePasswordWarga(ChangePasswordRequestDTO requestDTO) throws Exception {
        // Mendapatkan informasi warga yang sedang login dari Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName(); // Mengambil email dari Authentication

        WargaModel loggedInWarga = wargaRepository.findByEmail(loggedInEmail);
        if (loggedInWarga == null) {
            throw new NotFoundException("Logged in warga not found");
        }

        // Memeriksa kecocokan password lama
        if (!bcryptEncoder.matches(requestDTO.getOldPassword(), loggedInWarga.getPassword())) {
            throw new ValidationException("Old password is incorrect");
        }

        // Memperbarui password dengan yang baru
        String newPasswordEncoded = bcryptEncoder.encode(requestDTO.getNewPassword());
        loggedInWarga.setPassword(newPasswordEncoded);

        // Simpan perubahan
        return wargaRepository.save(loggedInWarga);
    }

    // Get Profile
    public WargaModel getProfile(String loggedInEmail) throws NotFoundException {
        // Mencari pengguna berdasarkan email yang sedang login
        WargaModel loggedInWarga = wargaRepository.findByEmail(loggedInEmail);
        if (loggedInWarga == null) {
            throw new NotFoundException("Logged in warga not found");
        }
        return loggedInWarga;
    }

    // update profile
    public WargaModel updateProfile(WargaUpdateRequestDTO requestDTO) {
        // Mendapatkan informasi autentikasi pengguna yang sedang login
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Email pengguna yang sedang login

        // Menemukan pengguna berdasarkan email
        WargaModel existingWarga = wargaRepository.findByEmail(email);
        if (existingWarga == null) {
            throw new NotFoundException("User not found with email: " + email);
        }

        // Memperbarui profil pengguna
        existingWarga.setNama(requestDTO.getNama());
        existingWarga.setNo_telp(requestDTO.getNoTelp());
        existingWarga.setEmail(requestDTO.getEmail());
        existingWarga.setAlamat(requestDTO.getAlamat());

        return wargaRepository.save(existingWarga);
    }
}
