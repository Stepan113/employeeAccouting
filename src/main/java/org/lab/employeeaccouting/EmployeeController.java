package org.lab.employeeaccouting;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    private final EmployeeService service;

    public EmployeeController(final EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(
            @Valid @RequestBody final EmployeeRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(
            @PathVariable final Long id,
            @Valid @RequestBody final EmployeeRequest request) {
        return service.update(request, id);
    }

    @GetMapping
    public Page<EmployeeResponse> getAll(
            @RequestParam(defaultValue = DEFAULT_PAGE)
            @Min(value = 0, message = "page must be >= 0") final int page,

            @RequestParam(defaultValue = DEFAULT_SIZE)
            @Min(value = 1, message = "size must be >= 1")
            @Max(value = 100, message = "size must be <= 100") final int size) {
        return service.getAll(page, size);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(
            @PathVariable final Long id) {
        return service.getById(id);
    }

    @GetMapping("/email/{email}")
    public EmployeeResponse getByEmail(
            @PathVariable final String email) {
        return service.getByEmail(email);
    }

    @GetMapping("/phone/{phoneNumber}")
    public EmployeeResponse getByPhoneNumber(
            @Phone @PathVariable final String phoneNumber) {
        return service.getByPhoneNumber(phoneNumber);
    }

    @GetMapping("/post/{type}")
    public Page<EmployeeResponse> getByPost(
            @PathVariable final PostType type,

            @RequestParam(defaultValue = DEFAULT_PAGE)
            @Min(value = 0, message = "page must be >= 0") final int page,

            @RequestParam(defaultValue = DEFAULT_SIZE)
            @Min(value = 1, message = "size must be >= 1")
            @Max(value = 100, message = "size must be <= 100") final int size) {
        return service.getByPost(type, page, size);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable final Long id) {
        service.delete(id);
    }
}