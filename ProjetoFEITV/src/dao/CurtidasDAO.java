/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author rocal
 */
public class CurtidasDAO {
    private Connection connection;

    public CurtidasDAO(Connection connection) {
        this.connection = connection;
    }

    public void curtir(String email, int videoId) throws SQLException {

        String sql = "INSERT INTO curtidas (usuario_email, video_id) VALUES (?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setInt(2, videoId);

        stmt.execute();
    }
    
    public ResultSet buscarCurtidas(String email) throws SQLException {

    String sql = """
        SELECT v.id, v.titulo, v.ano, v.duracao, v.tipo
        FROM curtidas c
        JOIN video v ON c.video_id = v.id
        WHERE c.usuario_email = ?
    """;

    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1, email);

    return stmt.executeQuery();
    }
    
    public void removerCurtida(String email, int videoId) throws SQLException {

    String sql = "DELETE FROM curtidas WHERE usuario_email = ? AND video_id = ?";

    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1, email);
    stmt.setInt(2, videoId);

    stmt.executeUpdate();
    }
    
}
