/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rocal
 */
public abstract class Video {
    private int id;
    private String titulo;
    private String ano;
    private String duracao;
    private String tipo;

    public Video(int id, String titulo, String ano, String duracao, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAno() { return ano; }
    public String getDuracao() { return duracao; }
    public String getTipo() { return tipo; }
}
