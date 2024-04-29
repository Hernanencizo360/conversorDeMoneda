package org.example.servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.example.modelos.AdaptadorLocalDateTime;
import org.example.modelos.Conversion;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionServicio {
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new AdaptadorLocalDateTime())
            .create();

    public void start(String monedaInicial, String monedaFinal, double cantidadConvertida,  double resultado) {
        Conversion conversion = crearConversion(monedaInicial, monedaFinal, cantidadConvertida,resultado);
        String json = transformarConversionAJson(conversion);

        // Si no existe historial, crearlo y agregar la conversión
        if (!existeHistorial()) {
            //System.out.println("Creando historial...");
            guardarJsonEnArchivoLocal("[]"); // Crear historial vacío
        }

        // Leer el historial y agregar la nueva conversión
        String historialJson = leerHistorialDesdeArchivo();
        json = agregarConversionAJson(historialJson, conversion);

        // Guardar el historial actualizado
        guardarJsonEnArchivoLocal(json);
    }

    public void mostrarHistorial() {
        boolean existeHistorial = existeHistorial();
        if (existeHistorial) {
            Conversion[] conversiones = cargarHistorial();
            mostrarUltimasConversiones(conversiones);
        } else {
            System.out.println("--> Lo sentimos aún no existe historial de conversiones...");
            System.out.println("--> Realice una primer conversión...");
        }
        System.out.println("");
    }

    private Conversion[] cargarHistorial() {
        String historialJson = leerHistorialDesdeArchivo();
        // Convertir el JSON a un array de objetos Conversion
        return gson.fromJson(historialJson, Conversion[].class);
    }

    private void mostrarUltimasConversiones(Conversion[] conversiones) {
        System.out.println("Últimas conversiones en el sistema: ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-28s | %-28s | %-23s | %-23s | %-23s |\n", "Moneda Inicial", "Moneda Final", "Cantidad Convertida", "Resultado", "Fecha y Hora");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

        int inicio = Math.max(0, conversiones.length - 5); // Índice de inicio para mostrar las últimas 5 conversiones
        for (int i = inicio; i < conversiones.length; i++) {
            Conversion conversion = conversiones[i];
            String monedaInicial = conversion.getMonedaInicial();
            String monedaFinal = conversion.getMonedaFinal();
            double cantidadConvertida = conversion.getCantidadConvertida();
            double resultado = conversion.getResultado();
            LocalDateTime fechaDeConversion = conversion.getFechaDeConversion();
            String fechaHoraFormateada = fechaDeConversion.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            System.out.printf("| %-28s | %-28s | %-23s | %-23s | %-23s |\n", monedaInicial, monedaFinal, cantidadConvertida, resultado, fechaHoraFormateada);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private boolean existeHistorial() {
        File file = new File("historial.json");
        return file.exists();
    }

    private Conversion crearConversion(String monedaInicial, String monedaFinal, double cantidadConvertida,  double resultado) {
        return new Conversion(monedaInicial, monedaFinal, cantidadConvertida, resultado);
    }

    private String transformarConversionAJson(Conversion conversion) {
        return gson.toJson(conversion);
    }

    private String agregarConversionAJson(String historialJson, Conversion conversion) {

        JsonArray jsonArray;

        // Si el historialJson está vacío, inicializarlo con un array que contenga solo la nueva conversión
        if (historialJson.isEmpty()) {
            jsonArray = new JsonArray();
        } else {
            jsonArray = gson.fromJson(historialJson, JsonArray.class);
        }

        jsonArray.add(gson.toJsonTree(conversion));
        return jsonArray.toString();
    }

    private String leerHistorialDesdeArchivo() {
        File historialFile = new File("historial.json");

        try (BufferedReader reader = new BufferedReader(new FileReader(historialFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                stringBuilder.append(linea);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println("Error al leer el 'historial.json'.");
            e.getMessage();
            return "[]"; // Devolver un arreglo vacío en caso de error
        }
    }

    private void guardarJsonEnArchivoLocal(String json) {
        try (FileWriter writer = new FileWriter("historial.json")) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
            e.printStackTrace(System.out);
        }
    }
}
