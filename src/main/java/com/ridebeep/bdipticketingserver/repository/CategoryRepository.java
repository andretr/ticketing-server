package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}