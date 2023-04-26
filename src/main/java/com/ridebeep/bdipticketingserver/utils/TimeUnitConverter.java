package com.ridebeep.bdipticketingserver.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TimeUnitConverter implements AttributeConverter<TimeUnit, String> {

    @Override
    public String convertToDatabaseColumn(TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return timeUnit.name();
    }

    @Override
    public TimeUnit convertToEntityAttribute(String timeUnit) {
        if (timeUnit == null) {
            return null;
        }

        return Stream.of(TimeUnit.values())
                .filter(c -> c.name().equals(timeUnit))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
