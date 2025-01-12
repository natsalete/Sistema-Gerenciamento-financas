package com.sd.prj_mvc_model.DAO;

import com.sd.prj_mvc_util.Conexao;
import com.sd.prj_mvc_model.objetos.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    
    Connection conn;

    public CategoriaDao() {
        conn = new Conexao().conectar();
    }
    
    public Categorias Salvar(Categorias c) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Categorias(nome) values(?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, c.getNome());
            stmt.execute();// Executa o SQL no banco
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                c.setId(rs.getInt("id"));
            } else {
                c.setId(-1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public void Editar(Categorias c) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Categorias SET nome = ? " + " WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int Excluir(Categorias c) {
        int verif = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Categorias WHERE id = ?");
            stmt.setInt(1, c.getId());
            verif = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return verif;
    }

    public List<Categorias> getCategorias() {
        List<Categorias> lstC = new ArrayList<>();

        ResultSet rs;

        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Categorias");

            rs = ppStmt.executeQuery();

            while (rs.next()) {
                lstC.add(getCategorias(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstC;
    }

    public Categorias getCategorias(int id) {
        Categorias c = new Categorias();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Categorias WHERE id = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ppStmt.setInt(1, id);
            rs = ppStmt.executeQuery();
            rs.first();
            c = getCategorias(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public List<Categorias> getCategorias(String nome) {
        List<Categorias> lstC = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Categorias WHERE nome ILIKE ?");
            ppStmt.setString(1, nome + "%");
            rs = ppStmt.executeQuery();
            while (rs.next()) {
                lstC.add(getCategorias(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lstC;
    }

    public Categorias getCategorias(ResultSet rs) throws SQLException {
        Categorias c = new Categorias();

        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
    
        return c;
    }
    
}
