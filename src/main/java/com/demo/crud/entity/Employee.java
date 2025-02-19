package com.demo.crud.entity;

import jakarta.persistence.*;

@Entity

@Table(name="Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String city;
	@Column(nullable = false)
	private String empimg;

	public String getEmpimg() {
		return empimg;
	}

	public void setEmpimg(String empimg) {
		this.empimg = empimg;
	}



//	public Employee() {
//	}
//
//	public Employee(String empimg) {
//		this.empimg = empimg;
//	}
	/*
	 * public Employee(int id, String name, String city) { this.id = id; this.name =
	 * name; this.city = city; } public Employee() {
	 * 
	 }*/


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city='" + city + '\'' +
				", empimg='" + empimg + '\'' +
				'}';
	}
}
