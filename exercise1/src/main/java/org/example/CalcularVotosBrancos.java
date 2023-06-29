package org.example;

import java.util.Optional;

public class CalcularVotosBrancos implements CalcularVotos{
    @Override
    public Double calcularPorcetualDeVotos(Integer votos, Integer totalEleitores) {
        Optional<Integer> optVotosBrancos = Optional.ofNullable(votos);
        Optional<Integer> optTotalEleitores = Optional.ofNullable(totalEleitores);

        if (optVotosBrancos.isEmpty() || optTotalEleitores.isEmpty() || optTotalEleitores.get() == 0) {
            throw new IllegalArgumentException("Os valores de votosBrancos e totalEleitores devem ser não nulos, e totalEleitores deve ser diferente de zero.");
        }

        if (optVotosBrancos.get() < 0 || optTotalEleitores.get() < 0) {
            throw new IllegalArgumentException("Os valores de votosBrancos e totalEleitores devem ser não negativos.");
        }

        double porcentualVotosBrancos = (optVotosBrancos.get().doubleValue() / optTotalEleitores.get().doubleValue()) * 100;
        return porcentualVotosBrancos;    }
}
