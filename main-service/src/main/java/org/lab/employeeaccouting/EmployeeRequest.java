package org.lab.employeeaccouting;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank(message = "ФИО не может быть пустым")
    private String fio;
    @Email(message = "Некорректный email")
    @NotBlank(message = "Почта не может быть пуста")
    private String email;
    @NotBlank(message = "Телефон не может быть пустым")
    @Phone
    private String phoneNumber;
    @NotNull(message = "Должность не может быть пустой")
    private PostType post;
}
