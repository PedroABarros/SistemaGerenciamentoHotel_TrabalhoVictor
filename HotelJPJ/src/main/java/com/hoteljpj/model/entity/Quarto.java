package com.hoteljpj.model.entity;

import jakarta.persistence.*;


@Entity
@Table (name = "quarto")
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String indentificacao;
    private String status;
    private String tipoCama;
    private int quantidadeLeito;
    private double preco;
    private double vista;
    private String comodidades;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndentificacao() {
        return indentificacao;
    }

    public void setIndentificacao(String indentificacao) {
        this.indentificacao = indentificacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoCama() {
        return tipoCama;
    }

    public void setTipoCama(String tipoCama) {
        this.tipoCama = tipoCama;
    }

    public int getQuantidadeLeito() {
        return quantidadeLeito;
    }

    public void setQuantidadeLeito(int quantidadeLeito) {
        this.quantidadeLeito = quantidadeLeito;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getVista() {
        return vista;
    }

    public void setVista(double vista) {
        this.vista = vista;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
