package com.demo.CloudRunDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.CloudRunDemo.dao.ClientDao;
import com.demo.CloudRunDemo.dao.model.Client;

@Service
public class CleintService {

    @Autowired
    private ClientDao clientDao;

    public String hello() {
        return "Hello from Cloud Run";
    }

    // get all clients
    @Transactional
    public List<Client> getAllClients() {
        return clientDao.selectClients();
    }
    
}
