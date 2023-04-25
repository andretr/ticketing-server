package com.ridebeep.bdipticketingserver.model;


import com.ridebeep.bdipticketingserver.model.id.TicketRecipientId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "ticket_history")
public class TicketHistory {

    @Id
    @Column(name = "ticket_history_id", columnDefinition = "BINARY(16)")
    private UUID ticketHistoryId;

    @NotNull
    @Column(name="ticket_id", columnDefinition = "BINARY(16)")
    private UUID ticketId;

    @NotNull
    @Lob
    @Column(name = "event_detail")
    private String eventDetail;
}
