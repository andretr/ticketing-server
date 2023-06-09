package com.ridebeep.bdipticketserver.model;

import com.ridebeep.bdipticketserver.model.enums.TicketStatus;
import com.ridebeep.bdipticketserver.utils.TicketStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;
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
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name="ticket_id")
    private UUID ticketId;

    @NotNull
    @Column(name = "category_id")
    private UUID categoryId;

    @NotNull
    @Column(name = "priority_id")
    private UUID priorityId;

    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Convert(converter = TicketStatusConverter.class)
    @ColumnTransformer(write = "?::ticket_status")
    @Column(name = "status", columnDefinition = "ticket_status")
    private TicketStatus status;

    @NotNull
    @Column(name = "event_data", columnDefinition="TEXT")
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
