package com.ridebeep.bdipticketingserver.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketRecipientId implements Serializable {

    private UUID ticketId;

    private UUID userId;
}
