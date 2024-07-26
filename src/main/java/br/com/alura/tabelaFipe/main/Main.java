package br.com.alura.tabelaFipe.main;

import br.com.alura.tabelaFipe.model.Data;
import br.com.alura.tabelaFipe.model.Models;
import br.com.alura.tabelaFipe.model.Vehicle;
import br.com.alura.tabelaFipe.sevices.ApiConsume;
import br.com.alura.tabelaFipe.sevices.DataConversor;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    Scanner input = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ApiConsume apiConsume = new ApiConsume();
    private DataConversor dataConversor = new DataConversor();

    public void showMenu () {

        var menu = """
                \n*** Digite uma das opções Abaixo:  ***
                 Carro
                 Moto
                 Caminhão 
                """;

        System.out.println(menu);
        var response = input.nextLine().toLowerCase();

        String address;

        if (response.contains("car")) {
            address = URL_BASE + "carros/marcas";
        } else if (response.contains("moto")) {
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "caminhoes/marcas";
        }

        var json = apiConsume.getData(address);

        var marcas = dataConversor.getList(json, Data.class);

        marcas.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Qual marca de carro deseja procurar? ");
        var code = input.nextLine();
        address = address + "/" + code + "/" + "modelos";

        json = apiConsume.getData(address);

        var listModel = dataConversor.getData(json, Models.class);

        System.out.println("\nModelos dessa marca: ");
        listModel.models().stream().sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("\nDigite um veiculo: ");
        var vehicleName = input.nextLine().toLowerCase();

        List<Data> filteredModels = listModel.models().stream()
                .filter(m -> m.name().toLowerCase().contains(vehicleName))
                .collect(Collectors.toList());

        System.out.println("Modelos filtrados: ");
        filteredModels.forEach(System.out::println);

        System.out.println("Digite o código do modelo: ");
        var vehicleCode = input.nextLine();

        address = address + "/" + vehicleCode + "/anos";
        json = apiConsume.getData(address);
        List<Data> years = dataConversor.getList(json, Data.class);

        List<Vehicle> vehiclesList = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var addressYears = address + "/" + years.get(i).code();
            json = apiConsume.getData(addressYears);
            Vehicle vehicle = dataConversor.getData(json, Vehicle.class);
            vehiclesList.add(vehicle);
        }

        System.out.println("\nTodos os Veiculos filtrados por ano: ");
        vehiclesList.forEach(System.out::println);
    }
}
