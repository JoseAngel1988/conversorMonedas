package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "21dc4ae0a3d1948b3021cbb3"; // Reemplaza con tu clave de la API
        List<String> historial = new ArrayList<>();
        boolean continuar = true;

        System.out.println(" 💰💰💰 Bienvenido al mejor Conversor de Monedas 🪙🪙🪙");

        while (continuar) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Realizar una conversión");
            System.out.println("2. Ver historial de conversiones");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> realizarConversion(scanner, apiKey, historial);
                case 2 -> mostrarHistorial(historial);
                case 3 -> {
                    System.out.println("¡Gracias por usar el Conversor de Monedas! 🪙💸");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void realizarConversion(Scanner scanner, String apiKey, List<String> historial) {
        System.out.print("Seleccione la moneda base (por ejemplo, USD): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Seleccione la moneda objetivo (por ejemplo, EUR): ");
        String target = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad a convertir: ");
        double cantidad;
        try {
            cantidad = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            System.out.println("Cantidad inválida. Intente nuevamente.");
            scanner.nextLine(); // Limpiar buffer
            return;
        }

        // Construcción de la URL para la API
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + base + "/" + target;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            JsonElement jsonElement = new Gson().fromJson(responseBody, JsonElement.class);

            if (jsonElement.isJsonObject()) {
                JsonObject jsonResponse = jsonElement.getAsJsonObject();

                if (jsonResponse.has("conversion_rate")) {
                    double conversionRate = jsonResponse.get("conversion_rate").getAsDouble();
                    double resultado = cantidad * conversionRate;

                    System.out.printf("La tasa de cambio de %s a %s es: %.2f%n", base, target, conversionRate);
                    System.out.printf("%.2f %s equivalen a %.2f %s%n", cantidad, base, resultado, target);

                    // Guardar en historial
                    historial.add(String.format("%.2f %s -> %.2f %s (Tasa: %.2f)", cantidad, base, resultado, target, conversionRate));
                } else {
                    System.out.println("Error: la respuesta no contiene la tasa de conversión ('conversion_rate').");
                }
            } else {
                System.out.println("Error: la respuesta de la API no es un objeto JSON.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Hubo un error al conectarse a la API.");
        }
    }

    private static void mostrarHistorial(List<String> historial) {
        if (historial.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
        } else {
            System.out.println("Historial de conversiones:");
            for (String registro : historial) {
                System.out.println(" - " + registro);
            }
        }
    }
}
