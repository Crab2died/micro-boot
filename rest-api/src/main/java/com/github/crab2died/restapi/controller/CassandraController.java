package com.github.crab2died.restapi.controller;

import com.github.crab2died.cassandra.repository.CassandraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CassandraController {

    @Autowired
    private CassandraRepo cassandraRepo;

    @GetMapping("cassandra/list")
    public ResponseEntity getList() {
        return new ResponseEntity(cassandraRepo.getList(), HttpStatus.OK);
    }

}
