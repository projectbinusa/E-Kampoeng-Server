package com.e_kampoeng.response;

public class WilayahRTResponseDTO {

    private Long id;
    private Long nomorRt;
    private Long kepalaRtId;
    private String kepalaRtNama;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomorRt() {
        return nomorRt;
    }

    public void setNomorRt(Long nomorRt) {
        this.nomorRt = nomorRt;
    }

    public Long getKepalaRtId() {
        return kepalaRtId;
    }

    public void setKepalaRtId(Long kepalaRtId) {
        this.kepalaRtId = kepalaRtId;
    }

    public String getKepalaRtNama() {
        return kepalaRtNama;
    }

    public void setKepalaRtNama(String kepalaRtNama) {
        this.kepalaRtNama = kepalaRtNama;
    }
}
