package com.taiwanmobile.CloudRunDemo.dao;

import java.util.List;

import com.taiwanmobile.CloudRunDemo.model.EmployeeModel;

public interface EmployeeDao {
	List<EmployeeModel> findAll();
}
