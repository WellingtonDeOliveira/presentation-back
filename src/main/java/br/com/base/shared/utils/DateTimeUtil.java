package br.com.base.shared.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@SuppressWarnings("unused")
public class DateTimeUtil {

    public static OffsetDateTime nowZoneUTC() {
        return nowZone("UTC");
    }

    public static OffsetDateTime nowZoneLocal() {
        return OffsetDateTime.now();
    }

    public static OffsetDateTime nowZone(String zoneId) {
        return nowZone(ZoneId.of(zoneId));
    }

    public static OffsetDateTime nowZone(ZoneId zoneId) {
        return OffsetDateTime.now(zoneId);
    }
}
