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
    private Boolean wifi;
    private Boolean estacionamento;
    private Boolean cafe;
    private Boolean almoco;
    private Boolean janta;
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

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Boolean estacionamento) {
        this.estacionamento = estacionamento;
    }

    public Boolean getCafe() {
        return cafe;
    }

    public void setCafe(Boolean cafe) {
        this.cafe = cafe;
    }

    public Boolean getAlmoco() {
        return almoco;
    }

    public void setAlmoco(Boolean almoco) {
        this.almoco = almoco;
    }

    public Boolean getJanta() {
        return janta;
    }

    public void setJanta(Boolean janta) {
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
