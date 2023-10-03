package com.fbk.rating.domain;

public class ValutazioneProdotto {
    private Integer idProdotto;
    private Double votoMedio;

    public Integer getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Double getVotoMedio() {
        return votoMedio;
    }

    public void setVotoMedio(Double votoMedio) {
        this.votoMedio = votoMedio;
    }

    public ValutazioneProdotto(Integer idProdotto, Double votoMedio) {
        this.idProdotto = idProdotto;
        this.votoMedio = votoMedio;
    }

}
