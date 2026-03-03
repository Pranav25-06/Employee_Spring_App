package com.pranav.service;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.type.TypeVariableToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranav.dao.EmployeeDao;
import com.pranav.entity.EmployeeEntity;
import com.pranav.model.EmployeeModel;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Override
	public Map<Integer,String> addEmployees(EmployeeModel... employeeModels) {
		List<EmployeeEntity> l =  Arrays.stream(employeeModels)
				.map(model-> modelMapper.map(model, EmployeeEntity.class))
				.toList();
		var x = employeeDao.addEmployees(l);
		return x.results();
	}
	
	@Override
	public EmployeeModel getEmployeeById(Integer id) {
		EmployeeEntity employeeEntity = employeeDao.getEmployeeById(id);
		EmployeeModel employeeModel = modelMapper.map(employeeEntity, EmployeeModel.class);
		return employeeModel;
	}
	
	public List<EmployeeModel> getEmployeeByCityAndName(String city,String name) {
		List<EmployeeEntity> employeeEntities = employeeDao.getEmployeeByCityAndName(city, name);
		System.out.println(employeeEntities);
		List<EmployeeModel> list =  employeeEntities.stream()
				.map(entity-> modelMapper.map(entity, EmployeeModel.class))
				.toList();
		System.out.println(list);
		return list;
	}

	@Override
	public List<EmployeeModel> getEmployeeByName(String empName) {
		List<EmployeeEntity> employeeEntities = employeeDao.getEmployeeByName(empName);
		System.out.println(employeeEntities);
		List<EmployeeModel> list =  employeeEntities.stream()
				.map(entity-> modelMapper.map(entity, EmployeeModel.class))
				.toList();
		System.out.println(list);
		return list;
	}

	@Override
	public List<EmployeeModel> getEmployeeByCity(String empCity) {
		List<EmployeeEntity> employeeEntities = employeeDao.getEmployeeByCity(empCity);
		System.out.println(employeeEntities);
		List<EmployeeModel> list =  employeeEntities.stream()
				.map(entity-> modelMapper.map(entity, EmployeeModel.class))
				.toList();
		System.out.println(list);
		return list;
	}

	@Override
	public String addEmployee(EmployeeModel employeeModel) {
		EmployeeEntity employeeEntity = modelMapper.map(employeeModel, EmployeeEntity.class);
		String msg = employeeDao.addEmployee(employeeEntity);
		return msg;
	}

	@Override
	public EmployeeModel deleteEmployee(Integer id) {
		
		EmployeeEntity employeeEntity = employeeDao.deleteEmployee(id);
		if(employeeEntity == null) {
			return null;
		}
		EmployeeModel employeeModel = modelMapper.map(employeeEntity, EmployeeModel.class);
		return employeeModel;
	}

	@Override
	public EmployeeModel updateEmployee(Integer id,EmployeeModel employeeModel) {
		EmployeeEntity employeeEntity1 = modelMapper.map(employeeModel, EmployeeEntity.class);
		EmployeeEntity employeeEntity = employeeDao.updateEmployee(id,employeeEntity1);
		if(employeeEntity == null) {
			return null;
		}
		EmployeeModel employeeModel1 = modelMapper.map(employeeEntity, EmployeeModel.class);
		return employeeModel1;
	}

	@Override
	public List<EmployeeModel> getEmployeeByType(String empType) {
		List<EmployeeEntity> employeeEntities = employeeDao.getEmployeeByType(empType);
		if(employeeEntities == null) return null;
		System.out.println(employeeEntities);
		List<EmployeeModel> list =  employeeEntities.stream()
				.map(entity-> modelMapper.map(entity, EmployeeModel.class))
				.toList();
		System.out.println(list);
		return list;
	}

	@Override
	public List<EmployeeModel> getEmployeeByCityAndType(String empCity, String empType) {
		List<EmployeeEntity> employeeEntities = employeeDao.getEmployeeByCityAndType(empCity,empType);
		if(employeeEntities == null) return null;
		System.out.println(employeeEntities);
		List<EmployeeModel> list =  employeeEntities.stream()
				.map(entity-> modelMapper.map(entity, EmployeeModel.class))
				.toList();
		System.out.println(list);
		return list;
	}

	@Override
	public List<EmployeeModel> getEmployeeByNameAndType(String empName, String empType) {
		List<EmployeeEntity> employeeEntities = employeeDao.getEmployeeByNameAndType(empName,empType);
		if(employeeEntities == null) return null;
		System.out.println(employeeEntities);
		List<EmployeeModel> list =  employeeEntities.stream()
				.map(entity-> modelMapper.map(entity, EmployeeModel.class))
				.toList();
		System.out.println(list);
		return list;
	}
}
