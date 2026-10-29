package pe.gob.pnp.criminalistica.equilibrat.controller.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(value == null ? null : value.toString());
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String valor = in.nextString();
        return valor == null ? null : LocalDateTime.parse(valor);
    }
}
