package com.pranav.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pranav.entity.EmployeeEntity;
import com.pranav.model.EmployeeModel;
import com.pranav.repository.EmployeeRepository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public AddEmployeeResponse addEmployees(List<EmployeeEntity> employeeEntities) {

	    if (employeeEntities == null || employeeEntities.isEmpty()) {
	        return new AddEmployeeResponse(
	                "No employees provided",
	                null
	        );
	    }

	    Map<Integer, String> resultMap = new HashMap<>();

	    for (EmployeeEntity employee : employeeEntities) {

	        if (employeeRepository.existsById(employee.getEmpId())) {

	            resultMap.put(employee.getEmpId(),
	                    "Id Already Exists");

	        } else {

	            employeeRepository.save(employee);

	            resultMap.put(employee.getEmpId(),
	                    "Employee Added Successfully");
	        }
	    }

	    return new AddEmployeeResponse(
	            "Processing Completed",
	            resultMap
	    );
	}
	
	public List<EmployeeEntity> getEmployeeByCityAndName(String city,String name) {
		var list = employeeRepository.findByEmpCityAndEmpName(city, name);
		return list;
	}

	@Override
	public EmployeeEntity getEmployeeById(Integer id) {
		EmployeeEntity employeeEntity = employeeRepository.getById(id);
		return employeeEntity;
	}

	@Override
	public List<EmployeeEntity> getEmployeeByName(String empName) {
		List<EmployeeEntity> employeeEntities = employeeRepository.getByEmpName(empName);
		return employeeEntities;
	}

	@Override
	public List<EmployeeEntity> getEmployeeByCity(String empCity) {
		List<EmployeeEntity> employeeEntities = employeeRepository.getByEmpCity(empCity);
		return employeeEntities;
	}

	@Override
	public String addEmployee(EmployeeEntity employeeEntity) {
		String msg = null;
		if (employeeRepository.existsById(employeeEntity.getEmpId())) {

            return msg;

        } else {
        	employeeRepository.save(employeeEntity);
            return "Employee Added Successfully";
        }
	}

	@Override
	public EmployeeEntity deleteEmployee(Integer id) {
		if(employeeRepository.existsById(id)) {
			EmployeeEntity employeeEntity = employeeRepository.getById(id);
			employeeRepository.deleteById(id);
			return employeeEntity;
		}
		
		return null;
	}

	@Override
	public EmployeeEntity updateEmployee(Integer id,EmployeeEntity employeeEntity) {
		if(employeeRepository.existsById(id)) {
			
			employeeRepository.save(employeeEntity);
			return employeeEntity;
		}
		
		return null;
	}

	@Override
	public List<EmployeeEntity> getEmployeeByType(String empType) {
		List<EmployeeEntity> employeeEntities = employeeRepository.getByEmpType(empType);
		return employeeEntities;
	}

	@Override
	public List<EmployeeEntity> getEmployeeByCityAndType(String empCity, String empType) {
		List<EmployeeEntity> employeeEntities = employeeRepository.getByEmpCityAndEmpType(empCity,empType);
		return employeeEntities;
	}

	@Override
	public List<EmployeeEntity> getEmployeeByNameAndType(String empName, String empType) {
		List<EmployeeEntity> employeeEntities = employeeRepository.getByEmpNameAndEmpType(empName,empType);
		return employeeEntities;
	}
}
