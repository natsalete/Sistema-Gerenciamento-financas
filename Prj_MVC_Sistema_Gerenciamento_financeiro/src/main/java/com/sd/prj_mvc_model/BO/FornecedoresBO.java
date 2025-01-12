package com.sd.prj_mvc_model.BO;

import com.sd.prj_mvc_model.DAO.FornecedoresDao;
import com.sd.prj_mvc_model.objetos.Fornecedores;
import java.util.List;

public class FornecedoresBO {
    FornecedoresDao fDAO;
    
    public FornecedoresBO() {
        fDAO = new FornecedoresDao();
    } 
    
    public Fornecedores Salvar(Fornecedores f)
    {
        return fDAO.Salvar(f);
    }
    
    public void Editar(Fornecedores f)
    {
        fDAO.Editar(f);
    }
    
    public int Excluir(Fornecedores f)
    {
        return fDAO.Excluir(f);
    }
    
    public List<Fornecedores> getFornecedores()
    {
        return fDAO.getFornecedores();
    }
    
    public Fornecedores getFornecedores(int id)
    {
        return fDAO.getFornecedores(id);
    }
    
    public List<Fornecedores> getFornecedores(String nome)
    {
        return fDAO.getFornecedores(nome);
    }
}
