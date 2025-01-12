package com.sd.prj_mvc_controller;

import com.sd.prj_mvc_model.BO.FornecedoresBO;
import com.sd.prj_mvc_model.objetos.Fornecedores;
import com.sd.prj_mvc_view.FormCadastroFornecedores;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastroFornecedores {
    private final FornecedoresBO fornecedoresBO;
    private List<Fornecedores> lstFornecedores;

    public ControllerCadastroFornecedores() {
        fornecedoresBO = new FornecedoresBO();
        lstFornecedores = new ArrayList<>();
    }

    public void preencherCombo(FormCadastroFornecedores view) {
        String nome = view.getTxtConsNome();
        if (!nome.isEmpty()) {
            lstFornecedores = fornecedoresBO.getFornecedores(nome);
            view.clearCmbFornecedores();
            lstFornecedores.forEach(itemFornecedores -> {
                view.setCmbFornecedores(itemFornecedores.getNome()+ " | " + itemFornecedores.getId());
            });
        } else {
            view.clearCmbFornecedores();
        }
    }

    public void preencherCombo(int id, FormCadastroFornecedores view) {
        Fornecedores f = fornecedoresBO.getFornecedores(id);
        view.clearCmbFornecedores(); 
        view.setCmbFornecedores(f.getNome() + " | " + f.getEmail() + " | " + f.getTelefone() + " | " + f.getId());
        preencherCampos(f, view);
    }

    private void preencherCampos(Fornecedores fornecedores, FormCadastroFornecedores view) {
        view.setTxtCodigo(String.valueOf(fornecedores.getId()));
        view.setTxtNome(fornecedores.getNome());
        view.setTxtTelefone(fornecedores.getTelefone());
        view.setTxtEmail(fornecedores.getEmail());
        view.setBtnSalvarEnabled(false);
    }

    public void preencherCampos(FormCadastroFornecedores view) {
        if (!lstFornecedores.isEmpty()) {
            int index = view.getCmbFornecedoresSelectedIndex();
            if (index >= 0) { 
                Fornecedores fornecedores = lstFornecedores.get(index);
                preencherCampos(fornecedores, view);
            }
        }
    }

    public void novo(FormCadastroFornecedores view) {
        // Limpa todos os campos
        view.setTxtConsNome("");
        view.clearCmbFornecedores();
        view.setTxtCodigo("");
        view.setTxtNome("");
        view.setTxtTelefone("");
        view.setTxtEmail("");
        view.setBtnSalvarEnabled(true);
        
        // Reinicia a lista de categorias
        lstFornecedores = new ArrayList<>();
    }

    public boolean salvar(FormCadastroFornecedores view) {
        Fornecedores fornecedores = new Fornecedores();
        fornecedores.setNome(view.getTxtNome());
        fornecedoresBO.Salvar(fornecedores);
        int codigo = fornecedores.getId();
        if (codigo != -1) {
            view.setTxtCodigo(String.valueOf(fornecedores.getId()));
            return true;
        }
        return false;
    }

    public void editar(FormCadastroFornecedores view) {
        Fornecedores fornecedores = new Fornecedores();
        fornecedores.setId(Integer.parseInt(view.getTxtCodigo()));
        fornecedores.setNome(view.getTxtNome());
        fornecedoresBO.Editar(fornecedores);
    }

    public boolean excluir(FormCadastroFornecedores view) {
        Fornecedores fornecedores = new Fornecedores();
        fornecedores.setId(Integer.parseInt(view.getTxtCodigo()));
        return fornecedoresBO.Excluir(fornecedores) == 1;
    }
}
