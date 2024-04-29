package org.example.servicios;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.excepciones.HttpRequestException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.TreeMap;

public class ConversorMonedaServicio {

    private static final String[] MONEDAS_DESEADAS_DE_LA_API = {"ARS", "BOB", "BRL", "CLP", "COP", "USD", "EUR", "GBP", "CNY"};
    private TreeMap<String, Double> conversionRatesMap = new TreeMap<>();

    public Double manager(String monedaInicial, String monedaFinal, Double cantidad) {
        System.out.println("Estamos procesando tu petición, por favor aguarde unos segundos...");

        String json;
        try {
            json = realizarSolicitudHttpRequest(monedaInicial);
        } catch (HttpRequestException e) {
            System.out.println("Hubo un error al obtener los datos de la API: " + e.getMessage());
            return null;
        }

        obtenerMonedasDeseadas(json);
        Double rateMonedaFinal = buscarValorDeLaMoneda(monedaFinal);
        return convertirMoneda(rateMonedaFinal, cantidad);
    }

    private Double buscarValorDeLaMoneda(String keyMoneda) {
        for (Map.Entry<String, Double> elemento : conversionRatesMap.entrySet()) {
            if (keyMoneda.equalsIgnoreCase(elemento.getKey())) {
                return elemento.getValue();
            }
        }
        return null;
    }

    private String realizarSolicitudHttpRequest(String monedaInicial) throws HttpRequestException {
        String direccion = "https://v6.exchangerate-api.com/v6/d87117438aec3c391bbea56e/latest/" + monedaInicial;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (InterruptedException e) {
            throw new HttpRequestException("La solicitud HTTP fue interrumpida.", e);
        } catch (IOException e) {
            throw new HttpRequestException("Error al realizar la solicitud HTTP.", e);
        }
    }

    private void obtenerMonedasDeseadas(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject conversionRatesObject = jsonObject.getAsJsonObject("conversion_rates");
        conversionRatesMap.clear(); // Limpiar el TreeMap antes de agregar nuevas tasas de conversión

        for (String codigoMoneda : conversionRatesObject.keySet()) {
            if (esPrimitivo(conversionRatesObject.get(codigoMoneda))) {
                agregarMonedaDeseada(conversionRatesObject, codigoMoneda);
            }
        }
    }

    private void agregarMonedaDeseada(JsonObject conversionRatesObject, String codigoMoneda) {
        for (String codigoDeseado : MONEDAS_DESEADAS_DE_LA_API) {
            if (codigoMoneda.equals(codigoDeseado)) {
                double tasaConversion = conversionRatesObject.get(codigoMoneda).getAsDouble();
                conversionRatesMap.put(codigoMoneda, tasaConversion);
            }
        }
    }

    //Metodo para confirmar que el json venga con valores corrctos
    private boolean esPrimitivo(JsonElement elemento) {
        return elemento.isJsonPrimitive();
    }

    private double convertirMoneda(Double rateMonedaFinal, Double cantidad) {
        return cantidad * rateMonedaFinal;
    }
}

