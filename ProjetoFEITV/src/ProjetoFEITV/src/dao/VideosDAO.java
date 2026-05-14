/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;



/**
 *
 * @author rocal
 */

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import model.Video;
import model.Filme;
import model.Serie;
import java.util.ArrayList;
import java.util.List;

public class VideosDAO {
    private Connection conn;
    
    public VideosDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM video WHERE titulo LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + nome + "%");
        return stmt.executeQuery();
    }
    
    public Video converterParaVideo(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String titulo = res.getString("titulo");
        String ano = res.getString("ano");
        String duracao = res.getString("duracao");
        String tipo = res.getString("tipo");
        
        if (tipo.equalsIgnoreCase("Filme")) {
            return new Filme(id, titulo, ano, duracao);
        } else {
            return new Serie(id, titulo, ano, duracao);
        }
    }
}
