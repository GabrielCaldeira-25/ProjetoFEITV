/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurtidasDAO;
import dao.Conexao;
import model.Usuarios;
import view.ResultadoBusca;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ControleResultadoBusca {

    private ResultadoBusca tela;
    private Usuarios usuario;
    private int videoId;

    public ControleResultadoBusca(ResultadoBusca tela, Usuarios usuario, int videoId) {
        this.tela = tela;
        this.usuario = usuario;
        this.videoId = videoId;
    }

    public void curtirVideo() {

        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            CurtidasDAO dao = new CurtidasDAO(conn);

            dao.curtir(usuario.getEmail(), videoId);

            JOptionPane.showMessageDialog(tela, "Curtido com sucesso!");

        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate") || e.getMessage().contains("unique")) {
                JOptionPane.showMessageDialog(tela, "Você já curtiu esse vídeo!");
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao curtir: " + e.getMessage());
            }
        }
    }
}