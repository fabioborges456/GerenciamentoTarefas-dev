package com.unip.sistema.gerenciamento.domain;

import java.util.ArrayList;

public class Categoria {
  private String nome;
  private String descricao;
  private ArrayList<Tarefa> tarefas;

  public Categoria(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
    this.tarefas = new ArrayList<>();
  }

  public Categoria() {

  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public ArrayList<Tarefa> getTarefas() {
    return tarefas;
  }

  public void addTarefa(Tarefa tarefa) {
    tarefas.add(tarefa);
  }

  public void removeTarefa(Tarefa tarefa) {
    tarefas.remove(tarefa);
  }
}
