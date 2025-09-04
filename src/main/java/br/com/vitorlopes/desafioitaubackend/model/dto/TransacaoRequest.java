package br.com.vitorlopes.desafioitaubackend.model.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;

public class TransacaoRequest {
    @NotNull
    private double valor;

    @NotNull
    private OffsetDateTime dataHora;

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
