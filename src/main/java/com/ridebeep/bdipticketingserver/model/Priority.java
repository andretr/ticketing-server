package com.ridebeep.bdipticketingserver.model;

import com.ridebeep.bdipticketingserver.utils.TimeUnitConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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
    @Column(name = "priority_id")
    private UUID priorityId;

    @NotNull
    @Column(name="category_id")
    private UUID categoryId;

    @NotNull
    @Column(name = "priority_name")
    private String priorityName;

    @NotNull
    @Column(name = "sla_warning_threshold")
    private Integer slaWarningThreshold;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Convert(converter = TimeUnitConverter.class)
    @ColumnTransformer(write = "?::time_unit")
    @Column(name = "sla_warning_unit", columnDefinition = "time_unit")
    private TimeUnit slaWarningUnit;

     @NotNull
    @Column(name = "sla_overdue_threshold")
    private Integer slaOverdueThreshold;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Convert(converter = TimeUnitConverter.class)
    @ColumnTransformer(write = "?::time_unit")
    @Column(name = "sla_overdue_unit", columnDefinition = "time_unit")
    private TimeUnit slaOverdueUnit;

    @CreationTimestamp
    @Column(name="created", nullable = false, updatable = false)
    private Timestamp created;

    @UpdateTimestamp
    @Column(name="modified", nullable = false)
    private Timestamp modified;
}
