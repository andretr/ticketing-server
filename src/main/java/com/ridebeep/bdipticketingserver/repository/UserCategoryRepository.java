package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.UserCategory;
import com.ridebeep.bdipticketingserver.model.id.UserCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, UserCategoryId> {
}