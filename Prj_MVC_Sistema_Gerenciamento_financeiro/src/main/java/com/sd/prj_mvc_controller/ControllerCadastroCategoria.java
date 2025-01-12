package com.sd.prj_mvc_controller;

import com.sd.prj_mvc_model.BO.CategoriaBO;
import com.sd.prj_mvc_model.objetos.Categorias;
import com.sd.prj_mvc_view.FormCadastroCategoria;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastroCategoria {
    private final CategoriaBO categoriaBO;
    private List<Categorias> lstCategorias;

    public ControllerCadastroCategoria() {
        categoriaBO = new CategoriaBO();
        lstCategorias = new ArrayList<>();
    }

    public void preencherCombo(FormCadastroCategoria view) {
        String nome = view.getTxtConsNome();
        if (!nome.isEmpty()) {
            lstCategorias = categoriaBO.getCategorias(nome);
            view.clearCmbCategorias();
            lstCategorias.forEach(itemCategoria -> {
                view.setCmbCategorias(itemCategoria.getNome() + " | " + itemCategoria.getId());
            });
        } else {
            view.clearCmbCategorias();
        }
    }

    public void preencherCombo(int id, FormCadastroCategoria view) {
        Categorias c = categoriaBO.getCategoria(id);
        view.clearCmbCategorias(); // Limpa o combo antes de adicionar
        view.setCmbCategorias(c.getNome() + " | " + c.getId());
        preencherCampos(c, view);
    }

    private void preencherCampos(Categorias categoria, FormCadastroCategoria view) {
        view.setTxtCodigo(String.valueOf(categoria.getId()));
        view.setTxtNome(categoria.getNome());
        view.setBtnSalvarEnabled(false);
    }

    public void preencherCampos(FormCadastroCategoria view) {
        if (!lstCategorias.isEmpty()) {
            int index = view.getCmbCategoriasSelectedIndex();
            if (index >= 0) { 
                Categorias categoria = lstCategorias.get(index);
                preencherCampos(categoria, view);
            }
        }
    }

    public void novo(FormCadastroCategoria view) {
        // Limpa todos os campos
        view.setTxtConsNome("");
        view.clearCmbCategorias();
        view.setTxtCodigo("");
        view.setTxtNome("");
        view.setBtnSalvarEnabled(true);
        
        // Reinicia a lista de categorias
        lstCategorias = new ArrayList<>();
    }

    public boolean salvar(FormCadastroCategoria view) {
        Categorias categoria = new Categorias();
        categoria.setNome(view.getTxtNome());
        categoriaBO.Salvar(categoria);
        int codigo = categoria.getId();
        if (codigo != -1) {
            view.setTxtCodigo(String.valueOf(categoria.getId()));
            return true;
        }
        return false;
    }

    public void editar(FormCadastroCategoria view) {
        Categorias categoria = new Categorias();
        categoria.setId(Integer.parseInt(view.getTxtCodigo()));
        categoria.setNome(view.getTxtNome());
        categoriaBO.Editar(categoria);
    }

    public boolean excluir(FormCadastroCategoria view) {
        Categorias categoria = new Categorias();
        categoria.setId(Integer.parseInt(view.getTxtCodigo()));
        return categoriaBO.Excluir(categoria) == 1;
    }
    
}
