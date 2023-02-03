package com.taiwanmobile.CloudRunDemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiwanmobile.CloudRunDemo.dao.EmployeeDao;
import com.taiwanmobile.CloudRunDemo.model.EmployeeModel;
import com.taiwanmobile.CloudRunDemo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<EmployeeModel> findAll() {
		return employeeDao.findAll();
	}

}
