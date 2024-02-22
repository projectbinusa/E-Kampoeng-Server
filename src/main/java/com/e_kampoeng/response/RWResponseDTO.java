package com.e_kampoeng.response;

import com.e_kampoeng.model.WargaModel;

public class RWResponseDTO {
    private Long id;
    private Long nomorRW;
    private WargaModel warga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomorRW() {
        return nomorRW;
    }

    public void setNomorRW(Long nomorRW) {
        this.nomorRW = nomorRW;
    }

    public WargaModel getWarga() {
        return warga;
    }

    public void setWarga(WargaModel warga) {
        this.warga = warga;
    }
}
