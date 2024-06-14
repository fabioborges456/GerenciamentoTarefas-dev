package com.unip.sistema.gerenciamento.service;

import com.unip.sistema.gerenciamento.domain.Pessoa;
import com.unip.sistema.gerenciamento.domain.Tarefa;
import com.unip.sistema.gerenciamento.system.Armazenamento;
import java.util.Scanner;

public class PessoaService {
  private final Scanner scanner;
  private final Armazenamento sistema;

  public PessoaService(Armazenamento armazenamento) {
    scanner = new Scanner(System.in);
    sistema = armazenamento;
  }

  public boolean receberAcao(int acao) {
    switch (acao) {
      case 1 -> criarPessoa();
      case 2 -> listarPessoas();
      case 3 -> atualizarPessoa();
      case 4 -> removerPessoa();
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

  private void criarPessoa() {

    System.out.println("--- Criação de Pessoa ---");

    System.out.print("Digite o nome da pessoa: ");
    String nome = scanner.nextLine();

    boolean status = false;
    System.out.print("A pessoa está ativa? (S/N): ");
    String statusStr = scanner.next();

    if (statusStr.equalsIgnoreCase("S")) {
      status = true;
    }

    Pessoa pessoa = new Pessoa(nome, status);
    sistema.adicionarPessoa(pessoa);

    System.out.println("Pessoa criada com sucesso!");
  }

  private void listarPessoas() {
    var pessoas = sistema.getPessoas();

    System.out.println("Listagem de pessoas:\n");

    for (Pessoa pessoa : pessoas) {
      System.out.println("Nome: " + pessoa.getNome());
      System.out.println("Status: " + pessoa.getAtivo());
      System.out.println("Lista de tarefas associadas:");
      for (Tarefa tarefa : pessoa.getTarefas()) {
        System.out.println("- " + tarefa.getTitulo());
      }
      System.out.println("-------------------------");
    }

    System.out.println("Digite qualquer botão para sair");
    scanner.next();
  }

  private void atualizarPessoa() {
    // Consumir \n do input anterior
    scanner.nextLine();

    Pessoa pessoa = null;

    while (pessoa == null) {
      System.out.println("Digite o nome da pessoa que deseja atualizar: ");
      String nome = scanner.nextLine();

      pessoa = sistema.buscarPessoa(nome);

      if (pessoa == null) {
        System.out.println("Pessoa não encontrada.");
      }
    }

    System.out.println("Digite os novos dados da pessoa:");

    System.out.print("Nome: ");
    String novoNome = scanner.nextLine();
    pessoa.setNome(novoNome);

    boolean novoStatus = false;

    System.out.print("A pessoa está ativa? (S/N): ");
    String statusStr = scanner.next();

    if (statusStr.equalsIgnoreCase("S")) {
      novoStatus = true;
    }
    pessoa.setAtivo(novoStatus);

    System.out.println("Categoria atualizada com sucesso.");
  }

  private void removerPessoa() {
    System.out.println("== Remover Pessoa ==");

    //Consumir \n do input anterior
    scanner.nextLine();

    Pessoa pessoa = null;
    while (pessoa == null) {
      System.out.println("Digite o nome da pessoa que deseja excluir: ");
      String nome = scanner.nextLine();

      pessoa = sistema.buscarPessoa(nome);

      if (pessoa == null) {
        System.out.println("Pessoa não encontrada.");
      }
    }
    sistema.removerPessoa(pessoa);
    System.out.println("Pessoa removida com sucesso!");
  }
}
