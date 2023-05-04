package com.ridebeep.bdipticketserver.utils;

import com.ridebeep.bdipticketserver.model.enums.TicketStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TicketStatusConverterTest {
    TicketStatusConverter ticketStatusConverter = new TicketStatusConverter();
    @Test
    void causeConverterTest() {
        assertNull(ticketStatusConverter.convertToEntityAttribute(null));
        assertEquals(TicketStatus.ACKNOWLEDGED, ticketStatusConverter.convertToEntityAttribute("ACKNOWLEDGED"));
    }
    @Test
    void causeConvertToDbColumnTest() {
        assertNull(ticketStatusConverter.convertToDatabaseColumn(null));
        assertEquals("ACKNOWLEDGED", ticketStatusConverter.convertToDatabaseColumn(TicketStatus.ACKNOWLEDGED));
    }
}
