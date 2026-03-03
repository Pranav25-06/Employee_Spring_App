package com.pranav.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public record EmployeeVO( Integer emp_id,String emp_name,String emp_city,String empType) {
	

}
