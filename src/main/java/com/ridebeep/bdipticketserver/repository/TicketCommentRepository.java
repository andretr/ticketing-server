package com.ridebeep.bdipticketserver.repository;

import com.ridebeep.bdipticketserver.model.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketCommentRepository extends JpaRepository<TicketComment, UUID> {
}