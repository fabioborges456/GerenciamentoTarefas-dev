package com.unip.sistema.gerenciamento.system;

import com.unip.sistema.gerenciamento.service.CategoriaService;
import com.unip.sistema.gerenciamento.service.PessoaService;
import com.unip.sistema.gerenciamento.service.TarefaService;
import java.util.Scanner;

public class OperacoesSistema {

  private final Scanner scanner;
  private final TarefaService tarefaService;
  private final CategoriaService categoriaService;
  private final PessoaService pessoaService;

  public OperacoesSistema() {
    scanner = new Scanner(System.in);
    Armazenamento sistema = new Armazenamento();
    tarefaService = new TarefaService(sistema);
    categoriaService = new CategoriaService(sistema);
    pessoaService = new PessoaService(sistema);
  }

  public void exibirMenuPrincipal() {
    System.out.println("------ MENU PRINCIPAL ------");
    System.out.println("1 - Tarefas");
    System.out.println("2 - Categorias");
    System.out.println("3 - Pessoas");
    System.out.println("0 - Sair");
    System.out.println("-----------------------------");

    System.out.print("Escolha uma opção: ");
    int opcao = scanner.nextInt();

    switch (opcao) {
      case 1 -> exibirMenuTarefas();
      case 2 -> exibirMenuCategorias();
      case 3 -> exibirMenuPessoas();
      case 0 -> {
        System.out.println("Saindo do sistema...");
        System.exit(0);
      }
      default -> {
        System.out.println("Opção inválida. Tente novamente.");
        exibirMenuPrincipal();
      }
    }
  }

  public void exibirMenuTarefas() {
    System.out.println("------ MENU DE TAREFAS ------");
    System.out.println("1 - Criar tarefa");
    System.out.println("2 - Consultar tarefas");
    System.out.println("3 - Atualizar tarefa");
    System.out.println("4 - Excluir tarefa");
    System.out.println("0 - Voltar");
    System.out.println("-----------------------------");

    System.out.print("Escolha uma opção: ");
    int opcao = scanner.nextInt();

    if (tarefaService.receberAcao(opcao)) {
      exibirMenuPrincipal();
    } else {
      exibirMenuTarefas();
    }
  }

  public void exibirMenuCategorias() {
    System.out.println("------ MENU DE CATEGORIAS ------");
    System.out.println("1 - Criar categoria");
    System.out.println("2 - Consultar categorias");
    System.out.println("3 - Atualizar categoria");
    System.out.println("4 - Excluir categoria");
    System.out.println("0 - Voltar");
    System.out.println("-----------------------------");

    System.out.print("Escolha uma opção: ");
    int opcao = scanner.nextInt();

    if (categoriaService.receberAcao(opcao)) {
      exibirMenuPrincipal();
    } else {
      exibirMenuCategorias();
    }
  }

  public void exibirMenuPessoas() {
    System.out.println("------ MENU DE PESSOAS ------");
    System.out.println("1 - Criar pessoa");
    System.out.println("2 - Consultar pessoas");
    System.out.println("3 - Atualizar pessoa");
    System.out.println("4 - Excluir pessoa");
    System.out.println("0 - Voltar");
    System.out.println("-----------------------------");

    System.out.print("Escolha uma opção: ");
    int opcao = scanner.nextInt();

    if (pessoaService.receberAcao(opcao)) {
      exibirMenuPrincipal();
    } else {
      exibirMenuPessoas();
    }
  }
}