/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author rocal
 */

import dao.Conexao;
import dao.ListaVideoDAO;
import dao.VideosDAO;
import model.Usuarios;
import view.VideosDaLista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;



public class ControleVideosDaLista {
    private VideosDaLista tela;
    private Usuarios usuario;
    private int listaId;
    
    public ControleVideosDaLista(VideosDaLista tela, Usuarios usuario, int listaId) {
        this.tela = tela;
        this.usuario = usuario;
        this.listaId = listaId;
    }
    
    public void carregarVideos() {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            ListaVideoDAO dao = new ListaVideoDAO(conn);
            ResultSet res = dao.buscarVideos(listaId);
            
            JPanel painel = tela.getPainelVideos();
            painel.removeAll();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            
            while (res.next()) {
                int videoId = res.getInt("id");
                String titulo = res.getString("titulo");
                
                JPanel linha = new JPanel();
                linha.add(new JLabel(titulo));
                
                JButton btnRemover = new JButton("Remover");
                btnRemover.addActionListener(e -> {
                    removerVideo(videoId);
                });
                
                linha.add(btnRemover);
                painel.add(linha);
            }
            painel.revalidate();
            painel.repaint();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao carregar vídeos");
        }
    }
    
    public void adicionarVideo(String busca) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            VideosDAO dao = new VideosDAO(conn);
            ResultSet res = dao.buscarPorNome(busca);
            
            if (res.next()) {
                int videoId = res.getInt("id");
                Conexao conexao2 = new Conexao();
                Connection conn2 = conexao2.getConnection();
                ListaVideoDAO dao2 = new ListaVideoDAO(conn2);
                dao2.adicionarVideo(listaId, videoId);
                JOptionPane.showMessageDialog(tela, "Vídeo adicionado!");
                carregarVideos();
            } else {
                JOptionPane.showMessageDialog(tela, "Vídeo não encontrado!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao adicionar vídeo");
        }
    }
    
    private void removerVideo(int videoId) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            ListaVideoDAO dao = new ListaVideoDAO(conn);
            dao.removerVideo(listaId, videoId);
            carregarVideos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao remover vídeo");
        }
    }
}
