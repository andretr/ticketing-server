package com.ridebeep.bdipticketingserver.model;

import com.ridebeep.bdipticketingserver.model.id.UserCategoryId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@IdClass(UserCategoryId.class)
@Table(name = "user_categories")
public class UserCategory {

        @Id
        @Column(name = "category_id", columnDefinition = "BINARY(16)")
        private UUID categoryId;

        @Id
        @Column(name="user_id", columnDefinition = "BINARY(16)")
        private UUID userId;

}
