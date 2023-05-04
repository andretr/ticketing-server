package com.ridebeep.bdipticketserver.utils;

import com.ridebeep.bdipticketserver.model.enums.TicketStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TicketStatusConverter implements AttributeConverter<TicketStatus, String> {

    @Override
    public String convertToDatabaseColumn(TicketStatus ticketStatus) {
        if (ticketStatus == null) {
            return null;
        }
        return ticketStatus.name();
    }

    @Override
    public TicketStatus convertToEntityAttribute(String ticketStatus) {
        if (ticketStatus == null) {
            return null;
        }

        return Stream.of(TicketStatus.values())
                .filter(c -> c.name().equals(ticketStatus))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
