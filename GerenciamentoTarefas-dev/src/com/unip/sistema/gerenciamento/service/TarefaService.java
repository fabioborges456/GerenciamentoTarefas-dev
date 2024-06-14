package com.unip.sistema.gerenciamento.service;

import com.unip.sistema.gerenciamento.domain.Categoria;
import com.unip.sistema.gerenciamento.domain.Pessoa;
import com.unip.sistema.gerenciamento.domain.Tarefa;
import com.unip.sistema.gerenciamento.system.Armazenamento;
import java.util.Scanner;

public class TarefaService {
  private final Scanner scanner;
  private final Armazenamento sistema;

  public TarefaService(Armazenamento armazenamento) {
    scanner = new Scanner(System.in);
    sistema = armazenamento;
  }

  public boolean receberAcao(int acao) {
    switch (acao) {
      case 1 -> criarTarefa();
      case 2 -> listarTarefas();
      case 3 -> atualizarTarefa();
      case 4 -> removerTarefa();
      case 0 -> {
        return true;
      }
      default -> {
        System.out.println("Opção inválida!");
        return false;
      }
    }
    return true;
  }

  private void criarTarefa() {

    System.out.println("--- Criação de Tarefa ---");

    System.out.print("Digite o título da tarefa: ");
    String titulo = scanner.nextLine();

    System.out.print("Digite a descrição da tarefa: ");
    String descricao = scanner.nextLine();

    Categoria categoria = buscarCategoria();

    Pessoa pessoa = buscarPessoa();

    boolean status = false;
    System.out.print("A tarefa está concluída? (S/N): ");
    String statusStr = scanner.next();

    if (statusStr.equalsIgnoreCase("S")) {
      status = true;
    }

    Tarefa tarefa = new Tarefa(titulo, descricao, status);
    sistema.adicionarTarefa(tarefa);

    if (categoria.getNome() != null) {
      categoria.addTarefa(tarefa);
    }

    if (pessoa.getNome() != null) {
      pessoa.addTarefa(tarefa);
    }

    System.out.println("Tarefa criada com sucesso!");
  }

  private void listarTarefas() {
    var tarefas = sistema.getTarefas();

    System.out.println("Listagem de tarefas:\n");

    for (Tarefa tarefa : tarefas) {
      System.out.println("Título: " + tarefa.getTitulo());
      System.out.println("Descrição: " + tarefa.getDescricao());
      System.out.println("Status: " + tarefa.getStatus());
      System.out.println("-------------------------");
    }

    System.out.println("Digite qualquer botão para sair");
    scanner.next();
  }

  private void atualizarTarefa() {
    // Consumir \n do input anterior
    scanner.nextLine();

    Tarefa tarefa = null;

    while (tarefa == null) {
      System.out.println("Digite o título da tarefa que deseja atualizar: ");
      String tarefaTitulo = scanner.nextLine();

      tarefa = sistema.buscarTarefa(tarefaTitulo);

      if (tarefa == null) {
        System.out.println("Tarefa não encontrada.");
      }
    }

    var categoriaAnterior = sistema.buscarCategoriaPorTarefa(tarefa);
    if (categoriaAnterior != null) {
      categoriaAnterior.removeTarefa(tarefa);
    }

    var pessoaAnterior = sistema.buscarPessoaPorTarefa(tarefa);
    if (pessoaAnterior != null) {
      pessoaAnterior.removeTarefa(tarefa);
    }

    System.out.println("Digite os novos dados da tarefa:");

    System.out.print("Título: ");
    String novoTitulo = scanner.nextLine();
    tarefa.setTitulo(novoTitulo);

    System.out.print("Descrição: ");
    String novaDescricao = scanner.nextLine();
    tarefa.setDescricao(novaDescricao);

    Categoria categoria = buscarCategoria();

    Pessoa pessoa = buscarPessoa();

    System.out.print("A tarefa está concluída? (S/N): ");
    String statusStr = scanner.nextLine();

    tarefa.setStatus(statusStr.equalsIgnoreCase("S"));

    if (categoria.getNome() != null) {
      categoria.addTarefa(tarefa);
    }

    if (pessoa.getNome() != null) {
      pessoa.addTarefa(tarefa);
    }

    System.out.println("Tarefa atualizada com sucesso.");
  }

  private void removerTarefa() {
    System.out.println("== Remover Tarefa ==");

    //Consumir \n do input anterior
    scanner.nextLine();

    Tarefa tarefa = null;
    while (tarefa == null) {
      System.out.println("Digite o título da tarefa que deseja excluir: ");
      String tarefaTitulo = scanner.nextLine();

      tarefa = sistema.buscarTarefa(tarefaTitulo);

      if (tarefa == null) {
        System.out.println("Tarefa não encontrada.");
      }
    }

    var categoria = sistema.buscarCategoriaPorTarefa(tarefa);
    if (categoria != null) {
      categoria.removeTarefa(tarefa);
    }

    var pessoa = sistema.buscarPessoaPorTarefa(tarefa);
    if (pessoa != null) {
      pessoa.removeTarefa(tarefa);
    }

    sistema.removerTarefa(tarefa);
    System.out.println("Tarefa removida com sucesso!");
  }

  private Categoria buscarCategoria() {
    Categoria categoria = null;

    while (categoria == null) {
      System.out.println("Digite a categoria da tarefa: (0 para seguir sem categoria) ");
      String categoriaTitulo = scanner.nextLine();

      if (categoriaTitulo.equals("0")) {
        categoria = new Categoria();
        continue;
      }

      categoria = sistema.buscarCategoria(categoriaTitulo);

      if (categoria == null) {
        System.out.println("Categoria inválida.");
      }
    }
    return categoria;
  }

  private Pessoa buscarPessoa() {
    Pessoa pessoa = null;

    while (pessoa == null) {
      System.out.println("Digite a pessoa da tarefa: (0 para seguir sem pessoa) ");
      String nome = scanner.nextLine();

      if (nome.equals("0")) {
        pessoa = new Pessoa();
        continue;
      }

      pessoa = sistema.buscarPessoa(nome);

      if (pessoa == null) {
        System.out.println("Pessoa não encontrada.");
      }
    }
    return pessoa;
  }

}
