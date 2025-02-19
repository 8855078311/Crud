package com.demo.crud.service;

import java.util.List;

import com.demo.crud.entity.Employee;

public interface EmployeeService{

	public Employee saveEmployee(Employee employee);

	public Employee getEmployeeById(Integer id);

	List<Employee> getEmployeebyName(String name);

	public List<Employee> getAllEmployee(Employee employee);

	public Employee ChangeSomedata(Employee employee);

	Employee UpdateEmp(Employee employee,int EmpId);

	public void DeleteEmployee(Employee employee);

	String deleteById(Integer id);


	//nativ
	public List<Employee>getListByCity(String city);
}
