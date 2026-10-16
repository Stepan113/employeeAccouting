package org.lab.employeeaccouting;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeRequest request);

    EmployeeResponse toDto(Employee employee);

    Employee partitionalUpdate(EmployeeRequest request, @MappingTarget Employee employee);
}
