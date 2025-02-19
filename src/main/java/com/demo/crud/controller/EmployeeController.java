package com.demo.crud.controller;

import java.util.ArrayList;

import java.util.List;

import com.demo.crud.service.CacheInspectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.crud.entity.Employee;
import com.demo.crud.service.EmployeeService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	private Logger logger= LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private CacheInspectionService cacheInspectionService;
	@Autowired
	private EmployeeService empservice;
	
	@GetMapping("/msg")
	public String getmsg() {
		return "Hello Sonali";
	}
	
	@GetMapping("/list")
	public List<String>getEmployeeData(){
		List<String>list=new ArrayList<String>();
		list.add("ram");
		list.add("10");
		list.add("pune");
		return list ;
		
	}

	@PostMapping("/saveEmployeedata")
	public ResponseEntity<Employee>saveEmployee(@RequestBody Employee employee){
		
		System.out.println(employee);
		
		Employee saveEmployeeContr=empservice.saveEmployee(employee);
		
		return ResponseEntity.ok().body(saveEmployeeContr);
		
	}
	
	@GetMapping("/empid/{id}")
	@Cacheable("cacheEmp")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")Integer id){
		
		Employee empgetById=empservice.getEmployeeById(id);
		
		return ResponseEntity.ok().body(empgetById);
	}

	@GetMapping("/cacheData")
	public void getCachedData(@RequestParam("cacheName")String cacheName){
		this.cacheInspectionService.printData(cacheName);
	}
	
	@GetMapping("/empname/{name}")
	public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable("name")String name){
		
		List<Employee> empgetByName=empservice.getEmployeebyName(name);
		
		return ResponseEntity.ok().body(empgetByName);
	}
	
	@GetMapping("/emplist")
	public ResponseEntity<List<Employee>> getAllEmployee(Employee employee){
		
		List<Employee> emplist=empservice.getAllEmployee(employee);
		
		return ResponseEntity.ok().body(emplist);
	}
	
	@PatchMapping("/SaveorFlush")
	public ResponseEntity<Employee> UpdateOrFlushdata(@RequestBody Employee employee){
		 Employee emp1=empservice.ChangeSomedata(employee);
		 
		 return ResponseEntity.ok().body(emp1); 
	}
	
	@PutMapping("/updatEmp/{id}")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee ,@PathVariable("id") int empId){
		 Employee emp1=empservice.UpdateEmp(employee,empId);
		 
		 return ResponseEntity.ok().body(emp1); 
	}

	@DeleteMapping("/delete")
	public void deleteEmpData(Employee employee){
        empservice.DeleteEmployee(employee);
    }
//
	@DeleteMapping("/deleteby/{id}")
	public ResponseEntity<String>deleteById(@PathVariable("id") Integer id){
		String s = empservice.deleteById(id);
		if(s.equals("Employee data delete Successfully by id..."))
		{
			return ResponseEntity.status(HttpStatus.OK).body(s);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(s);
		}

    }

	//native
	@GetMapping("/{city}")
	public ResponseEntity<List<Employee>>findbycity(@PathVariable("city") String city){
		List<Employee> listByCity = empservice.getListByCity(city);
		return ResponseEntity.status(HttpStatus.OK).body(listByCity);
    }

	//Upload File and Json Combine
	@PostMapping("/uploadfileoremp")
	public ResponseEntity<?>addEmployeeInformation(@RequestParam("file")MultipartFile file,@RequestParam("userdata") String employeeData) throws JsonProcessingException {
		this.logger.info("employee:{}",employeeData);
		this.logger.info("File Name:{}",file.getOriginalFilename());

		//converting string into json
		Employee employee=null ;
		try{
			employee= mapper.readValue(employeeData, Employee.class);
		}catch(JsonProcessingException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		}
		return ResponseEntity.ok().body(employee);
	}
}
