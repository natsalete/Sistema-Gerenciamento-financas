package com.sd.prj_mvc_controller;

import com.sd.prj_mvc_model.BO.OrcamentosBO;
import com.sd.prj_mvc_model.objetos.Orcamentos;
import com.sd.prj_mvc_view.FormCadastroOrcamentos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ControllerCadastroOrcamentos {
    private final OrcamentosBO orcamentosBO;
    private List<Orcamentos> lstOrcamentos;
    
    public ControllerCadastroOrcamentos() {
        orcamentosBO = new OrcamentosBO();
        lstOrcamentos = new ArrayList<>();
    }
    
    public void preencherCombo(FormCadastroOrcamentos view) {
        String status = view.getTxtConsSatus();
        if (!status.isEmpty()) {
            lstOrcamentos = orcamentosBO.getOrcamentosPorStatus(status);
            view.clearCmbOrcamentos();
            lstOrcamentos.forEach(orcamento -> {
                view.setCmbOrcamentos(String.format("%d | %s | %.2f | %s", 
                    orcamento.getId(), 
                    orcamento.getDescricao(), 
                    orcamento.getValorTotal(),
                    orcamento.getStatus()));
            });
        } else {
            view.clearCmbOrcamentos();
        }
    }
    
    public void preencherCombo(int id, FormCadastroOrcamentos view) {
        Orcamentos orcamento = orcamentosBO.getOrcamentos(id);
        if (orcamento != null) {
            view.clearCmbOrcamentos();
            view.setCmbOrcamentos(String.format("%d | %s | %.2f | %s | %s | %s",
                orcamento.getId(),
                orcamento.getDescricao(),
                orcamento.getValorTotal(),
                orcamento.getDataInicio(),
                orcamento.getDataFim(),
                orcamento.getStatus()));
            preencherCampos(orcamento, view);
        }
    }
    
    private void preencherCampos(Orcamentos orcamento, FormCadastroOrcamentos view) {
        view.setTxtCodigo(String.valueOf(orcamento.getId()));
        view.setTxtDescricao(orcamento.getDescricao());
        view.setTxtValorTotal(String.valueOf(orcamento.getValorTotal()));
        view.setTxtDataInicial(orcamento.getDataInicio());
        view.setTxtDataFim(orcamento.getDataFim());
        view.setCmbStatus(orcamento.getStatus());
        view.setBtnSalvarEnabled(false);
    }
    
    public void preencherCampos(FormCadastroOrcamentos view) {
        if (!lstOrcamentos.isEmpty()) {
            int index = view.getCmbOrcamentosSelectedIndex();
            if (index >= 0) {
                Orcamentos orcamento = lstOrcamentos.get(index);
                preencherCampos(orcamento, view);
            }
        }
    }
    
    public void novo(FormCadastroOrcamentos view) {
        view.setTxtConsStatus("");
        view.clearCmbOrcamentos();
        view.setTxtCodigo("");
        view.setTxtDescricao("");
        view.setTxtValorTotal("");
        view.setTxtDataInicial("");
        view.setTxtDataFim("");
        view.setCmbStatus("Em Andamento");
        view.setBtnSalvarEnabled(true);
        lstOrcamentos = new ArrayList<>();
    }
    
    public boolean salvar(FormCadastroOrcamentos view) {
        try {
            Orcamentos orcamento = new Orcamentos();
            orcamento.setDescricao(view.getTxtDescricao());
            orcamento.setValorTotal(new BigDecimal(view.getTxtValorTotal().replace(",", ".")));
            orcamento.setDataInicio(view.getTxtDataInicial());
            orcamento.setDataFim(view.getTxtDataFim());
            orcamento.setStatus("Em Andamento"); // Status inicial padrão
            
            orcamento = orcamentosBO.Salvar(orcamento);
            if (orcamento.getId() != -1) {
                view.setTxtCodigo(String.valueOf(orcamento.getId()));
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void editar(FormCadastroOrcamentos view) {
        try {
            Orcamentos orcamento = new Orcamentos();
            orcamento.setId(Integer.parseInt(view.getTxtCodigo()));
            orcamento.setDescricao(view.getTxtDescricao());
            orcamento.setValorTotal(new BigDecimal(view.getTxtValorTotal().replace(",", ".")));
            orcamento.setDataInicio(view.getTxtDataInicial());
            orcamento.setDataFim(view.getTxtDataFim());
            orcamento.setStatus(view.getCmbStatus());
            
            orcamentosBO.Editar(orcamento);
        } catch (NumberFormatException e) {
            // Handle the error appropriately
        }
    }
    
    public boolean excluir(FormCadastroOrcamentos view) {
        try {
            Orcamentos orcamento = new Orcamentos();
            orcamento.setId(Integer.parseInt(view.getTxtCodigo()));
            return orcamentosBO.Excluir(orcamento) == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /*public boolean concluirOrcamento(FormCadastroOrcamentos view) {
        try {
            int id = Integer.parseInt(view.getTxtCodigo());
            return orcamentosBO.ConcluirOrcamento(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean cancelarOrcamento(FormCadastroOrcamentos view) {
        try {
            int id = Integer.parseInt(view.getTxtCodigo());
            return orcamentosBO.CancelarOrcamento(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }*/
    
}
