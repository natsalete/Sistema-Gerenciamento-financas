package com.sd.prj_mvc_model.DAO;

import com.sd.prj_mvc_model.objetos.Fornecedores;
import com.sd.prj_mvc_util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresDao {
    Connection conn;
   
    public FornecedoresDao() {
        conn = new Conexao().conectar();
    }
    

    public Fornecedores Salvar(Fornecedores f) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Fornecedores(nome, telefone, email) values(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3,f.getEmail());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                f.setId(rs.getInt(1));
            } else {
                f.setId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            f.setId(-1);
        }
        return f;
    }
    
     public void Editar(Fornecedores f) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Fornecedores SET nome = ?, telefone = ?, email = ?" + " WHERE id = ?");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getEmail());
            stmt.setInt(4, f.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int Excluir(Fornecedores f) {
        int verif = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Fornecedores WHERE id = ?");
            stmt.setInt(1, f.getId());
            verif = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return verif;
    }
    
   public List<Fornecedores> getFornecedores() {
        List<Fornecedores> lstF = new ArrayList<>();

        ResultSet rs;

        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Fornecedores");

            rs = ppStmt.executeQuery();

            while (rs.next()) {
                lstF.add(getFornecedores(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstF;
    }

    public Fornecedores getFornecedores(int id) {
        Fornecedores c = new Fornecedores();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Fornecedores WHERE id = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id);
            rs = ppStmt.executeQuery();
            rs.first();
            c = getFornecedores(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public List<Fornecedores> getFornecedores(String nome) {
        List<Fornecedores> lstF = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Fornecedores WHERE nome ILIKE ?");
            ppStmt.setString(1, nome + "%");
            rs = ppStmt.executeQuery();
            while (rs.next()) {
                lstF.add(getFornecedores(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstF;
    }

      
    public Fornecedores getFornecedores(ResultSet rs) throws SQLException {
        Fornecedores f = new Fornecedores();

        f.setId(rs.getInt("id"));
        f.setNome(rs.getString("nome"));
        f.setNome(rs.getString("telefone"));
        f.setNome(rs.getString("email"));
      
        return f;
    }

    
}
