package org.example.modelos;

import com.google.gson.annotations.Expose;
import java.time.LocalDateTime;

public class Conversion {
    @Expose
    private String monedaInicial;
    @Expose
    private String monedaFinal;
    @Expose
    private double cantidadConvertida;
    @Expose
    private double resultado;
    @Expose
    private LocalDateTime fechaDeConversion;

    // Constructor
    public Conversion(String monedaInicial, String monedaFinal, double cantidadConvertida, double resultado) {
        this.monedaInicial = monedaInicial;
        this.monedaFinal = monedaFinal;
        this.cantidadConvertida = cantidadConvertida;
        this.resultado = resultado;
        this.fechaDeConversion = LocalDateTime.now();
    }

    public String getMonedaInicial() {
        return monedaInicial;
    }

    public String getMonedaFinal() {
        return monedaFinal;
    }

    public double getCantidadConvertida() {
        return cantidadConvertida;
    }

    public LocalDateTime getFechaDeConversion() {
        return fechaDeConversion;
    }

    public double getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "[" +
                "monedaInicial='" + monedaInicial + '\'' +
                ", monedaFinal='" + monedaFinal + '\'' +
                ", cantidadConvertida=" + cantidadConvertida +
                ", resultado=" + resultado +
                ", fechaDeConversion=" + fechaDeConversion +
                ']';
    }
}

