package org.lab.employeeaccouting;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fio;
    @Column(unique = true)
    private String email;
    @Column(unique = true, name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private PostType post;
    @Column(name = "reason_dismissal")
    private String reasonDismissal;
}
