/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurtidasDAO;
import dao.Conexao;
import model.Usuarios;
import view.Curtidas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

/**
 *
 * @author rocal
 */
public class ControleCurtidas {
    private Curtidas tela;
    private Usuarios usuario;

    public ControleCurtidas(Curtidas tela, Usuarios usuario) {
        this.tela = tela;
        this.usuario = usuario;
    }

    public void carregarCurtidas() {

        

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConnection();
            CurtidasDAO dao = new CurtidasDAO(conn);

            ResultSet res = dao.buscarCurtidas(usuario.getEmail());

            JPanel painel = tela.getPainelCurtidas();
            painel.removeAll();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

            while (res.next()) {

                int id = res.getInt("id");
                String titulo = res.getString("titulo");

                JPanel linha = new JPanel();
                JButton btnDescurtir = new JButton("Descurtir");

                btnDescurtir.addActionListener(e -> {
                    descurtir(id);
                });

                linha.add(new JLabel(titulo));
                linha.add(btnDescurtir);

                painel.add(linha);
            }

            painel.revalidate();
            painel.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao carregar curtidas");
        }
    }

    private void descurtir(int videoId) {

        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            CurtidasDAO dao = new CurtidasDAO(conn);

            dao.removerCurtida(usuario.getEmail(), videoId);

            carregarCurtidas(); // 🔥 Atualiza a tela automaticamente

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao remover curtida");
        }
    }
    
}
