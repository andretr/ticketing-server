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
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "priorities")
public class Priority {

    @Id
    @Column(name = "priority_id", columnDefinition = "BINARY(16)")
    private UUID priorityId;

    @NotNull
    @Column(name="category_id", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @NotNull
    @Column(name = "priority_name")
    private String priorityName;

    @NotNull
    @Column(name = "sla_warning_threshold")
    private Integer slaWarningThreshold;

    @NotNull
    @Column(name = "sla_warning_unit")
    private TimeUnit slaWarningUnit;

     @NotNull
    @Column(name = "sla_overdue_threshold")
    private Integer slaOverdueThreshold;

    @NotNull
    @Column(name = "sla_overdue_unit")
    private TimeUnit slaOverdueUnit;
}
