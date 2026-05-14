/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.Conexao;
import model.Usuarios;
import view.Logado;
import java.sql.SQLException;
import java.sql.ResultSet;
import dao.VideosDAO;
import javax.swing.JOptionPane;
import java.sql.Connection;
import view.ResultadoBusca;
/**
 *
 * @author rocal
 */
public class ControleLogado {
    private Logado tela4;
    private Usuarios usuario;

    public ControleLogado(Logado tela4, Usuarios usuario) {
        this.tela4 = tela4;
        this.usuario = usuario;
    }
    
    
    public void buscarPorNome(String nome) {

    Conexao conexao = new Conexao();

    try {
        Connection conn = conexao.getConnection();
        VideosDAO dao = new VideosDAO(conn);

        ResultSet res = dao.buscarPorNome(nome);

        if (res.next()) {
            
            int id = res.getInt("id");

            String titulo = res.getString("titulo");
            String ano = res.getString("ano");
            String duracao = res.getString("duracao");
            String tipo = res.getString("tipo");

            ResultadoBusca telaResultado = new ResultadoBusca(usuario, id, titulo, ano, duracao, tipo);

            telaResultado.setVisible(true);
            tela4.dispose();

        } else {
            JOptionPane.showMessageDialog(tela4, "Vídeo não encontrado");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(tela4, "Erro ao buscar vídeos");
    }
}
    
    
}
