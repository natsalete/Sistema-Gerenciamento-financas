package com.sd.prj_mvc_controller;

import com.sd.prj_mvc_model.BO.PagamentosBO;
import com.sd.prj_mvc_model.objetos.Fornecedores;
import com.sd.prj_mvc_model.objetos.Pagamentos;
import com.sd.prj_mvc_view.FormCadastroPagamentos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastroPagamentos {
    private final PagamentosBO pagamentosBO;
    private List<Pagamentos> lstPagamentos;
    private List<Fornecedores> lstFornecedores; 
   
    public ControllerCadastroPagamentos() {
        pagamentosBO = new PagamentosBO();
        lstPagamentos = new ArrayList<>();
        lstFornecedores = new ArrayList<>(); 
    }
      
    public void preencherCombo(FormCadastroPagamentos view) {
        String descricao = view.getTxtConsDescricao();
        if (!descricao.isEmpty()) {
            lstPagamentos = pagamentosBO.getPagamentos(descricao);
            view.clearCmbPagamentos();
            lstPagamentos.forEach(pagamentos -> {
                view.setCmbPagamentos(String.format("%d | %s | %.2f", 
                    pagamentos.getId(), 
                    pagamentos.getDescricao(), 
                    pagamentos.getValor(),
                    pagamentos.getFornecedor_id().getId()));
            });
        } else {
            view.clearCmbPagamentos();
        }
    }
    
    public void preencherComboFornecedores(FormCadastroPagamentos view) {
        String nome = view.getTxtNomeFornecedores();
        if (!nome.isEmpty()) {
            lstFornecedores = pagamentosBO.getPagamentosFornecedores(nome);
            view.clearCmbFornecedores();
            lstFornecedores.forEach(fornecedor -> {
                view.setCmbFornecedores(String.format("%d | %s", 
                    fornecedor.getId(), 
                    fornecedor.getNome()));
            });
        } else {
            view.clearCmbFornecedores();
        }
    }
    
    
    public void preencherCombo(int id, FormCadastroPagamentos view) {
        Pagamentos pagamentos = pagamentosBO.getPagamentos(id);
        if (pagamentos != null) {
            view.clearCmbPagamentos();
            view.setCmbPagamentos(String.format("%d | %s | %.2f | %s | %d | %d",
                pagamentos.getId(),
                pagamentos.getDescricao(),
                pagamentos.getValor(),
                pagamentos.getData(),
                pagamentos.getFornecedor_id().getId()));
            preencherCampos(pagamentos, view);
        }
    }
    
    private void preencherCampos(Pagamentos pagamento, FormCadastroPagamentos view) {
        if (pagamento == null) return;

        // Limpar campos antes de preencher
        view.clearCmbFornecedores();
  
        // Preencher campos básicos
        view.setTxtCodigo(String.valueOf(pagamento.getId()));
        view.setTxtDescricao(pagamento.getDescricao());
        view.setTxtValor(String.valueOf(pagamento.getValor()));
        view.setTxtData(pagamento.getData());

        // Preencher categoria com verificação null
        Fornecedores fornecedores = pagamento.getFornecedor_id();
        if (fornecedores != null) {
            view.setCmbFornecedores(fornecedores.getNome() + " | " + fornecedores.getId());
        }

        view.setBtnSalvarEnabled(false);
    }
    
    public void preencherCampos(FormCadastroPagamentos view) {
        if (!lstPagamentos.isEmpty()) {
            int index = view.getCmbDespesasSelectedIndex();
            if (index >= 0) {
                Pagamentos pagamento = lstPagamentos.get(index);
                preencherCampos(pagamento, view);
            }
        }
    }
    
    public void novo(FormCadastroPagamentos view) {
        view.clearCmbPagamentos();
        view.setTxtCodigo("");
        view.setTxtDescricao("");
        view.setTxtValor("");
        view.setTxtData("");
        view.clearCmbFornecedores(); 
        view.setBtnSalvarEnabled(true);
        lstFornecedores = new ArrayList<>();
    }
    
    public boolean salvar(FormCadastroPagamentos view) {
        try {
            Pagamentos pagamentos = new Pagamentos();
            pagamentos.setDescricao(view.getTxtDescricao());
            pagamentos.setValor(new BigDecimal(view.getTxtValor().replace(",", ".")));
            pagamentos.setData(view.getTxtData());
            
            // Corrigir para usar os valores dos ComboBoxes corretamente
            Fornecedores fornecedor = lstFornecedores.get(view.getCmbFornecedores());
           
            pagamentos.setFornecedor_id(fornecedor);
            
 
            pagamentos = pagamentosBO.Salvar(pagamentos);
            if (pagamentos.getId() != -1) {
                view.setTxtCodigo(String.valueOf(pagamentos.getId()));
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void editar(FormCadastroPagamentos view) {
        try {
            Pagamentos pagamento = new Pagamentos(); // Corrigir tipo
            pagamento.setId(Integer.parseInt(view.getTxtCodigo()));
            pagamento.setDescricao(view.getTxtDescricao());
            pagamento.setValor(new BigDecimal(view.getTxtValor().replace(",", ".")));
            pagamento.setData(view.getTxtData());
            
            // Corrigir para usar os valores dos ComboBoxes corretamente
            Fornecedores fornecedor = lstFornecedores.get(view.getCmbFornecedores());
            
            pagamento.setFornecedor_id(fornecedor);
            
            pagamentosBO.Editar(pagamento);
        } catch (NumberFormatException e) {
            // Handle the error appropriately
        }
    }
    
    public boolean excluir(FormCadastroPagamentos view) {
        try {
            Pagamentos pagamento = new Pagamentos();
            pagamento.setId(Integer.parseInt(view.getTxtCodigo()));
            return pagamentosBO.Excluir(pagamento) == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
}
