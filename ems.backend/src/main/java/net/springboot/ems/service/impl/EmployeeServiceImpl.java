package net.springboot.ems.service.impl;

import lombok.AllArgsConstructor;
import net.springboot.ems.dto.EmployeeDto;
import net.springboot.ems.entity.Department;
import net.springboot.ems.entity.Employee;
import net.springboot.ems.exception.ResourceNotFoundException;
import net.springboot.ems.mapper.EmployeeMapper;
import net.springboot.ems.repository.DepartmentRepository;
import net.springboot.ems.repository.EmployeeRepository;
import net.springboot.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id" +  employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with the given id :" + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
        -> new ResourceNotFoundException("Employee does not exists with the given id :" + employeeId));

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with id" +  updatedEmployee.getDepartmentId()));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(department);
        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
                -> new ResourceNotFoundException("Employee does not exists with the given id :" + employeeId));

        employeeRepository.deleteById(employeeId);
    }


}
