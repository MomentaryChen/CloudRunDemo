package com.demo.CloudRunDemo.dao;

import java.util.List;

import com.demo.CloudRunDemo.model.EmployeeModel;

public interface EmployeeDao {
	List<EmployeeModel> findAll();
}
