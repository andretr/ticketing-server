package com.ridebeep.bdipticketserver.model;

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
@Table(name = "ticket_comments")
public class TicketComment {

    @Id
    @Column(name = "ticket_comments_id")
    private UUID commentId;

    @NotNull
    @Column(name = "ticket_id")
    private UUID ticketId;

    @NotNull
    @Column(name="comment", columnDefinition="TEXT")
    private String comment;

    @CreationTimestamp
    @Column(name="created", nullable = false, updatable = false)
    private Timestamp created;

    @UpdateTimestamp
    @Column(name="modified", nullable = false)
    private Timestamp modified;
}
