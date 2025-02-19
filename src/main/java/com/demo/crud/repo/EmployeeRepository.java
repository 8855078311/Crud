package com.demo.crud.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.crud.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	List<Employee> findByName(String name);

	Optional<Employee> findById(Integer id);

	@Query(value = "select * from Employee c where city=?1",nativeQuery = true)
	List<Employee> findByCity(String city);

	//List<Employee> findAll(Employee employee);
}