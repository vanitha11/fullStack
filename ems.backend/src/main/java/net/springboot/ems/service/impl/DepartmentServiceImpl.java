package net.springboot.ems.service.impl;

import lombok.AllArgsConstructor;
import net.springboot.ems.dto.DepartmentDto;
import net.springboot.ems.entity.Department;
import net.springboot.ems.exception.ResourceNotFoundException;
import net.springboot.ems.mapper.DepartmentMapper;
import net.springboot.ems.repository.DepartmentRepository;
import net.springboot.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.maptoDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.maptoDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exists with the given id :" + departmentId));

        return DepartmentMapper.maptoDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.maptoDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()
                -> new ResourceNotFoundException("Department does not exists with the given id :" + departmentId));

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());

        Department updatedDepartmentObj = departmentRepository.save(department);
        return DepartmentMapper.maptoDepartmentDto(updatedDepartmentObj);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()
                -> new ResourceNotFoundException("Department does not exists with the given id :" + departmentId));

        departmentRepository.deleteById(departmentId);
    }
}
