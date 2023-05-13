package com.digi.model;

import com.digi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    @Column(name = "verification_code")
    private String verifyCode;
    @Enumerated(EnumType.STRING)
    private Status status;
}
