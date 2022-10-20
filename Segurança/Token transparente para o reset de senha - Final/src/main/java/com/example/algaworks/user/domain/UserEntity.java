package com.example.algaworks.user.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Type type;

    @CreatedDate
    private OffsetDateTime createdAt;

    public enum Type {
        ADMIN, CUSTOMER
    }
}
