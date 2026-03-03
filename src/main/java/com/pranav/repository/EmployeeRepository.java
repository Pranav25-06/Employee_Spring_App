package com.pranav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{
	
	List<EmployeeEntity> findByEmpCityAndEmpName(String empCity,String empName);

	List<EmployeeEntity> getByEmpName(String empName);

	List<EmployeeEntity> getByEmpCity(String empCity);

	List<EmployeeEntity> getByEmpType(String empType);

	List<EmployeeEntity> getByEmpCityAndEmpType(String empCity, String empType);

	List<EmployeeEntity> getByEmpNameAndEmpType(String empName, String empType);
}
