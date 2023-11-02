package com.example.demo1.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/5/19 17:04
 * @version: 1.0
 */
@Data
@TableName("stock")
public class Stock {


    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private Integer stockNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;



}
