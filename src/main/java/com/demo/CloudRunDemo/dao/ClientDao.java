package com.demo.CloudRunDemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.CloudRunDemo.dao.model.Client;

@Mapper
public interface ClientDao {

    public List<Client> selectClients();
    
}
