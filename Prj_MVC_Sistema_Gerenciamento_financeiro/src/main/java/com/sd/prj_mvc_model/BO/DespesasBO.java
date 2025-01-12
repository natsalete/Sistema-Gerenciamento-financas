package com.sd.prj_mvc_model.BO;

import com.sd.prj_mvc_model.DAO.DespesasDao;
import com.sd.prj_mvc_model.objetos.Categorias;
import com.sd.prj_mvc_model.objetos.Despesas;
import com.sd.prj_mvc_model.objetos.Orcamentos;
import java.util.List;

public class DespesasBO {
    
    DespesasDao dDAO;
    
    public DespesasBO() {
        dDAO = new DespesasDao();
    } 
       
    public Despesas Salvar(Despesas d)
    {
        return dDAO.Salvar(d);
    }
    
    public void Editar(Despesas d)
    {
        dDAO.Editar(d);
    }
    
    public int Excluir(Despesas d)
    {
        return dDAO.Excluir(d);
    }
    
    public List<Despesas> getDespesas()
    {
        return dDAO.getDespesas();
    }
    
    public Despesas getDespesas(int id)
    {
        return dDAO.getDespesas(id);
    }
    
     public List<Despesas> getDespesasDescr(String descricao)
    {
        return dDAO.getDespesasDescr(descricao);
    }
    
    
    public List<Categorias> getDespesas(String nome)
    {
        return dDAO.getDespesas(nome);
    }
    
    public List<Orcamentos> getDespesas1(String descricao)
    {
        return dDAO.getDespesas1(descricao);
    }
}
