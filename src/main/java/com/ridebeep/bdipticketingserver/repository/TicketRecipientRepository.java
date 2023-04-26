package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.TicketRecipient;
import com.ridebeep.bdipticketingserver.model.id.TicketRecipientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRecipientRepository extends JpaRepository<TicketRecipient, TicketRecipientId> {
}