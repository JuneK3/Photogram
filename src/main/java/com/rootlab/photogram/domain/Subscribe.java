package com.rootlab.photogram.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "subscribe_unique_constraint",
                columnNames = {"fromUserId", "toUserId"}
        )
})
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fromUserId")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "toUserId")
    private User toUser;

    private LocalDateTime createdAt;
    
}
