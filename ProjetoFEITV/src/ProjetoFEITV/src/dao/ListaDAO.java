/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rocal
 */
public class ListaDAO {
    private Connection connection;
    
    public ListaDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void criarLista(String nome, String email) throws SQLException {
        String sql = "INSERT INTO lista (nome, usuario_email) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, email);
        stmt.execute();
        connection.close();
    }
    
    public ResultSet buscarListas(String email) throws SQLException {
        String sql = "SELECT * FROM lista WHERE usuario_email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        return stmt.executeQuery();
    }
    
    public void removerLista(int id) throws SQLException {
    // primeiro remove os videos da lista
    String sql1 = "DELETE FROM lista_video WHERE lista_id = ?";
    PreparedStatement stmt1 = connection.prepareStatement(sql1);
    stmt1.setInt(1, id);
    stmt1.executeUpdate();
    
    // depois remove a lista
    String sql2 = "DELETE FROM lista WHERE id = ?";
    PreparedStatement stmt2 = connection.prepareStatement(sql2);
    stmt2.setInt(1, id);
    stmt2.executeUpdate();
    
    connection.close();
}



    
    
    
}
