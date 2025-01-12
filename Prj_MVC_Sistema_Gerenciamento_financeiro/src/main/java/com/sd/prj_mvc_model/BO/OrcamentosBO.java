package com.sd.prj_mvc_model.BO;

import com.sd.prj_mvc_model.DAO.OrcamentosDao;
import com.sd.prj_mvc_model.objetos.Orcamentos;
import java.util.List;

public class OrcamentosBO {
    OrcamentosDao oDAO;
    
    public OrcamentosBO() {
        oDAO = new OrcamentosDao();
    } 
    
    public Orcamentos Salvar(Orcamentos o)
    {
        return oDAO.Salvar(o);
    }
    
    public void Editar(Orcamentos o)
    {
        oDAO.Editar(o);
    }
    
    public int Excluir(Orcamentos c)
    {
        return oDAO.Excluir(c);
    }
    
    public List<Orcamentos> getOrcamentos()
    {
        return oDAO.getOrcamentos();
    }
    
    public Orcamentos getOrcamentos(int id)
    {
        return oDAO.getOrcamento(id);
    }
    
    public List<Orcamentos> getOrcamentosPorStatus(String status)
    {
        return oDAO.getOrcamentosPorStatus(status);
    }
    
}
