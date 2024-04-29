package org.example.modelos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConversionTest {

    @Test
    void testConstructorYGetters() {
        String monedaInicial = "USD";
        String monedaFinal = "EUR";
        double cantidadConvertida = 100.0;
        double resultado = 85.0;

        Conversion conversion = new Conversion(monedaInicial, monedaFinal, cantidadConvertida, resultado);

        assertNotNull(conversion);
        assertEquals(monedaInicial, conversion.getMonedaInicial());
        assertEquals(monedaFinal, conversion.getMonedaFinal());
        assertEquals(cantidadConvertida, conversion.getCantidadConvertida());
        assertEquals(resultado, conversion.getResultado());
        assertNotNull(conversion.getFechaDeConversion());
    }

    @Test
    void testToString() {
        String monedaInicial = "USD";
        String monedaFinal = "EUR";
        double cantidadConvertida = 100.0;
        double resultado = 85.0;

        Conversion conversion = new Conversion(monedaInicial, monedaFinal, cantidadConvertida, resultado);

        String expectedString = "[monedaInicial='USD', monedaFinal='EUR', cantidadConvertida=100.0, resultado=85.0, fechaDeConversion=" + conversion.getFechaDeConversion() + "]";
        assertEquals(expectedString, conversion.toString());
    }
}
