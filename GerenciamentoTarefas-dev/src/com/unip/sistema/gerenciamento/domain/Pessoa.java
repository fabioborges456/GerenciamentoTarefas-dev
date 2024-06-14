package com.unip.sistema.gerenciamento.domain;

import java.util.ArrayList;

public class Pessoa {
  private String nome;
  private boolean ativo;
  private ArrayList<Tarefa> tarefas;

  public Pessoa(String nome, boolean ativo) {
    this.nome = nome;
    this.ativo = ativo;
    this.tarefas = new ArrayList<>();
  }

  public Pessoa() {

  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getAtivo() {
    if (ativo) {
      return "Ativo";
    } else {
      return "Inativo";
    }
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
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
