package org.lab.employeeaccouting;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository rep;
    private final EmployeeMapper mapper;

    public EmployeeService(
            final EmployeeRepository rep,
            final EmployeeMapper mapper) {
        this.rep = rep;
        this.mapper = mapper;
    }

    @Transactional
    public EmployeeResponse create(final EmployeeRequest request) {
        if (rep.findByEmail(request.getEmail()).isPresent()) {
            throw new EntityExistsException(
                    "Сотрудник с такой почтой существует!");
        }
        if (rep.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new EntityExistsException(
                    "Сотрудник с таким телефоном существует!");
        }
        final Employee employee = mapper.toEntity(request);
        return mapper.toDto(rep.save(employee));
    }

    @Transactional
    public EmployeeResponse update(final EmployeeRequest request,
                                   final Long id) {
        Employee employee = rep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Сотрудник с таким id не найден!"));
        employee = mapper.partitionalUpdate(request, employee);
        return mapper.toDto(employee);
    }

    public Page<EmployeeResponse> getAll(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return rep.findAll(pageable).map(mapper::toDto);
    }

    public EmployeeResponse getById(final Long id) {
        final Employee employee = rep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Сотрудник с таким id не найден!"));
        return mapper.toDto(employee);
    }

    public EmployeeResponse getByEmail(final String email) {
        final Employee employee = rep.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Сотрудник с таким email не найден!"));
        return mapper.toDto(employee);
    }

    public EmployeeResponse getByPhoneNumber(final String number) {
        final Employee employee = rep.findByPhoneNumber(number)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Сотрудник с таким телефоном не найден!"));
        return mapper.toDto(employee);
    }

    public Page<EmployeeResponse> getByPost(
            final PostType type,
            final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size);
        final Page<Employee> employees = rep.findByPost(type, pageable);
        return employees.map(mapper::toDto);
    }

    public void delete(final Long id) {
        if (!rep.existsById(id)) {
            throw new EntityNotFoundException(
                    "Сотрудник с таким id не найден!");
        }
        rep.deleteById(id);
    }
}
