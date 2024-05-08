package com.hoteljpj.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private Double wifi;
    private Double estacionamento;
    private Double cafe;
    private Double almoco;
    private Double janta;
    private Integer classificacao;
    private Integer qtdQuartos;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFundacao;


    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getWifi() {
        return wifi;
    }

    public void setWifi(Double wifi) {
        this.wifi = wifi;
    }

    public Double getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Double estacionamento) {
        this.estacionamento = estacionamento;
    }

    public Double getCafe() {
        return cafe;
    }

    public void setCafe(Double cafe) {
        this.cafe = cafe;
    }

    public Double getAlmoco() {
        return almoco;
    }

    public void setAlmoco(Double almoco) {
        this.almoco = almoco;
    }

    public Double getJanta() {
        return janta;
    }

    public void setJanta(Double janta) {
        this.janta = janta;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    public Integer getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(Integer qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }
}
