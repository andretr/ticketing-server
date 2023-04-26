package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}