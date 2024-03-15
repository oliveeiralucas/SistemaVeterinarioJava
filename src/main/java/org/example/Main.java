package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaVeterinario.executar();
    }
}

class Animal {
    private int id;
    private String nome;
    private String especie;
    private int idade;
    private double peso;
    private String raca;
    private String cor;
    private String sexo;
    private String proprietario;

    public Animal(int id, String nome, String especie, int idade, double peso, String raca, String cor, String sexo, String proprietario) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
        this.cor = cor;
        this.sexo = sexo;
        this.proprietario = proprietario;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }
}

class SistemaVeterinario {
    private static List<Animal> listaAnimais = new ArrayList<>();
    private static int proximoId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void executar() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarAnimal();
                    break;
                case 2:
                    buscarAnimalPorId();
                    break;
                case 3:
                    alterarAnimal();
                    break;
                case 4:
                    excluirAnimal();
                    break;
                case 5:
                    listarAnimais();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("1 - Cadastrar animal");
        System.out.println("2 - Buscar animal pela ID");
        System.out.println("3 - Alterar animal");
        System.out.println("4 - Excluir animal");
        System.out.println("5 - Listar animais");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Opção inválida. Tente novamente.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void cadastrarAnimal() {
        System.out.print("Nome do animal: ");
        String nome = scanner.next();
        System.out.print("Espécie do animal: ");
        String especie = scanner.next();
        int idade = lerInteiro("Idade do animal");
        double peso = lerDouble("Peso do animal");
        scanner.nextLine(); // Consumindo a quebra de linha pendente
        System.out.print("Raça do animal: ");
        String raca = scanner.nextLine();
        System.out.print("Cor do animal: ");
        String cor = scanner.nextLine();
        System.out.print("Sexo do animal: ");
        String sexo = scanner.next();
        System.out.print("Proprietário do animal: ");
        String proprietario = scanner.next();

        Animal animal = new Animal(proximoId++, nome, especie, idade, peso, raca, cor, sexo, proprietario);
        listaAnimais.add(animal);
        System.out.println("Animal cadastrado com sucesso!");
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("Valor inválido. Informe um número inteiro.");
            System.out.print(mensagem + ": ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Valor inválido. Informe um número decimal.");
            System.out.print(mensagem + ": ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static void buscarAnimalPorId() {
        System.out.print("Digite a ID do animal: ");
        int id = lerInteiro("");

        for (Animal animal : listaAnimais) {
            if (animal.getId() == id) {
                exibirInformacoesAnimal(animal);
                return;
            }
        }
        System.out.println("Animal não encontrado.");
    }

    private static void alterarAnimal() {
        System.out.print("Digite a ID do animal que deseja alterar: ");
        int id = lerInteiro("");

        for (Animal animal : listaAnimais) {
            if (animal.getId() == id) {
                animal.setIdade(lerInteiro("Nova idade do animal"));
                animal.setPeso(lerDouble("Novo peso do animal"));
                scanner.nextLine(); // Consumir a quebra de linha pendente
                System.out.print("Nova raça do animal: ");
                animal.setRaca(scanner.nextLine());
                System.out.print("Nova cor do animal: ");
                animal.setCor(scanner.nextLine());
                System.out.print("Novo sexo do animal: ");
                animal.setSexo(scanner.next());
                System.out.print("Novo proprietário do animal: ");
                animal.setProprietario(scanner.next());
                System.out.println("Animal alterado com sucesso!");
                return;
            }
        }
        System.out.println("Animal não encontrado.");
    }

    private static void excluirAnimal() {
        System.out.print("Digite a ID do animal que deseja excluir: ");
        int id = lerInteiro("");

        for (int i = 0; i < listaAnimais.size(); i++) {
            if (listaAnimais.get(i).getId() == id) {
                listaAnimais.remove(i);
                System.out.println("Animal excluído com sucesso!");
                return;
            }
        }
        System.out.println("Animal não encontrado.");
    }

    private static void listarAnimais() {
        if (listaAnimais.isEmpty()) {
            System.out.println("Não há animais cadastrados.");
            return;
        }

        System.out.println("Animais cadastrados:");
        for (Animal animal : listaAnimais) {
            exibirInformacoesAnimal(animal);
        }
    }

    private static void exibirInformacoesAnimal(Animal animal) {
        System.out.println("ID: " + animal.getId());
        System.out.println("Nome: " + animal.getNome());
        System.out.println("Espécie: " + animal.getEspecie());
        System.out.println("Idade: " + animal.getIdade());
        System.out.println("Peso: " + animal.getPeso());
        System.out.println("Raça: " + animal.getRaca());
        System.out.println("Cor: " + animal.getCor());
        System.out.println("Sexo: " + animal.getSexo());
        System.out.println("Proprietário: " + animal.getProprietario());
    }
}
