package com.taiwanmobile.CloudRunDemo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.taiwanmobile.CloudRunDemo.dao.EmployeeDao;
import com.taiwanmobile.CloudRunDemo.model.EmployeeModel;
import com.taiwanmobile.CloudRunDemo.mapper.EmployeeRowMapper;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public List<EmployeeModel> findAll() {
		return template.query("select * from employee", new EmployeeRowMapper());
	}

}