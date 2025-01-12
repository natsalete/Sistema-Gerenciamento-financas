package com.sd.prj_mvc_model.objetos;

import java.math.BigDecimal;

public class ItemOrcamento {
    private int id;
    private String descricao;
    private int quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private int orcamentoId;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        atualizarValorTotal(); // Atualizando valor total sempre que quantidade for alterada
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
        atualizarValorTotal(); // Atualizando valor total sempre que valor unitário for alterado
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    // Método para calcular e atualizar o valor total
    private void atualizarValorTotal() {
        this.valorTotal = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
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
        return "ItemOrcamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                ", valorTotal=" + valorTotal +
                ", orcamentoId=" + orcamentoId +
                '}';
    }
    
}
