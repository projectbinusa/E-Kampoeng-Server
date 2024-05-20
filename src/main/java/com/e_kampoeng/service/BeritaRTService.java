package com.e_kampoeng.service;

import com.e_kampoeng.dto.BeritaDTO;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.model.Berita;
import com.e_kampoeng.model.Tags;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.BeritaRepository;
import com.e_kampoeng.repository.CategoryBeritaRepository;
import com.e_kampoeng.repository.TagsRepository;
import com.e_kampoeng.repository.WargaRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BeritaRTService {

    @Autowired
    private BeritaRepository beritaRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private CategoryBeritaRepository categoryBeritaRepository;

    @Autowired
    private WargaRepository wargaRepository;

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/bawaslu-a6bd2.appspot.com/o/%s?alt=media";

    private Long getWilayahRTIdByEmail(String email) {
        WargaModel warga = wargaRepository.findByEmail(email);
        if (warga == null || warga.getWilayahRT() == null) {
            throw new NotFoundException("User not found or does not belong to any WilayahRT");
        }
        return warga.getWilayahRT().getId();
    }

    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Berita save(BeritaDTO beritaDTO, MultipartFile multipartFile) throws Exception {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);

        Berita newBerita = new Berita();
        String image = imageConverter(multipartFile);
        newBerita.setAuthor(beritaDTO.getAuthor());
        newBerita.setJudulBerita(beritaDTO.getJudulBerita());
        newBerita.setIsiBerita(beritaDTO.getIsiBerita());
        newBerita.setImage(image);
        newBerita.setCategoryBerita(categoryBeritaRepository.findById(beritaDTO.getCategoryId()));
        newBerita.setWilayahRT(wargaRepository.findByEmail(email).getWilayahRT());

        return beritaRepository.save(newBerita);
    }

    public Optional<Berita> findById(Long id) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return beritaRepository.findByIdAndWilayahRTId(id, wilayahRTId);
    }

    public Page<Berita> findAllWithPagination(Pageable pageable) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return beritaRepository.findAllByWilayahRTId(wilayahRTId, pageable);
    }

    @Transactional
    public void delete(Long id) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        Berita berita = beritaRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElseThrow(() -> new NotFoundException("Berita not found"));

        if (berita != null) {
            berita.getTagsBerita().clear();
            berita.setCategoryBerita(null);

            beritaRepository.delete(berita);
        }
    }

    public Berita update(Long id, BeritaDTO beritaDTO, MultipartFile multipartFile) throws Exception {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        Berita berita = beritaRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElseThrow(() -> new NotFoundException("Berita not found"));
        String image = imageConverter(multipartFile);
        berita.setJudulBerita(beritaDTO.getJudulBerita());
        berita.setIsiBerita(beritaDTO.getIsiBerita());
        berita.setAuthor(beritaDTO.getAuthor());
        berita.setImage(image);
        berita.setCategoryBerita(categoryBeritaRepository.findById(beritaDTO.getCategoryId()));
        return beritaRepository.save(berita);
    }

    public List<Berita> beritaTerbaru() {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return beritaRepository.findFirst5ByWilayahRTIdOrderByUpdatedDateDesc(wilayahRTId);
    }

    public List<Berita> searchBerita(String judul) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return beritaRepository.searchByJudulBeritaAndWilayahRTId(judul, wilayahRTId);
    }

    public Berita getBeritaById(Long id) throws Exception {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        Berita berita = beritaRepository.findByIdAndWilayahRTId(id, wilayahRTId).orElseThrow(() -> new Exception("Berita not found"));
        return berita;
    }

    public List<Berita> arsip(String bulan) {
        return beritaRepository.find(bulan);
    }

    public Berita tagsInBerita(Long beritaId, Long tagsId) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        Berita berita = beritaRepository.findByIdAndWilayahRTId(beritaId, wilayahRTId).orElseThrow(() -> new NotFoundException("Berita not found"));
        Tags tags = tagsRepository.findById(tagsId).orElseThrow(() -> new NotFoundException("ID Tag Not Found"));

        Set<Tags> tagsSet = berita.getTagsBerita();
        tagsSet.add(tags);
        berita.setTagsBerita(tagsSet);
        return beritaRepository.save(berita);
    }

    public List<Berita> getByTags(Long tagsId) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return beritaRepository.getAllByTagsAndWilayahRTId(tagsId, wilayahRTId);
    }

    public Page<Berita> getByCategory(Long categoryId, Pageable pageable) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return beritaRepository.findByCategoryBerita_IdAndWilayahRTId(categoryId, wilayahRTId, pageable);
    }

    public List<Berita> relatedPosts(Long idBerita) throws Exception {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        String berita = beritaRepository.getByIdBeritaAndWilayahRTId(idBerita, wilayahRTId);
        return beritaRepository.relatedPost(berita);
    }

    public List<Berita> terbaruByCategory(Long categoryId) {
        String email = getAuthenticatedUserEmail();
        Long wilayahRTId = getWilayahRTIdByEmail(email);
        return beritaRepository.terbaruByCategoryAndWilayahRTId(categoryId, wilayahRTId);
    }

    private String imageConverter(MultipartFile multipartFile) throws Exception {
        try {
            String fileName = getExtension(multipartFile.getOriginalFilename());
            File file = convertFile(multipartFile, fileName);
            var RESPONSE_URL = uploadFile(file, fileName);
            file.delete();
            return RESPONSE_URL;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error upload file: " + e.getMessage());
        }
    }

    private String getExtension(String fileName) {
        return fileName.split("\\.")[0];
    }

    private File convertFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        System.out.println("File size: " + file.length());
        return file;
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("storage-e-kampoeng.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("e-kampoeng-firebase.json");
        Credentials credentials = GoogleCredentials.fromStream(serviceAccount);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
}
