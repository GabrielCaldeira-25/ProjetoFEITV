/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuarios;
/**
 *
 * @author rocal
 */
public class UsuariosDAO {
    private Connection conn;

    public UsuariosDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Usuarios usuario) throws SQLException{
        String sql ="select * from usuario where email = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public void inserir(Usuarios usuario)throws SQLException{
        String sql = "insert into usuario(nome, email, senha) values ('"
                    + usuario.getNome() + "', '"
                    + usuario.getEmail() + "', '"
                    + usuario.getSenha() + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    
}
