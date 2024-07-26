package br.com.alura.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Vehicle(@JsonAlias("TipoVeiculo") String type,
                      @JsonAlias("Valor") String value,
                      @JsonAlias("Marca") String brand ,
                      @JsonAlias("Modelo") String model,
                      @JsonAlias("AnoModelo") int year,
                      @JsonAlias("Combustivel") String fuel,
                      @JsonAlias("CodigoFipe") String fipeCode,
                      @JsonAlias("MesReferencia") String referenceMonth,
                      @JsonAlias("SiglaCombustivel") String fuelAcronym) {
}
