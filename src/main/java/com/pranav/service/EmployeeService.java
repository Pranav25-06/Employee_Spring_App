package com.pranav.service;

import java.util.List;
import java.util.Map;

import com.pranav.model.EmployeeModel;

public interface EmployeeService {

	Map<Integer, String> addEmployees(EmployeeModel... employeeModels);

	EmployeeModel getEmployeeById(Integer id);
	
	List<EmployeeModel> getEmployeeByCityAndName(String city,String name);

	List<EmployeeModel> getEmployeeByName(String empName);

	List<EmployeeModel> getEmployeeByCity(String empCity);

	String addEmployee(EmployeeModel employeeModel);

	EmployeeModel deleteEmployee(Integer id);

	EmployeeModel updateEmployee(Integer id, EmployeeModel employeeModel);

	List<EmployeeModel> getEmployeeByType(String empType);

	List<EmployeeModel> getEmployeeByCityAndType(String empCity, String empType);

	List<EmployeeModel> getEmployeeByNameAndType(String empName, String empType);

}