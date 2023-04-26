package com.ridebeep.bdipticketingserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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
    private Timestamp created;

    @UpdateTimestamp
    @Column(name="modified", nullable = false)
    private Timestamp modified;

}
