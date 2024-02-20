package com.e_kampoeng.response;

import com.e_kampoeng.dto.WargaDTO;
import com.e_kampoeng.model.WargaModel;

public class RTResponseDTO {
    private Long id;
    private Long nomorRT;
    private WargaModel warga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomorRT() {
        return nomorRT;
    }

    public void setNomorRT(Long nomorRT) {
        this.nomorRT = nomorRT;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }
}
