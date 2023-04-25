package com.ridebeep.bdipticketingserver.model;

import com.ridebeep.bdipticketingserver.model.id.TicketRecipientId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@IdClass(TicketRecipientId.class)
@Table(name = "ticket_recipients")
public class TicketRecipient {

    @Id
    @Column(name = "ticket_id", columnDefinition = "BINARY(16)")
    private UUID ticketId;

    @Id
    @Column(name="user_id", columnDefinition = "BINARY(16)")
    private UUID userId;
}
