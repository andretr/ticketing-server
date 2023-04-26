package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, UUID> {
}