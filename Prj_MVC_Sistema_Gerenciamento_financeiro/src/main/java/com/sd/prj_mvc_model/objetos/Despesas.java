package com.sd.prj_mvc_model.objetos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesas {
     private int id;
    private String descricao;
    private BigDecimal valor;
    private String data;
    private Categorias categoriaId;
    private Orcamentos orcamentoId;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public Categorias getCategorias() {
        return categoriaId;
    }

    public void setCategoria(Categorias categorias) {
        this.categoriaId = categorias;
    }
    
    public Orcamentos getOrcamentos() {
        return orcamentoId;
    }

    public void setOrcamento(Orcamentos orcamento) {
        this.orcamentoId = orcamento;
    }


    // toString para depuração
    @Override
    public String toString() {
        return "Despesa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                ", categoriaId=" + categoriaId +
                ", orcamentoId=" + orcamentoId +
                '}';
    }
}
