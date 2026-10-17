package org.lab.employeeaccouting;

import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String fio;
    private String email;
    private String phoneNumber;
    private PostType post;
    private String reasonDismissal;
}
