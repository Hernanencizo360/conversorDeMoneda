package org.example.controladores;

import org.example.servicios.ConversionServicio;
import org.example.servicios.ConversorMonedaServicio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConversorMonedaController {

    private final Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private double cantidadDeDineroAconvertir;
    private String monedaInicial;
    private String monedaFinal;
    private final String[] monedas = {"Peso Argentino (ARS)", "Boliviano (BOB)", "Real Brasileño (BRL)",
            "Peso Chileno (CLP)", "Peso Colombiano (COP)", "Dólar Estadounidense (USD)", "Euro (EUR)",
            "Libra esterlina (GBP)", "Yuan chino (CNY)"};
    private ConversionServicio conversionServicio = new ConversionServicio();

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenuMonedas();
            int eleccionMonedaInicial = pedirOpcion(0, 10);

            switch (eleccionMonedaInicial) {
                case 1, 2, 3, 4, 5, 6, 7, 8:
                    monedaInicial = monedas[eleccionMonedaInicial - 1];
                    cantidadDeDineroAconvertir = pedirCantidadAConvertir();
                    String[] monedasFiltradas = obtenerMonedasFiltradas();
                    mostrarMenuMonedasFiltrado(monedasFiltradas);
                    monedaFinal = obtenerMonedaFinal(monedasFiltradas);
                    System.out.println("\033[32mEstás convirtiendo la cantidad de " + cantidadDeDineroAconvertir + " " + monedaInicial + " a " + monedaFinal + "\033[39;49m");
                    System.out.println("");

                    var conversorMonedaServicio = new ConversorMonedaServicio();
                    double resultado = conversorMonedaServicio.manager(obtenerCodigo(monedaInicial), obtenerCodigo(monedaFinal), cantidadDeDineroAconvertir);
                    imprimirResultado(resultado);
                    conversionServicio.start(monedaInicial, monedaFinal, cantidadDeDineroAconvertir, resultado);
                    break;
                case 9:
                    conversionServicio.mostrarHistorial();
                    break;
                case 0:
                    System.out.println("Gracias por utilizar nuestro servicio, vuelva pronto...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void imprimirResultado(double resultado) {
        System.out.printf("""
                \033[92m
                ******************************|TICKET DE RESULTADO|****************************
                *  %.2f %s EQUIVALEN A %.2f %s 
                ******************************|TICKET DE RESULTADO|****************************\033[39;49m
                """, cantidadDeDineroAconvertir, monedaInicial, resultado, monedaFinal);
        System.out.println("");
    }

    private void mostrarMenuMonedas() {
        System.out.print("""
                \033[32m
                ********************************||*******************************
                *                  BIENVENIDO AL CONVERSOR DE MONEDAS           *
                *               Seleccione la moneda que desea convertir        *
                *****************************************************************
                * 1) Peso Argentino (ARS).    2) Boliviano (BOB).               *
                * 3) Real Brasileño (BRL).    4) Peso Chileno (CLP).            *
                * 5) Peso Colombiano (COP).   6) Dólar Estadounidense (USD).    *
                * 7) Euro (EUR).              8) Libra esterlina (GBP).         *
                * 8) Yuan chino (CNY).        9) Historial ultimas conversiones *
                * 0) Salir.                                                     *
                *****************************************************************\033[39;49m
                """);

    }

    private int pedirOpcion(int minimo, int maximo) {
        int opcion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("--> Ingrese su opción: ");
                opcion = leer.nextInt();
                if (opcion >= minimo && opcion <= maximo) {
                    entradaValida = true;
                } else {
                    System.out.println("\033[93mOpción fuera de rango. Intente de nuevo. \033[39;49m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31mError: ingrese un número entero válido.\033[39;49m");
                leer.nextLine(); // Limpiar el buffer del scanner
            }
        }
        return opcion;
    }

    private double pedirCantidadAConvertir() {
        boolean salir = false;
        double cantidad = 0;
        do {
            try {
                System.out.println("Ingrese la cantidad que desea convertir:");
                cantidad = leer.nextDouble();
                salir = true;
            } catch (InputMismatchException e) {
                System.out.println("\033[31mError: ingrese un número válido. \033[92mEjemplo -> 2 o 2,5 o 20,530 etc...\033[39;49m");
                leer.nextLine(); // Limpiar el buffer del scanner
            }
        } while (!salir);
        return cantidad;
    }

    private String[] obtenerMonedasFiltradas() {
        List<String> monedasFiltradas = new ArrayList<>();
        for (String moneda : monedas) {
            if (!moneda.equals(monedaInicial)) {
                monedasFiltradas.add(moneda);
            }
        }
        return monedasFiltradas.toArray(new String[0]);
    }

    private void mostrarMenuMonedasFiltrado(String[] monedasFiltradas) {
        System.out.println("Seleccione la moneda a la que desea convertir:");
        for (int i = 0; i < monedasFiltradas.length; i++) {
            System.out.println((i + 1) + ") " + monedasFiltradas[i]);
        }
        System.out.println("");
    }

    private String obtenerMonedaFinal(String[] monedasFiltradas) {
        int eleccion;
        String monedaSeleccionada;
        do {
            eleccion = pedirOpcion(1, monedasFiltradas.length);
            int indice = eleccion - 1;
            monedaSeleccionada = monedasFiltradas[indice];
            if (monedaSeleccionada.equals(monedaInicial)) {
                System.out.println("No puedes seleccionar la misma moneda inicial. Por favor, elige otra moneda:");
            }
        } while (monedaSeleccionada.equals(monedaInicial));
        return monedaSeleccionada;
    }

    private String obtenerCodigo(String nombreMoneda) {
        String codigoCorrespondiente = null;
        //Buscar los indices de los parentesis:
        int indiceInicio = nombreMoneda.indexOf("(");
        int indiceFinal = nombreMoneda.indexOf(")");
        if (indiceInicio != -1 && indiceFinal != -1) {
            codigoCorrespondiente = nombreMoneda.substring(indiceInicio + 1, indiceFinal);
        } else {
            System.out.println("Error al encontrar el codigo. Ejemplo de Formato: Peso Argentino (ARG)");
        }
        return codigoCorrespondiente;
    }
}




