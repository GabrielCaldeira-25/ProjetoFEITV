/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;


/**
 *
 * @author rocal
 */
public class VideosDAO {

    private Connection conn; // 1️⃣ variável da conexão

    // 2️⃣ construtor que recebe a conexão
    public VideosDAO(Connection conn) {
        this.conn = conn;
    }

    public ResultSet buscarPorNome(String nome) throws SQLException {

        String sql = "SELECT * FROM video WHERE titulo LIKE ?";

        PreparedStatement stmt = conn.prepareStatement(sql); // usa conn
        stmt.setString(1, "%" + nome + "%");

        return stmt.executeQuery();
    }
}
