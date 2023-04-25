package com.ridebeep.bdipticketingserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @NotNull
    @Column(name="tenant_id", columnDefinition = "BINARY(16)")
    private UUID tenantId;

    @NotNull
    @Column(name="default_priority_id", columnDefinition = "BINARY(16)")
    private UUID defaultPriorityId;

    @NotNull
    @Column(name = "category_name", unique = true)
    private String categoryName;

}
