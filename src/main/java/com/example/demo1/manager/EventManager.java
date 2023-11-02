package com.example.demo1.manager;

import com.example.demo1.model.Stock;
import com.example.demo1.util.HibernateUtil;
import org.hibernate.Session;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/9/19 9:49
 * @version: 1.0
 */
public class EventManager {

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        if (args[0].equals("store")) {
            eventManager.createAndStoreEvent();
        } else if (args[0].equals("list")) {
            List list = eventManager.listEvents();
            for (int i = 0; i < list.size(); i++) {
                Stock stocks = (Stock) list.get(i);
                System.out.println(stocks.getId() + stocks.getCreateTime());
            }
        }
        HibernateUtil.getSessionFactory().close();
    }

    public void createAndStoreEvent() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Stock stock = new Stock();
        stock.setStockNum(1);
        stock.setCreateTime(LocalDateTime.now());
        stock.setUpdateTime(LocalDateTime.now());
        session.save(stock);
        session.getTransaction().commit();
    }

    private List listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List fromStock = session.createQuery("",Stock.class).list();
        session.getTransaction().commit();
        return fromStock;
    }
}
