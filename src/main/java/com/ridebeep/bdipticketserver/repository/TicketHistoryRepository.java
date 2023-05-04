package com.ridebeep.bdipticketserver.repository;

import com.ridebeep.bdipticketserver.model.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, UUID> {
}