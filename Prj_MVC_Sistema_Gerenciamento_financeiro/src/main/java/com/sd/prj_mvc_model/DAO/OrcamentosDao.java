package com.sd.prj_mvc_model.DAO;

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

public class OrcamentosDao {
    // Status exatamente como definidos no banco
    private static final String STATUS_EM_ANDAMENTO = "Em Andamento";
    private static final String STATUS_CONCLUIDO = "Concluído";
    private static final String STATUS_CANCELADO = "Cancelado";
    
    Connection conn;
    ManipulaData md;
    
    public OrcamentosDao() {
        conn = new Conexao().conectar();
        md = new ManipulaData();
    }
    
    public Orcamentos Salvar(Orcamentos o) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Orcamentos(descricao, valor_total, data_inicio, data_fim, status) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, o.getDescricao());
            stmt.setBigDecimal(2, o.getValorTotal());
            stmt.setDate(3, md.String2Date(o.getDataInicio()));
            stmt.setDate(4, md.String2Date(o.getDataFim()));
            stmt.setString(5, STATUS_EM_ANDAMENTO); // Status padrão conforme definido no banco
            
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                o.setId(rs.getInt(1));
            } else {
                o.setId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    public void Editar(Orcamentos o) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Orcamentos SET descricao = ?, valor_total = ?, data_inicio = ?, data_fim = ? WHERE id = ? AND status = ?"
            );
            stmt.setString(1, o.getDescricao());
            stmt.setBigDecimal(2, o.getValorTotal());
            stmt.setDate(3, md.String2Date(o.getDataInicio()));
            stmt.setDate(4, md.String2Date(o.getDataFim()));
            stmt.setInt(5, o.getId());
            stmt.setString(6, STATUS_EM_ANDAMENTO); // Só permite editar orçamentos em andamento
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int Excluir(Orcamentos o) {
        int verif = 0;
        try {
            // Só permite excluir orçamentos em andamento
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Orcamentos WHERE id = ? AND status = ?");
            stmt.setInt(1, o.getId());
            stmt.setString(2, STATUS_EM_ANDAMENTO);
            verif = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return verif;
    }
    
    public boolean ConcluirOrcamento(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Orcamentos SET status = ? WHERE id = ? AND status = ?"
            );
            stmt.setString(1, STATUS_CONCLUIDO);
            stmt.setInt(2, id);
            stmt.setString(3, STATUS_EM_ANDAMENTO);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean CancelarOrcamento(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Orcamentos SET status = ? WHERE id = ? AND status = ?"
            );
            stmt.setString(1, STATUS_CANCELADO);
            stmt.setInt(2, id);
            stmt.setString(3, STATUS_EM_ANDAMENTO);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Orcamentos> getOrcamentos() {
        List<Orcamentos> lstO = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orcamentos ORDER BY data_inicio DESC");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lstO.add(getOrcamentoFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstO;
    }

    public Orcamentos getOrcamento(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Orcamentos WHERE id = ?",
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                return getOrcamentoFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Orcamentos> getOrcamentosPorStatus(String status) {
        List<Orcamentos> lstO = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Orcamentos WHERE status = ? ORDER BY data_inicio DESC"
            );
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lstO.add(getOrcamentoFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstO;
    }

    private Orcamentos getOrcamentoFromResultSet(ResultSet rs) throws SQLException {
        Orcamentos o = new Orcamentos();
        o.setId(rs.getInt("id"));
        o.setDescricao(rs.getString("descricao"));
        o.setValorTotal(rs.getBigDecimal("valor_total"));
        o.setDataInicio(md.Date2String(rs.getString("data_inicio")));
        o.setDataFim(md.Date2String(rs.getString("data_fim")));
        o.setStatus(rs.getString("status"));
        return o;
    }
}
