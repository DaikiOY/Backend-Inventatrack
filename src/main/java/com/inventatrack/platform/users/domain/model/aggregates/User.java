package com.inventatrack.platform.users.domain.model.aggregates;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private String phone;
    private String address;
    private String role;
    private String url;
    private OffsetDateTime createdAt;

    public User(String username, String email, String fullName, String password,
                String phone, String address, String role, String url, OffsetDateTime createdAt) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.url = url;
        this.createdAt = createdAt;
    }

    public User() {}

    // Getters 
    public Long getId() { return id; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getFullName() { return fullName; }

    public String getPassword() { return password; }

    public String getPhone() { return phone; }

    public String getAddress() { return address; }

    public String getRole() { return role; }

    public String getUrl() { return url; }

    public OffsetDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setEmail(String email) { this.email = email; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setPassword(String password) { this.password = password; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setAddress(String address) { this.address = address; }

    public void setRole(String role) { this.role = role; }

    public void setUrl(String url) { this.url = url; }

    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
