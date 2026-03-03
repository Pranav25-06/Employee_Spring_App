package com.pranav.dao;

import java.util.List;
import java.util.Optional;

import com.pranav.entity.EmployeeEntity;
import com.pranav.model.EmployeeModel;

public interface EmployeeDao {

	AddEmployeeResponse addEmployees(List<EmployeeEntity> employeeEntitites);
	List<EmployeeEntity> getEmployeeByCityAndName(String city,String name);
	EmployeeEntity getEmployeeById(Integer id);
	List<EmployeeEntity> getEmployeeByName(String empName);
	List<EmployeeEntity> getEmployeeByCity(String empCity);
	String addEmployee(EmployeeEntity employeeEntity);
	EmployeeEntity deleteEmployee(Integer id);
	EmployeeEntity updateEmployee(Integer id, EmployeeEntity employeeEntity);
	List<EmployeeEntity> getEmployeeByType(String empType);
	List<EmployeeEntity> getEmployeeByCityAndType(String empCity, String empType);
	List<EmployeeEntity> getEmployeeByNameAndType(String empName, String empType);

}