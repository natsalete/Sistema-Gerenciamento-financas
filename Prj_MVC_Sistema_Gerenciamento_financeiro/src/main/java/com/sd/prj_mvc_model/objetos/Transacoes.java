package com.sd.prj_mvc_model.objetos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacoes {
    private int id;
    private String tipo;
    private BigDecimal valor;
    private LocalDate data;
    private String descricao;
    private int orcamentoId;

    
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if ("entrada".equals(tipo) || "saída".equals(tipo)) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getOrcamentoId() {
        return orcamentoId;
    }

    public void setOrcamentoId(int orcamentoId) {
        this.orcamentoId = orcamentoId;
    }

    // toString para depuração
    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", orcamentoId=" + orcamentoId +
                '}';
    }
    
}
