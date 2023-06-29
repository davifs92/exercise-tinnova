package org.example;

import java.util.Optional;

public class CalcularVotosNulos implements CalcularVotos{
    @Override
    public Double calcularPorcetualDeVotos(Integer votos, Integer totalEleitores) {
        Optional<Integer> optVotosNulos = Optional.ofNullable(votos);
        Optional<Integer> optTotalEleitores = Optional.ofNullable(totalEleitores);

        if (optVotosNulos.isEmpty() || optTotalEleitores.isEmpty() || optTotalEleitores.get() == 0) {
            throw new IllegalArgumentException("Os valores de votosNulos e totalEleitores não podem ser nulos, e totalEleitores deve ser maior que zero.");
        }

        if (optVotosNulos.get() < 0 || optTotalEleitores.get() < 0) {
            throw new IllegalArgumentException("Os valores de votosNulos e totalEleitores não podem ser negativos");
        }

        double porcentualVotosNulos = (optVotosNulos.get().doubleValue() / optTotalEleitores.get().doubleValue()) * 100;
        return porcentualVotosNulos;    }
}
