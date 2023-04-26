package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketCommentRepository extends JpaRepository<TicketComment, UUID> {
}