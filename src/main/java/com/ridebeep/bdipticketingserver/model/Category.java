package com.ridebeep.bdipticketingserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id")
    private UUID categoryId;

    @NotNull
    @Column(name="tenant_id")
    private UUID tenantId;

    @Column(name="default_priority_id")
    private UUID defaultPriorityId;

    @NotNull
    @Column(name = "category_name", unique = true)
    private String categoryName;

    @CreationTimestamp
    @Column(name="created", nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name="modified", nullable = false)
    private LocalDateTime modified;

}
