package br.com.base.shared.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
    private static final DateTimeFormatter FORMATTER_ZONE = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    private static final DateTimeFormatter FORMATTER_UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");


    @Override
    public void serialize(OffsetDateTime value,
                          JsonGenerator generator,
                          SerializerProvider provider)
            throws IOException {
        if (value == null) {
            throw new IOException("OffsetDateTime argument is null.");
        }

        var isUTC = value.getOffset().equals(ZoneOffset.UTC);
        if (isUTC) {
            generator.writeString(FORMATTER_UTC.format(value));
        } else {
            generator.writeString(FORMATTER_ZONE.format(value));
        }
    }
}
