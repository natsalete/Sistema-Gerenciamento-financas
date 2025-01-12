package com.sd.prj_mvc_controller;

import com.sd.prj_mvc_model.BO.DespesasBO;
import com.sd.prj_mvc_model.objetos.Categorias;
import com.sd.prj_mvc_model.objetos.Despesas;
import com.sd.prj_mvc_model.objetos.Orcamentos;
import com.sd.prj_mvc_view.FormCadastroDespesas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastroDespesas {
    private final DespesasBO despesasBO;
    private List<Despesas> lstDespesas;
    private List<Categorias> lstCategorias; 
    private List<Orcamentos> lstOrcamentos; 
    
    public ControllerCadastroDespesas() {
        despesasBO = new DespesasBO();
        lstDespesas = new ArrayList<>();
        lstCategorias = new ArrayList<>(); 
        lstOrcamentos = new ArrayList<>();  
    }
   
    
    public void preencherCombo(FormCadastroDespesas view) {
        String descricao = view.getTxtConsDescricao();
        if (!descricao.isEmpty()) {
            lstDespesas = despesasBO.getDespesasDescr(descricao);
            view.clearCmbDespesas();
            lstDespesas.forEach(despesas -> {
                view.setCmbDespesas(String.format("%d | %s | %.2f", 
                    despesas.getId(), 
                    despesas.getDescricao(), 
                    despesas.getValor()));
            });
        } else {
            view.clearCmbDespesas();
        }
    }
    
    public void preencherComboCategorias(FormCadastroDespesas view) {
        String nome = view.getTxtNomeCategorias();
        if (!nome.isEmpty()) {
            lstCategorias = despesasBO.getDespesas(nome);
            view.clearCmbCategorias();
            lstDespesas.forEach(categoria -> {
                view.setCmbCategorias(String.format("%d | %s", 
                    categoria.getCategorias().getId(), 
                    categoria.getCategorias().getNome()));
            });
        } else {
            view.clearCmbCategorias();
        }
    }
    
    public void preencherComboOrcamentos(FormCadastroDespesas view) {
        String descricao = view.getTxtDescricaoOrcamentos();
        if (!descricao.isEmpty()) {
            lstOrcamentos = despesasBO.getDespesas1(descricao);
            view.clearCmbOrcamentos();
            lstDespesas.forEach(orcamento -> {
                view.setCmbOrcamentos(String.format("%d | %s | %.2f | %s", 
                    orcamento.getOrcamentos().getId(), 
                    orcamento.getOrcamentos().getDescricao(), 
                    orcamento.getOrcamentos().getValorTotal(),
                    orcamento.getOrcamentos().getStatus()));
            });
        } else {
            view.clearCmbOrcamentos();
        }
    }
    
    public void preencherCombo(int id, FormCadastroDespesas view) {
        Despesas despesa = despesasBO.getDespesas(id);
        if (despesa != null) {
            view.clearCmbDespesas();
            view.setCmbDespesas(String.format("%d | %s | %.2f | %s | %d | %d",
                despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                despesa.getData(),
                despesa.getCategorias().getId(),
                despesa.getOrcamentos().getId()));
            preencherCampos(despesa, view);
        }
    }
    
    private void preencherCampos(Despesas despesa, FormCadastroDespesas view) {
        if (despesa == null) return;

        // Limpar campos antes de preencher
        view.clearCmbCategorias();
        view.clearCmbOrcamentos();

        // Preencher campos básicos
        view.setTxtCodigo(String.valueOf(despesa.getId()));
        view.setTxtDescricao(despesa.getDescricao());
        view.setTxtValor(String.valueOf(despesa.getValor()));
        view.setTxtData(despesa.getData());

        // Preencher categoria com verificação null
        Categorias categoria = despesa.getCategorias();
        if (categoria != null) {
            view.setCmbCategorias(categoria.getNome() + " | " + categoria.getId());
        }

        // Preencher orçamento com verificação null
        Orcamentos orcamento = despesa.getOrcamentos();
        if (orcamento != null) {
            view.setCmbOrcamentos(orcamento.getId() + " - " + despesa.getDescricao() + 
                                " | " + orcamento.getValorTotal() + " | " + 
                                orcamento.getStatus());
        }

        view.setBtnSalvarEnabled(false);
    }
    
    public void preencherCampos(FormCadastroDespesas view) {
        if (!lstDespesas.isEmpty()) {
            int index = view.getCmbDespesasSelectedIndex();
            if (index >= 0) {
                Despesas despesa = lstDespesas.get(index);
                preencherCampos(despesa, view);
            }
        }
    }
    
    public void novo(FormCadastroDespesas view) {
        view.clearCmbDespesas();
        view.setTxtCodigo("");
        view.setTxtDescricao("");
        view.setTxtValor("");
        view.setTxtData("");
        view.clearCmbCategorias(); 
        view.clearCmbOrcamentos();
        view.setBtnSalvarEnabled(true);
        lstDespesas = new ArrayList<>();
    }
    
    public boolean salvar(FormCadastroDespesas view) {
        try {
            Despesas despesa = new Despesas();
            despesa.setDescricao(view.getTxtDescricao());
            despesa.setValor(new BigDecimal(view.getTxtValor().replace(",", ".")));
            despesa.setData(view.getTxtData());
            
            // Corrigir para usar os valores dos ComboBoxes corretamente
            Categorias categoria = lstCategorias.get(view.getCmbCategorias());
            Orcamentos orcamento = lstOrcamentos.get(view.getCmbOrcamentos());
            
            despesa.setCategoria(categoria);
            despesa.setOrcamento(orcamento); 
            
            despesa = despesasBO.Salvar(despesa);
            if (despesa.getId() != -1) {
                view.setTxtCodigo(String.valueOf(despesa.getId()));
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void editar(FormCadastroDespesas view) {
        try {
            Despesas despesa = new Despesas(); // Corrigir tipo
            despesa.setId(Integer.parseInt(view.getTxtCodigo()));
            despesa.setDescricao(view.getTxtDescricao());
            despesa.setValor(new BigDecimal(view.getTxtValor().replace(",", ".")));
            despesa.setData(view.getTxtData());
            
            // Corrigir para usar os valores dos ComboBoxes corretamente
            Categorias categoria = lstCategorias.get(view.getCmbCategorias());
            Orcamentos orcamento = lstOrcamentos.get(view.getCmbOrcamentos());
            
            despesa.setCategoria(categoria);
            despesa.setOrcamento(orcamento);
            
            despesasBO.Editar(despesa);
        } catch (NumberFormatException e) {
            // Handle the error appropriately
        }
    }
    
    public boolean excluir(FormCadastroDespesas view) {
        try {
            Despesas despesa = new Despesas();
            despesa.setId(Integer.parseInt(view.getTxtCodigo()));
            return despesasBO.Excluir(despesa) == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
