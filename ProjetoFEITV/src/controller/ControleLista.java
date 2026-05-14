/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Conexao;
import dao.ListaDAO;
import model.Usuarios;
import view.Lista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import view.VideosDaLista;

/**
 *
 * @author rocal
 */
public class ControleLista {
    private Lista tela;
    private Usuarios usuario;
    
    public ControleLista(Lista tela, Usuarios usuario) {
        this.tela = tela;
        this.usuario = usuario;
    }
    
    public void carregarListas() {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            ListaDAO dao = new ListaDAO(conn);
            ResultSet res = dao.buscarListas(usuario.getEmail());
            
            JPanel painel = tela.getPainelListas();
            painel.removeAll();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            
            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("nome");
                
                JPanel linha = new JPanel();
                JLabel lblNome = new JLabel(nome);
                
                JButton btnEditar = new JButton("Editar");
                btnEditar.addActionListener(e -> {
                    VideosDaLista tela = new VideosDaLista(usuario, id, nome);
                    tela.setVisible(true);
                });
                
                JButton btnRemover = new JButton("Remover");
                btnRemover.addActionListener(e -> {
                    removerLista(id);
                });
                
                linha.add(lblNome);
                linha.add(btnEditar);
                linha.add(btnRemover);
                painel.add(linha);
            }
            painel.revalidate();
            painel.repaint();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao carregar listas");
        }
    }
    
    public void criarLista(String nome) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            ListaDAO dao = new ListaDAO(conn);
            dao.criarLista(nome, usuario.getEmail());
            carregarListas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao criar lista");
        }
    }
    
    private void removerLista(int id) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            ListaDAO dao = new ListaDAO(conn);
            dao.removerLista(id);
            carregarListas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao remover lista");
        }
    }
    
}
