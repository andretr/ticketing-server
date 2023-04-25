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
@Table(name = "tenants")
public class Tenant {

    @Id
    @Column(name = "tenant_id", columnDefinition = "BINARY(16)")
    private UUID tenantId;

    @NotNull
    @Column(name="tenant_code")
    private String tenantCode;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "hidden_tenant")
    private Boolean hidden;

}
