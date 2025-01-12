package com.sd.prj_mvc_model.objetos;

import java.math.BigDecimal;

public class Orcamentos {
    private int id;
    private String descricao;
    private BigDecimal valorTotal;
    private String dataInicio;
    private String dataFim;
    private String status;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

        public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        // Validar se o status é válido
        if (status.equals("Em Andamento") || status.equals("Concluído") || status.equals("Cancelado")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status inválido: " + status);
        }
    }

    // toString para depuração
    @Override
    public String toString() {
        return "Orcamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valorTotal=" + valorTotal +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", status='" + status + '\'' +
                '}';
    }
}
