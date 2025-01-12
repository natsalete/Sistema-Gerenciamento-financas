package com.sd.prj_mvc_model.DAO;

import com.sd.prj_mvc_model.objetos.Fornecedores;
import com.sd.prj_mvc_model.objetos.Pagamentos;
import com.sd.prj_mvc_util.Conexao;
import com.sd.prj_mvc_util.ManipulaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PagamentosDao {
    Connection conn;
    ManipulaData md;
    FornecedoresDao fDAO;
    
    public PagamentosDao() {
        conn = new Conexao().conectar();
        md = new ManipulaData();
        FornecedoresDao fDAO;
    }
    
    public Pagamentos Salvar(Pagamentos p) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Pagamentos(valor, data, descricao, fornecedor_id) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            stmt.setBigDecimal(1, p.getValor());
            stmt.setDate(2, md.String2Date(p.getData()));
            stmt.setString(3, p.getDescricao());
            stmt.setInt(4, p.getFornecedor_id().getId()); // Status padrão conforme definido no banco
            
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1));
            } else {
                p.setId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public void Editar(Pagamentos p) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Pagamentos SET valor = ?, data = ?, descricao = ?, fornecedor_id = ? WHERE id = ?"
            );
            stmt.setBigDecimal(1, p.getValor());
            stmt.setDate(2, md.String2Date(p.getData()));
            stmt.setString(3, p.getDescricao());
            stmt.setInt(4, p.getFornecedor_id().getId()) ;
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int Excluir(Pagamentos p) {
        int verif = 0;
        try {
            // Só permite excluir orçamentos em andamento
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Pagamentos WHERE id = ?");
            stmt.setInt(1, p.getId());
            verif = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return verif;
    }
    
   
    public List<Pagamentos> getPagamentos() {
        List<Pagamentos> lstP = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Pagamentos");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lstP.add(getPagamentosFromResult(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstP;
    }

    public Pagamentos getPagamento(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Pagamentos WHERE id = ?",
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                return getPagamentosFromResult(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Pagamentos> getPagamentos(String descricao) {
        List<Pagamentos> lstP = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Pagamentos WHERE descricao ILIKE ?");
            ppStmt.setString(1, descricao + "%");
            rs = ppStmt.executeQuery();
            while (rs.next()) {
                lstP.add(getPagamentosFromResult(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstP;
    }
    
    public List<Fornecedores> getPagamentosFornecedores(String nome) {
        List<Fornecedores> lstFornecedores = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement(
                    "SELECT * FROM Fornecedores WHERE nome ILIKE ?");

            ppStmt.setString(1, nome + "%");
            rs = ppStmt.executeQuery();

            while (rs.next()) {
                Fornecedores fornecedore = new Fornecedores();
                fornecedore.setId(rs.getInt("id"));
                fornecedore.setNome(rs.getString("nome"));

                lstFornecedores.add(fornecedore);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstFornecedores;
    }

    private Pagamentos getPagamentosFromResult(ResultSet rs) throws SQLException {
        Pagamentos p = new Pagamentos();
        
        p.setId(rs.getInt("id"));
        p.setData(md.Date2String(rs.getString("data")));
        p.setValor(rs.getBigDecimal("valor"));
        p.setDescricao(rs.getString("descricao"));
        p.setFornecedor_id(fDAO.getFornecedores(rs.getInt("fornecedor_id")));
       
        return p;
    }
}
