package com.demo.CloudRunDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.CloudRunDemo.dao.EmployeeDao;
import com.demo.CloudRunDemo.model.EmployeeModel;
import com.demo.CloudRunDemo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<EmployeeModel> findAll() {
		return employeeDao.findAll();
	}

}
