package com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "username" }),
                @UniqueConstraint(columnNames = { "email" })
})
@Getter
@Setter
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String username;
        private String email;
        private String password;
        @Column(columnDefinition = "TINYINT(1) default 0", nullable = false)
        private Boolean isAdmin = false;

}
