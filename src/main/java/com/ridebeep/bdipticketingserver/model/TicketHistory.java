package com.ridebeep.bdipticketingserver.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "ticket_history")
public class TicketHistory {

    @Id
    @Column(name = "ticket_history_id")
    private UUID ticketHistoryId;

    @NotNull
    @Column(name="ticket_id")
    private UUID ticketId;

    @NotNull
    @Column(name = "event_detail", columnDefinition="TEXT")
    private String eventDetail;

    @CreationTimestamp
    @Column(name="created", nullable = false, updatable = false)
    private Timestamp created;
}
