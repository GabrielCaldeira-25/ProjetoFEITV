/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UsuariosDAO;
import dao.Conexao;
import model.Usuarios;
import view.login;
import view.Logado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rocal
 */
public class ControleLogin {
    
    private login tela1;
    public ControleLogin(login tela1){
        this.tela1 = tela1;
    }
    
    public void loginUsuario(){
        Usuarios usuario = new Usuarios(null, tela1.getEmail(), tela1.getSenha());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuariosDAO dao = new UsuariosDAO(conn);
            ResultSet res = dao.consultar(usuario);
            if(res.next()){
                JOptionPane.showMessageDialog(tela1, "Login feito!","Aviso", JOptionPane.INFORMATION_MESSAGE);
                String nome = res.getString("nome");
                String email = res.getString("email");
                String senha = res.getString("senha");
                Logado tela2 = new Logado(new Usuarios(nome, email, senha));
                tela2.setVisible(true);
                tela1.setVisible(false);
                
            }else{
                JOptionPane.showMessageDialog(tela1, "login não efetuado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
         
        }catch(SQLException e){
            JOptionPane.showMessageDialog(tela1, "Erro de conexao", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
