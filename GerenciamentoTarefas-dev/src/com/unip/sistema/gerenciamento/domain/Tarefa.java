package com.unip.sistema.gerenciamento.domain;

public class Tarefa {
  private String titulo;
  private String descricao;
  private boolean status;

  public Tarefa(String titulo, String descricao, boolean status) {
    this.titulo = titulo;
    this.descricao = descricao;
    this.status = status;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getStatus() {
    if (status) {
      return "Concluida";
    } else {
      return "Pendente";
    }
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}

