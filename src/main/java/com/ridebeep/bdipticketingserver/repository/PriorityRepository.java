package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriorityRepository extends JpaRepository<Priority, UUID> {
}