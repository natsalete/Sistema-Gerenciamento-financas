package com.sd.prj_mvc_model.objetos;

import java.math.BigDecimal;

public class Pagamentos {
    private int id;
    private BigDecimal valor;
    private String data;
    private String descricao;
    private Fornecedores fornecedor_id;
 
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedores getFornecedor_id() {
        return fornecedor_id;
    }

    public void setFornecedor_id(Fornecedores fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }
    
    // toString para depuração
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", valor='" + valor + '\'' +
                ", data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                ", fornecedor_id='" + descricao + '\'' +
                '}';
    }
}
