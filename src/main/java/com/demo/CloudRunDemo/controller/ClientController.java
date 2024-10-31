package com.demo.CloudRunDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CloudRunDemo.dao.model.Client;
import com.demo.CloudRunDemo.service.CleintService;

@RestController
public class ClientController {

    @Autowired
    private CleintService clientService;

    
    @GetMapping(value = "/clients")
    public List<Client> getClientS() throws Exception{
        System.out.println("hello");
        return clientService.getAllClients();
    }

}
