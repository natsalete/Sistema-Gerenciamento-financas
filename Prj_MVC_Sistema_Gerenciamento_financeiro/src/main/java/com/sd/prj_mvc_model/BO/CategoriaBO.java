package com.sd.prj_mvc_model.BO;

import com.sd.prj_mvc_model.DAO.CategoriaDao;
import com.sd.prj_mvc_model.objetos.Categorias;
import java.util.List;

public class CategoriaBO {
    CategoriaDao cDAO;
    
    public CategoriaBO() {
        cDAO = new CategoriaDao();
    } 
    
    public Categorias Salvar(Categorias c)
    {
        return cDAO.Salvar(c);
    }
    
    public void Editar(Categorias c)
    {
        cDAO.Editar(c);
    }
    
    public int Excluir(Categorias c)
    {
        return cDAO.Excluir(c);
    }
    
    public List<Categorias> getCategorias()
    {
        return cDAO.getCategorias();
    }
    
    public Categorias getCategoria(int id)
    {
        return cDAO.getCategorias(id);
    }
    
    public List<Categorias> getCategorias(String nome)
    {
        return cDAO.getCategorias(nome);
    }
}
