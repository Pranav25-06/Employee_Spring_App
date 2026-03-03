package com.pranav.rest.api;



import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.model.EmployeeModel;
import com.pranav.model.EmployeeVO;
import com.pranav.service.EmployeeService;

import jakarta.websocket.server.PathParam;

//controller with response body

@RestController
@RequestMapping("emp")
public class EmployeeApi {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeApi.class);
	
	@Autowired
	private EmployeeService employeeService;
	

	@PostMapping(value = "/addall",consumes = "application/json",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> addAllEmployees(@RequestBody EmployeeModel ...employeeModels) {
		System.out.println(employeeModels[0]);
		var msgMap = employeeService.addEmployees(employeeModels);
		if(msgMap.isEmpty()) {
			logger.error("NO employee Added");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
	                .body(Map.of("message", "Employee Not added"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(msgMap);
	}
	
	@PostMapping(value = "/add",consumes = "application/json",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeModel employeeModel) {

		
		var msg = employeeService.addEmployee(employeeModel);
		if(msg == null) {
			logger.error("NO employee Added");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
	                .body(Map.of("message", "Employee Not added"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@GetMapping(value = "/getbyid/{id}", 
		    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer empId){
		
		var employeeModel = employeeService.getEmployeeById(empId);
		if (employeeModel == null) {
			logger.error("Employee Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employee found with id : " + empId));
	    }
		EmployeeVO employeeVO = new EmployeeVO(
	            employeeModel.getEmpId(),
	            employeeModel.getEmpName(),
	            employeeModel.getEmpCity(),
	            employeeModel.getEmpType()
	    );
		return ResponseEntity.status(HttpStatus.OK).body(employeeVO);
	}
	
	@GetMapping(value = "getbynameandcity/{name}/{city}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getEmployeeByNameAndCity(@PathVariable("name") String empName,@PathVariable("city") String empCity){
		
		var models = employeeService.getEmployeeByCityAndName(empCity, empName);
		
		if (models.isEmpty()){
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employees found"));
	    }
		
		List<EmployeeVO> voList = models.stream()
	            .map(model -> new EmployeeVO(
	                    model.getEmpId(),
	                    model.getEmpName(),
	                    model.getEmpCity(),
	                    model.getEmpType()
	            ))
	            .toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(voList);
	}
	
	@GetMapping(value = "getbyname/{name}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getEmployeeByName(@PathVariable("name") String empName){
		
		var models = employeeService.getEmployeeByName(empName);
		
		if (models.isEmpty()){
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employees found with name : " + empName));
	    }
		
		List<EmployeeVO> voList = models.stream()
	            .map(model -> new EmployeeVO(
	                    model.getEmpId(),
	                    model.getEmpName(),
	                    model.getEmpCity(),
	                    model.getEmpType()
	            ))
	            .toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(voList);
	}
	
	@GetMapping(value ="getbycity/{city}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getEmployeeByCity(@PathVariable("city") String empCity){
		
		var models = employeeService.getEmployeeByCity(empCity);
		
		if (models.isEmpty()){
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employees found in city: " + empCity));
	    }
		
		List<EmployeeVO> voList = models.stream()
	            .map(model -> new EmployeeVO(
	                    model.getEmpId(),
	                    model.getEmpName(),
	                    model.getEmpCity(),
	                    model.getEmpType()
	            ))
	            .toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(voList);
	}
	
	@GetMapping(value ="getbytype/{type}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getEmployeeByType(@PathVariable("type") String empType){
		
		var models = employeeService.getEmployeeByType(empType);
		
		if (models.isEmpty()){
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employees found with type: " + empType));
	    }
		
		List<EmployeeVO> voList = models.stream()
	            .map(model -> new EmployeeVO(
	                    model.getEmpId(),
	                    model.getEmpName(),
	                    model.getEmpCity(),
	                    model.getEmpType()
	            ))
	            .toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(voList);
	}
	
	@GetMapping(value ="getbycityandtype/{city}/{type}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getEmployeeByCityAndType(@PathVariable("city") String empCity,@PathVariable("type") String empType){
		
		var models = employeeService.getEmployeeByCityAndType(empCity,empType);
		
		if (models.isEmpty()){
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employees found with type: " + empType));
	    }
		
		List<EmployeeVO> voList = models.stream()
	            .map(model -> new EmployeeVO(
	                    model.getEmpId(),
	                    model.getEmpName(),
	                    model.getEmpCity(),
	                    model.getEmpType()
	            ))
	            .toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(voList);
	}
	
	@GetMapping(value ="getbynameandtype/{name}/{type}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getEmployeeByNameAndType(@PathVariable("name") String empName,@PathVariable("type") String empType){
		
		var models = employeeService.getEmployeeByNameAndType(empName,empType);
		
		if (models.isEmpty()){
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employees found with type: " + empType));
	    }
		
		List<EmployeeVO> voList = models.stream()
	            .map(model -> new EmployeeVO(
	                    model.getEmpId(),
	                    model.getEmpName(),
	                    model.getEmpCity(),
	                    model.getEmpType()
	            ))
	            .toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(voList);
	}
	
	
	@DeleteMapping(value ="/delete/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id){
		EmployeeModel employeeModel = employeeService.deleteEmployee(id);
		if(employeeModel == null) {
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employee found with id : " + id));
		}
		EmployeeVO employeeVO = new EmployeeVO(
	            employeeModel.getEmpId(),
	            employeeModel.getEmpName(),
	            employeeModel.getEmpCity(),
	            employeeModel.getEmpType()
	    );
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("Employee Deleted",employeeVO));
	}
	
	@PutMapping(value = "/update/{id}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	
	public ResponseEntity<?> updateEmployee(@PathVariable("id") Integer id,@RequestBody EmployeeModel employeeModel){
		EmployeeModel employeeModel1 = employeeService.updateEmployee(id,employeeModel);
		if(employeeModel1 == null) {
			logger.error("Employees Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("message", "No employee found with id : " + id));
		}
		EmployeeVO employeeVO = new EmployeeVO(
	            employeeModel1.getEmpId(),
	            employeeModel1.getEmpName(),
	            employeeModel1.getEmpCity(),
	            employeeModel1.getEmpType()
	    );
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("Employee Updated",employeeVO));
	}
	
}
