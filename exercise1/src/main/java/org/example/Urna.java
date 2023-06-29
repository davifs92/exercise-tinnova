package org.example;

public class Urna {
    private CalcularVotos calcularVotos;
    public Urna(){};

    public Urna(CalcularVotos calcularVotos) {
        this.calcularVotos = calcularVotos;
    }

    public Double calcularPorcentual(Integer votos, Integer totalEleitores) {
        return this.calcularVotos.calcularPorcetualDeVotos(votos, totalEleitores);
    }

}
