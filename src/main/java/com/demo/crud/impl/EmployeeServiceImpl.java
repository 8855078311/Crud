package com.demo.crud.impl;

import java.util.List;
import java.util.Optional;

import com.demo.crud.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.crud.entity.Employee;
import com.demo.crud.repo.EmployeeRepository;
import com.demo.crud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository emprepo;

	@Override
	public Employee saveEmployee(Employee employee) {

		Employee saveEmpList = emprepo.save(employee);

		System.out.println("employee in service before save : " + employee);
		System.out.println("employee in service after save : " + saveEmpList);

		return saveEmpList;
	}

	@Override
	@Cacheable("cacheName")
	public Employee getEmployeeById(Integer id) {

//		Employee EmpgetbyId = emprepo.findById(id).get();

		Employee EmpgetbyId = emprepo.findById(id).orElseThrow(()->
				new ResourceNotFoundException("User not found with id:"+id));
		return EmpgetbyId;
	}

	@Override
	public List<Employee> getEmployeebyName(String name) {

		return this.emprepo.findByName(name);
	}

	@Override
	public List<Employee> getAllEmployee(Employee employee) {

		List<Employee> geAllemp = emprepo.findAll();

		return geAllemp;
	}

	@Override
	public Employee ChangeSomedata(Employee employee) {

		Employee emppatchdata = emprepo.saveAndFlush(employee);

		return emppatchdata;
	}

	@Override
	public Employee UpdateEmp(Employee employee, int empId) {

		Employee empById = this.emprepo.findById(empId).get();

		empById.setCity(employee.getCity());
		empById.setEmpimg(employee.getEmpimg());

		return this.emprepo.save(empById);
	}

	@Override
	public void DeleteEmployee(Employee employee) {
		emprepo.deleteAll();
    }

	@Override
	public String deleteById(Integer id) {
		Optional<Employee> EmployeebyId = emprepo.findById(id);
		if(EmployeebyId.isPresent())
		{
			emprepo.deleteById(id);
			return "Employee deleted successfully.";
		}
		else{
			return "Employee not found";
		}
	}

	@Override
	public List<Employee> getListByCity(String city) {
		List<Employee> byCity = emprepo.findByCity(city);

		return (List<Employee>)byCity;
	}
}
