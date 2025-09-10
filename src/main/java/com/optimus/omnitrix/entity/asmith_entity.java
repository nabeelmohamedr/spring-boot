package com.optimus.omnitrix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="users")
public class asmith_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @NotNull
    @NotBlank
    private String name;

    @Min(0)   // must be >= 0
    @Max(1000) // example limit
    //@Pattern(regexp ="^(0-9){10}$" ) only use in string
    private int power;

    @Email
    @Size(min = 5)
    private String email;

    private String username;
    private String password;

    public asmith_entity(long id, int power, String name, String username, String email, String password) {
        this.id = id;
        this.power = power;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public asmith_entity() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
