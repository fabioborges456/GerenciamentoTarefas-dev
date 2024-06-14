package com.unip.sistema.gerenciamento.system;

import com.unip.sistema.gerenciamento.domain.Categoria;
import com.unip.sistema.gerenciamento.domain.Pessoa;
import com.unip.sistema.gerenciamento.domain.Tarefa;
import java.util.ArrayList;
import java.util.List;

public class Armazenamento {
  private final List<Tarefa> tarefas;
  private final List<Categoria> categorias;
  private final List<Pessoa> pessoas;

  public Armazenamento() {
    this.tarefas = new ArrayList<>();
    this.categorias = new ArrayList<>();
    this.pessoas = new ArrayList<>();
  }

  public void adicionarTarefa(Tarefa tarefa) {
    this.tarefas.add(tarefa);
  }

  public void adicionarCategoria(Categoria categoria) {
    this.categorias.add(categoria);
  }

  public void adicionarPessoa(Pessoa pessoa) {
    this.pessoas.add(pessoa);
  }

  public void removerTarefa(Tarefa tarefa) {
    this.tarefas.remove(tarefa);
    var categoria = buscarCategoriaPorTarefa(tarefa);
    if (categoria != null) {
      categoria.removeTarefa(tarefa);
    }
  }

  public void removerCategoria(Categoria categoria) {
    this.categorias.remove(categoria);
  }

  public void removerPessoa(Pessoa pessoa) {
    this.pessoas.remove(pessoa);
  }

  public List<Tarefa> getTarefas() {
    return this.tarefas;
  }

  public List<Categoria> getCategorias() {
    return this.categorias;
  }

  public List<Pessoa> getPessoas() {
    return this.pessoas;
  }

  public Tarefa buscarTarefa(String titulo) {
    for (Tarefa tarefa : tarefas) {
      if (tarefa.getTitulo().equals(titulo)) {
        return tarefa;
      }
    }
    return null;
  }

  public Categoria buscarCategoria(String nome) {
    for (Categoria categoria : this.categorias) {
      if (categoria.getNome().equals(nome)) {
        return categoria;
      }
    }
    return null;
  }

  public Categoria buscarCategoriaPorTarefa(Tarefa tarefa) {
    for (Categoria categoria : categorias) {
      if (categoria.getTarefas().contains(tarefa)) {
        return categoria;
      }
    }
    return null;
  }

  public Pessoa buscarPessoa(String nome) {
    for (Pessoa pessoa : pessoas) {
      if (pessoa.getNome().equals(nome)) {
        return pessoa;
      }
    }
    return null;
  }

  public Pessoa buscarPessoaPorTarefa(Tarefa tarefa) {
    for (Pessoa pessoa : pessoas) {
      if (pessoa.getTarefas().contains(tarefa)) {
        return pessoa;
      }
    }
    return null;
  }

}

