package com.unip.sistema.gerenciamento.service;

import com.unip.sistema.gerenciamento.domain.Categoria;
import com.unip.sistema.gerenciamento.domain.Tarefa;
import com.unip.sistema.gerenciamento.system.Armazenamento;
import java.util.Scanner;

public class CategoriaService {

  private final Scanner scanner;
  private final Armazenamento sistema;

  public CategoriaService(Armazenamento armazenamento) {
    scanner = new Scanner(System.in);
    sistema = armazenamento;
  }

  public boolean receberAcao(int acao) {
    switch (acao) {
      case 1 -> criarCategoria();
      case 2 -> listarCategorias();
      case 3 -> atualizarCategoria();
      case 4 -> removerCategoria();
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

  private void criarCategoria() {

    System.out.println("--- Criação de Categoria ---");

    System.out.print("Digite o nome da categoria: ");
    String nome = scanner.nextLine();

    System.out.print("Digite a descrição da categoria: ");
    String descricao = scanner.nextLine();

    Categoria categoria = new Categoria(nome, descricao);
    sistema.adicionarCategoria(categoria);

    System.out.println("Categoria criada com sucesso!");
  }

  private void listarCategorias() {
    var categorias = sistema.getCategorias();

    System.out.println("Listagem de categorias:\n");

    for (Categoria categoria : categorias) {
      System.out.println("Nome: " + categoria.getNome());
      System.out.println("Descrição: " + categoria.getDescricao());
      System.out.println("Lista de tarefas associadas:");
      for (Tarefa tarefa : categoria.getTarefas()) {
        System.out.println("- " + tarefa.getTitulo());
      }
      System.out.println("-------------------------");
    }

    System.out.println("Digite qualquer botão para sair");
    scanner.next();
  }

  private void atualizarCategoria() {
    //Consumir \n do input anterior
    scanner.nextLine();

    Categoria categoria = null;

    while (categoria == null) {
      System.out.println("Digite o nome da categoria que deseja atualizar: ");
      String categoriaTitulo = scanner.nextLine();

      categoria = sistema.buscarCategoria(categoriaTitulo);

      if (categoria == null) {
        System.out.println("Categoria inválida.");
      }
    }

    System.out.println("Digite os novos dados da categoria:");

    System.out.print("Nome: ");
    String novoNome = scanner.nextLine();
    categoria.setNome(novoNome);

    System.out.print("Descrição: ");
    String novaDescricao = scanner.nextLine();
    categoria.setDescricao(novaDescricao);

    System.out.println("Categoria atualizada com sucesso.");
  }

  private void removerCategoria() {
    System.out.println("== Remover Categoria ==");

    //Consumir \n do input anterior
    scanner.nextLine();

    Categoria categoria = null;
    while (categoria == null) {
      System.out.println("Digite o nome da categoria que deseja excluir: ");
      String categoriaTitulo = scanner.nextLine();

      categoria = sistema.buscarCategoria(categoriaTitulo);

      if (categoria == null) {
        System.out.println("Categoria inválida.");
      }
    }
    sistema.removerCategoria(categoria);
    System.out.println("Categoria removida com sucesso!");
  }
}
