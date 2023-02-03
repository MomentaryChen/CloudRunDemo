package com.taiwanmobile.CloudRunDemo.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.collections4.comparators.FixedOrderComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.taiwanmobile.CloudRunDemo.config.GCSConfig;
import com.taiwanmobile.CloudRunDemo.model.EmployeeModel;
import com.taiwanmobile.CloudRunDemo.service.CloudStorageService;
import com.taiwanmobile.CloudRunDemo.service.EmployeeService;
import com.taiwanmobile.CloudRunDemo.util.CSVUtils;

@RestController
public class EmployeeContorller {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CloudStorageService gcsService;
	
	@Autowired
	GCSConfig gcsConfig;
	
	CSVUtils csvUtils = CSVUtils.getInstance();
	
	@GetMapping(value = "/name")
	public String getName() {
		return "Cloud Run Demo";
	}

	@GetMapping(value = "/employee")
	public List<EmployeeModel> getEmployees() {
		return employeeService.findAll();
	}
	
	
	@GetMapping(value = "/uploadGCS")
	public String exportEmployeesToGCS() {
		List<EmployeeModel> employees = employeeService.findAll();
		
		String objectName = "employees/employees-" + LocalDateTime.now().toString() + ".csv";
		StringBuilder sb = new StringBuilder(1024);

		try {
			FixedOrderComparator<String> fixedOrderComparator = new FixedOrderComparator<>("ID", "NAME", "ADDRESS", "EMAIL");
			StringWriter writer = csvUtils.writeRowsToCsvStrings(employees, fixedOrderComparator, EmployeeModel.class );
			gcsService.uploadObjectFromMemory(gcsConfig.bucketName, objectName, writer.toString());
		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			e.printStackTrace();
			return "上傳失敗";
		}
		
		return "上傳成功";
	}
}
