
package com.sd.prj_mvc_model.DAO;

import com.sd.prj_mvc_model.objetos.Categorias;
import com.sd.prj_mvc_model.objetos.Despesas;
import com.sd.prj_mvc_model.objetos.Orcamentos;
import com.sd.prj_mvc_util.Conexao;
import com.sd.prj_mvc_util.ManipulaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DespesasDao {
    Connection conn;
    CategoriaDao cDAO;
    OrcamentosDao oDAO;
    ManipulaData md;

    public DespesasDao() {
        conn = new Conexao().conectar();
        cDAO = new CategoriaDao();
        oDAO = new OrcamentosDao();
        md = new ManipulaData();
    }
    

    public Despesas Salvar(Despesas d) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Despesas(descricao, valor, data, categoria_id, orcamento_id) values(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, d.getDescricao());
            stmt.setBigDecimal(2, d.getValor());
            stmt.setDate(3, md.String2Date(d.getData()));
            stmt.setInt(4,d.getCategorias().getId());
            stmt.setInt(5, d.getOrcamentos().getId());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                d.setId(rs.getInt(1));
            } else {
                d.setId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            d.setId(-1);
        }
        return d;
    }
    
     public void Editar(Despesas d) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Despesas SET descricao = ?, valor = ?, data = ?, categoria_id = ?, orcamento_id = ?" + " WHERE id = ?");
            stmt.setString(1, d.getDescricao());
            stmt.setBigDecimal(2, d.getValor());
            stmt.setDate(3, md.String2Date(d.getData()));
            stmt.setInt(4, d.getCategorias().getId());
            stmt.setInt(5, d.getOrcamentos().getId());
            stmt.setInt(6, d.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int Excluir(Despesas d) {
        int verif = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Despesas WHERE id = ?");
            stmt.setInt(1, d.getId());
            verif = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return verif;
    }
    
    public List<Despesas> getDespesas() {
        List<Despesas> lstD = new ArrayList<>();

        ResultSet rs;

        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Despesas");

            rs = ppStmt.executeQuery();

            while (rs.next()) {

                lstD.add(getDespesas(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstD;
    }

    public Despesas getDespesas(int id) {
        Despesas d = new Despesas();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Despesas WHERE id = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id);
            rs = ppStmt.executeQuery();
            rs.first();
            d = getDespesas(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return d;
    }

    public List<Despesas> getDespesasDescr(String descricao) {
        List<Despesas> lstD = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Despesas WHERE descricao ILIKE ?");
            ppStmt.setString(1, descricao + "%");
            rs = ppStmt.executeQuery();
            while (rs.next()) {
                lstD.add(getDespesas(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstD;
    }

    public List<Categorias> getDespesas(String nome) {
        List<Categorias> lstCategorias = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement(
                    "SELECT * FROM Categorias WHERE nome ILIKE ?");

            ppStmt.setString(1, nome + "%");
            rs = ppStmt.executeQuery();

            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("categoria"));

                lstCategorias.add(categoria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstCategorias;
    }
    
    public List<Orcamentos> getDespesas1(String descricao) {
        List<Orcamentos> lstOrcamentos = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement(
                    "SELECT * FROM Orcamentos WHERE descricao ILIKE ?");

            ppStmt.setString(1, descricao + "%");
            rs = ppStmt.executeQuery();

            while (rs.next()) {
                Orcamentos orcamento = new Orcamentos();
                orcamento.setId(rs.getInt("id"));
                orcamento.setDescricao(rs.getString("descricao"));

                lstOrcamentos.add(orcamento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstOrcamentos;
    }
    public Despesas getDespesas(ResultSet rs) throws SQLException {
        Despesas d = new Despesas();

        d.setId(rs.getInt("id"));
        d.setDescricao(rs.getString("descricao"));
        d.setValor(rs.getBigDecimal("valor"));
        d.setData(md.Date2String(rs.getString("data")));
        d.setCategoria(cDAO.getCategorias(rs.getInt("categoria_id")));
        d.setOrcamento(oDAO.getOrcamento(rs.getInt("orcamento_id")));

        return d;
    }

    
}
