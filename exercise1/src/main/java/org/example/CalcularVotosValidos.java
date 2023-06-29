package org.example;

import java.util.Optional;

public class CalcularVotosValidos implements CalcularVotos {

   @Override
    public Double calcularPorcetualDeVotos(Integer votos, Integer totalEleitores) {
        Optional<Integer> optVotosTotais = Optional.ofNullable(votos);
        Optional<Integer> optTotalEleitores = Optional.ofNullable(totalEleitores);

        if (optVotosTotais.isEmpty() || optTotalEleitores.isEmpty() || optTotalEleitores.get() == 0) {
            throw new IllegalArgumentException("Os valores de votosTotais e totalEleitores não podem ser nulos e totalEleitores deve ser maior que zero.");
        }

        if (optVotosTotais.get() < 0 || optTotalEleitores.get() < 0) {
            throw new IllegalArgumentException("Os valores de votosTotais e totalEleitores não podem ser negativos");
        }

        double porcentualVotosValidos = (optVotosTotais.get().doubleValue() / optTotalEleitores.get().doubleValue()) * 100;
        return porcentualVotosValidos;    }
}
