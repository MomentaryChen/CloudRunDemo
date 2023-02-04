package com.demo.CloudRunDemo.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.collections4.comparators.FixedOrderComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.demo.CloudRunDemo.config.GCSConfig;
import com.demo.CloudRunDemo.model.EmployeeModel;
import com.demo.CloudRunDemo.service.CloudStorageService;
import com.demo.CloudRunDemo.service.EmployeeService;
import com.demo.CloudRunDemo.util.CSVUtils;

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
		
		Instant nowInstant = Instant.now();
		ZoneId taipeiZoneId = ZoneId.of("Asia/Taipei");
		DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
		ZonedDateTime zdt = ZonedDateTime.ofInstant(nowInstant, taipeiZoneId);
		
		String objectName = "employees/employees-" + zdt.format(DTF).toString()  + ".csv";
		
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
