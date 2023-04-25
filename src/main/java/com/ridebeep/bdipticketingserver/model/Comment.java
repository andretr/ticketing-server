package com.ridebeep.bdipticketingserver.model;

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
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "comment_id", columnDefinition = "BINARY(16)")
    private UUID commentId;

    @NotNull
    @Column(name = "ticket_id", columnDefinition = "BINARY(16)")
    private UUID ticketId;

    @NotNull
    @Lob
    @Column(name="comment")
    private String comment;

}
