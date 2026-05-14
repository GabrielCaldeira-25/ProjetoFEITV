/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UsuariosDAO;
import dao.Conexao;
import model.Usuarios;
import view.Cadastro;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rocal
 */
public class ControleCadastro {
    private Cadastro tela3;

    public ControleCadastro(Cadastro tela3) {
        this.tela3 = tela3;
    }
    
    public void salvarUsuario(){
        String nome = tela3.getNome();
        String email = tela3.getEmail();
        String senha = tela3.getSenha();
        Usuarios usuario = new Usuarios(nome, email, senha);
        
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuariosDAO dao = new UsuariosDAO(conn);
            dao.inserir(usuario);
            JOptionPane.showMessageDialog(tela3, "Usuario cadastrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(tela3, "Usuario não cadastrado!", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
