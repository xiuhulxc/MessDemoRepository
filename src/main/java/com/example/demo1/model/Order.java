package com.example.demo1.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/5/19 17:13
 * @version: 1.0
 */
@Data
@TableName("order")
public class Order {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

}
