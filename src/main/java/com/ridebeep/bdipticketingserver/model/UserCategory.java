package com.ridebeep.bdipticketingserver.model;

import com.ridebeep.bdipticketingserver.model.id.UserCategoryId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

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
        @Column(name = "category_id")
        private UUID categoryId;

        @Id
        @Column(name="user_id")
        private UUID userId;

}
