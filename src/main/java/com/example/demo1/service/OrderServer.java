package com.example.demo1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/5/19 17:11
 * @version: 1.0
 */
@Slf4j
@Service
@Transactional
public class OrderServer{

    @Autowired
    private JobService  jobService;


}
