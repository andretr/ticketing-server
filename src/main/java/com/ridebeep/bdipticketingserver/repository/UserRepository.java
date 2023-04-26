package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}