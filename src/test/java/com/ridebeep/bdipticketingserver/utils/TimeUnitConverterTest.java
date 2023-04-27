package com.ridebeep.bdipticketingserver.utils;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TimeUnitConverterTest {
    TimeUnitConverter timeUnitConverter = new TimeUnitConverter();
    @Test
    void causeConverterTest() {
        assertNull(timeUnitConverter.convertToEntityAttribute(null));
        assertEquals(TimeUnit.DAYS, timeUnitConverter.convertToEntityAttribute("DAYS"));
    }
    @Test
    void causeConvertToDbColumnTest() {
        assertNull(timeUnitConverter.convertToDatabaseColumn(null));
        assertEquals("DAYS", timeUnitConverter.convertToDatabaseColumn(TimeUnit.DAYS));
    }
}
