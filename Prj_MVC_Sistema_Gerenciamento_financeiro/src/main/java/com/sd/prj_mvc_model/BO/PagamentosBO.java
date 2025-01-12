package com.sd.prj_mvc_model.BO;

import com.sd.prj_mvc_model.DAO.PagamentosDao;
import com.sd.prj_mvc_model.objetos.Fornecedores;
import com.sd.prj_mvc_model.objetos.Pagamentos;
import java.util.List;

public class PagamentosBO {
    PagamentosDao pDAO;
    
    public PagamentosBO() {
        pDAO = new PagamentosDao();
    } 
    
    public Pagamentos Salvar(Pagamentos p)
    {
        return pDAO.Salvar(p);
    }
    
    public void Editar(Pagamentos p)
    {
        pDAO.Editar(p);
    }
    
    public int Excluir(Pagamentos p)
    {
        return pDAO.Excluir(p);
    }
    
    public List<Pagamentos> getPagamentos()
    {
        return pDAO.getPagamentos();
    }
    
    public Pagamentos getPagamentos(int id)
    {
        return pDAO.getPagamento(id);
    }
    
    public List<Pagamentos> getPagamentos(String descricao)
    {
        return pDAO.getPagamentos(descricao);
    }
    
    public List<Fornecedores> getPagamentosFornecedores(String nome)
    {
        return pDAO.getPagamentosFornecedores(nome);
    }
}
