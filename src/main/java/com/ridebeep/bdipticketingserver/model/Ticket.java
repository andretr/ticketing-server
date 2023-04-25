package com.ridebeep.bdipticketingserver.model;

import com.ridebeep.bdipticketingserver.model.enums.Status;
import com.ridebeep.bdipticketingserver.model.id.TicketRecipientId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


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
public class Ticket {

    @Id
    @Column(name="ticket_id", columnDefinition = "BINARY(16)")
    private UUID ticketId;

    @NotNull
    @Column(name = "category_id", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @NotNull
    @Column(name = "priority_id", columnDefinition = "BINARY(16)")
    private UUID priorityId;

    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "status")
    private Status status;

    @NotNull
    @Lob
    @Column(name = "event_data")
    private String eventData;

    @CreationTimestamp
    @Column(name="created", nullable = false, updatable = false)
    private Timestamp created;

    @UpdateTimestamp
    @Column(name="modified", nullable = false)
    private Timestamp modified;

    @Column(name="acknowledged")
    private Timestamp acknowledged;

    @Column(name="solved")
    private Timestamp solved;

    @Column(name="closed")
    private Timestamp closed;
}
