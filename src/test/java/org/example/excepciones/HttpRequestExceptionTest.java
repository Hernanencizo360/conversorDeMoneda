package org.example.excepciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HttpRequestExceptionTest {

    @Test
    void testConstructorConMensaje() {
        String mensajeError = "Error ocurrió durante la solicitud HTTP.";

        HttpRequestException excepcion = new HttpRequestException(mensajeError);

        assertEquals(mensajeError, excepcion.getMessage());
        assertNull(excepcion.getCause());
    }

    @Test
    void testConstructorConMensajeYCausa() {
        String mensajeError = "Error ocurrió durante la solicitud HTTP.";
        Throwable causa = new RuntimeException("Tiempo de conexión agotado.");

        HttpRequestException excepcion = new HttpRequestException(mensajeError, causa);

        assertEquals(mensajeError, excepcion.getMessage());
        assertEquals(causa, excepcion.getCause());
    }
}
