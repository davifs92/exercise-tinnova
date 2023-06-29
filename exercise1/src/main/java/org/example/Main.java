package org.example;

public class Main {
    public static void main(String[] args) {


        Integer totalEleitores = 1000;
        Integer validos = 800;
        Integer brancos = 150;
        Integer nulos = 50;

        Urna urna = new Urna (new CalcularVotosValidos());
        System.out.println("Porcentual de Votos válidos foi de: " + urna.calcularPorcentual(validos, totalEleitores) + "%");

        urna = new Urna (new CalcularVotosBrancos());
        System.out.println("Porcentual de Votos brancos foi de: " + urna.calcularPorcentual(brancos, totalEleitores) + "%");

        urna = new Urna (new CalcularVotosNulos());
        System.out.println("Porcentual de Votos nulos foi de: " + urna.calcularPorcentual(nulos, totalEleitores) + "%");



    }
}