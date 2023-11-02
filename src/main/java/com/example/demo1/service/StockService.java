package com.example.demo1.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo1.dao.stockDao;
import com.example.demo1.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/5/19 16:56
 * @version: 1.0
 */
@Transactional
@Service
@Slf4j
public class StockService {

    @Autowired
    private stockDao stock;

    public boolean decrease(String productId) {
        Stock select = this.stock.selectById(productId);
        stock.update(null, new UpdateWrapper<Stock>()
                .set("stock_num", select.getStockNum() - 1)
                .eq("id", productId));
        return true;

    }
}
