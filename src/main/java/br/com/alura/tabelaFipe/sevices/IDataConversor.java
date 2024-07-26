package br.com.alura.tabelaFipe.sevices;

import java.util.List;

public interface IDataConversor {
    <T> T getData(String json, Class<T> tClass);

    <T> List<T> getList(String json, Class<T> tClass);
}
