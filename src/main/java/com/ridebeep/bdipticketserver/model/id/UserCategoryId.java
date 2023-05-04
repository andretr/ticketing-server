package com.ridebeep.bdipticketserver.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCategoryId implements Serializable {
    private UUID userId;
    private UUID categoryId;
}
