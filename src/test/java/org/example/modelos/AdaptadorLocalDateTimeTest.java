package org.example.modelos;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class AdaptadorLocalDateTimeTest {

    private final AdaptadorLocalDateTime adaptador = new AdaptadorLocalDateTime();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Test
    void testSerialize() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 4, 29, 12, 0, 0);

        JsonElement jsonElement = adaptador.serialize(dateTime, null, null);

        assertNotNull(jsonElement);
        assertEquals(new JsonPrimitive(formatter.format(dateTime)), jsonElement);
    }

    @Test
    void testDeserialize() {
        String jsonString = "29-04-2024 12:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(jsonString, formatter);

        assertNotNull(dateTime);
        assertEquals(LocalDateTime.of(2024, 4, 29, 12, 0, 0), dateTime);
    }
}
