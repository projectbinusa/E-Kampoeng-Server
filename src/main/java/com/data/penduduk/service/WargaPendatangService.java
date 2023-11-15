package com.data.penduduk.service;

import com.data.penduduk.model.WargaPendatang;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface WargaPendatangService {

   WargaPendatang add (WargaPendatang wargaPendatang);

   WargaPendatang edit (WargaPendatang wargaPendatang, Long id);

   WargaPendatang get(Long id);

   List<WargaPendatang> getAll();

   Map<String, Boolean> delete(Long id);

}
