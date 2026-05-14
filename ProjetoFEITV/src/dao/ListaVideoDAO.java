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
public class ListaVideoDAO {
    private Connection connection;
    
    public ListaVideoDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void adicionarVideo(int listaId, int videoId) throws SQLException {
        String sql = "INSERT INTO lista_video (lista_id, video_id) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, listaId);
        stmt.setInt(2, videoId);
        stmt.execute();
        connection.close();
    }
    
    public ResultSet buscarVideos(int listaId) throws SQLException {
        String sql = """
            SELECT v.id, v.titulo, v.ano, v.duracao, v.tipo
            FROM lista_video lv
            JOIN video v ON lv.video_id = v.id
            WHERE lv.lista_id = ?
        """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, listaId);
        return stmt.executeQuery();
    }
    
    public void removerVideo(int listaId, int videoId) throws SQLException {
        String sql = "DELETE FROM lista_video WHERE lista_id = ? AND video_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, listaId);
        stmt.setInt(2, videoId);
        stmt.executeUpdate();
        connection.close();
    }
}
