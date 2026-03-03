package com.pranav.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
	private Integer empId;
	private String empName;
	private String empCity;
	private String empType;
	
	

}
