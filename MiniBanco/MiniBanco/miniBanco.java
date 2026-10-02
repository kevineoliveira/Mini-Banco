package MiniBanco;
 
// Projeto de estudo
//      Conceitos praticados:
//           Livro Think Java 2ed.
//              --> Variáveis e tipos primitivos;
//              --> Entrada de dados com Scanner;
//              --> Métodos void e com retorno;
//              --> Condicionais (if - else - else if);
//              --> Booleanos e validações;
//              --> Interações com for, do while e while;
//              --> Escopo de variáveis.
// @autor: Kevin Oliveira
// @version: 1.0
 
import java.util.Scanner;
 
public class miniBanco {
    // CONSTs:
    static final double SAQUE_LIMITE = 1000.00; // Valor limite de saque
    static final double TAXA_SAQUE = 0.02; // Taxa de saque de 2%
 
     static void exibirExtrato(String[] extrato, int totalLinhas){
        System.out.println("=== EXTRATO ===");
        if (totalLinhas == 0) {
            System.out.println("\nNenhuma transação realizada.");
        }else {
            for (int i = 0; i < totalLinhas; i++){
                System.out.println(" " + extrato[i]);
            }
        }
        System.out.println("===================================");
    }
    static int registrarTransacao(String[] extrato, int totalLinhas, String linha){
        extrato[totalLinhas] = linha;
        return totalLinhas + 1;
    }
    static double sacar(double saldo, double valor){
        return saldo - calcularTotalSaque(valor);
    }
    static double calcularTotalSaque(double valor){
        return valor + (valor * TAXA_SAQUE);
    }
    static boolean saldoSuficiente(double saldo, double valor){
        return saldo >= calcularTotalSaque(valor);
    }
    static boolean dentroDoLimite(double valor){
        return valor <= SAQUE_LIMITE;
    }
    static boolean valorValido(double valor){
        return valor > 0;
    }
    static double depositar(double saldo, double valor){
        return saldo + valor;
    }
    static void exibirSaldo(double saldo){
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }
    static void exibirMenu() {
        System.out.println("=== MINI BANCO ===");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Consultar saldo");
        System.out.println("4 - Ver extrato");
        System.out.println("0 - Sair");
        System.out.println("Digite uma das opções: ");
    }
 
    public static void main(String[] args) {
        // Entrada de dados
        Scanner input = new Scanner(System.in);
        String[] extrato = new String[50];
        int totalLinhas = 0;
        double saldo = 0.0; // saldo inicial sempre 0
        int opcao = -1; // opcao do menu
 
        // boas vindas
        System.out.print("Digite seu nome: ");
        String nome = input.nextLine();
 
       while (opcao != 0) {
            exibirMenu();
            opcao = input.nextInt();
 
            if (opcao == 1) {
                // FLUXO DEPOSITAR:
                // System.out.println("Depositar - Em breve");
                System.out.print("Valor a depositar: R$");
                double valor = input.nextDouble();
                if (!valorValido(valor)) {
                    System.out.println("Valor inválido! Deve ser maior que zero!");
                } else {
                    saldo = depositar(saldo, valor);
                    System.out.println("Deposito realizado com sucesso!");
                    exibirSaldo(saldo);
                    totalLinhas = registrarTransacao(extrato, totalLinhas, String.format("DEPÓSITO + R$ %.2f -> Saldo: R$ %.2f", valor, saldo));
                }
            } else if (opcao == 2) {
                // FLUXO SACAR
                // System.out.println("Sacar - Em breve");
                System.out.print("Valor a sacar: R$");
                double valorSaque = input.nextDouble();
 
                if (!valorValido(valorSaque)) {
                    System.out.println("Valor inválido!");
                } else if (!dentroDoLimite(valorSaque)) {
                    System.out.printf("Limite excedido. Máximo: R$%.2f%n", SAQUE_LIMITE);
                } else if (!saldoSuficiente(saldo, valorSaque)) {
                    System.out.printf("Saída insuficiênte. Necessário: R$%.2f%n", calcularTotalSaque(valorSaque));
                } else {
                    double taxa = valorSaque * TAXA_SAQUE;
                    saldo = sacar(saldo, valorSaque);
                    System.out.printf("Saque realizado. Taxa cobrada: R$%.2f%n", taxa);
                    exibirSaldo(saldo);
                    totalLinhas = registrarTransacao(extrato, totalLinhas, String.format("SAQUE -R$ %.2f -> Saldo: R$%.2f", valorSaque, saldo));
                }
            } else if (opcao == 3) {
                // System.out.println("Consultar saldo - Em breve");
                exibirSaldo(saldo);
            } else if (opcao == 4) {
                //System.out.println("Ver extrato - Em breve");
                exibirExtrato(extrato, totalLinhas);
            } else if (opcao == 0) {
                exibirExtrato(extrato, totalLinhas);
                System.out.println("Até logo " + nome + "!");

            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
        input.close();
    }
}
