package com.nayg;

import com.nayg.Api.BuscaApi;
import com.nayg.Service.ConverterMoedaService;
import com.nayg.exception.ApiException;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String base = "";
        String rate = "";
        int option = 0;
        double value = 0.0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seja bem vindo(a) ao conversor de moedas :] ");

        try {
            while (option != 7) {

                exibirMenu();

                System.out.println("Escolha uma opção válida:");
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> {base = "ARS"; rate = "USD";}
                    case 2 -> {base = "USD"; rate = "BRL";}
                    case 3 -> {base = "EUR"; rate = "BRL";}
                    case 4 -> {base = "BRL"; rate = "USD";}
                    case 5 -> {base = "BRL"; rate = "EUR";}
                    case 6 -> {base = "ARS"; rate = "BRL";}
                    case 7 -> {break;}
                    default -> {System.out.println("Opção inválida! Tente novamente."); continue;}
                }

                System.out.println("Digite o valor que a ser convertido: ");
                value = scanner.nextDouble();

                String resultJson = BuscaApi.buscaApi(base);

                if (resultJson == null) {
                    System.out.println("Erro ao buscar dados da API. Tente novamente.");
                    continue;
                }

                Double convertido = ConverterMoedaService.converter(resultJson, rate, value);

                System.out.printf("Valor %.2f %s corresponde ao valor final de %.2f %s", value, base, convertido, rate);
            }
        }
        catch (ApiException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println(e.getMessage() + " -> Entrada inválida! Digite um número!");
        }
        finally {
            scanner.close();
        }
    }

    public static void exibirMenu() {
        System.out.println("""
        
        1) Peso argentino > Dólar
        2) Dólar > Real brasileiro
        3) Euro > Real brasileiro
        4) Real brasileiro > Dólar
        5) Real brasileiro > Euro
        6) Peso argentino > Real brasileiro
        7) Sair
        """);
    }
}