package com.demo.CloudRunDemo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.demo.CloudRunDemo.model.EmployeeModel;

public class EmployeeRowMapper implements RowMapper<EmployeeModel> {

	@Override
	public EmployeeModel mapRow(ResultSet rs, int arg1) throws SQLException {
        
		EmployeeModel emp = new EmployeeModel();
		emp.setName(rs.getString("name"));
		emp.setId(rs.getInt("id"));
		emp.setAddress(rs.getString("address"));
		emp.setEmail(rs.getString("email"));
		return emp;
	}

}