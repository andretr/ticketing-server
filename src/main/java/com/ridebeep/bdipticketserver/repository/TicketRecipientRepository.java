package com.ridebeep.bdipticketserver.repository;

import com.ridebeep.bdipticketserver.model.TicketRecipient;
import com.ridebeep.bdipticketserver.model.id.TicketRecipientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRecipientRepository extends JpaRepository<TicketRecipient, TicketRecipientId> {
}