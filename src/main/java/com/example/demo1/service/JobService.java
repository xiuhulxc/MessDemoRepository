package com.example.demo1.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/6/28 15:29
 * @version: 1.0
 */

@Component
public class JobService {

//    // 每秒执行一次任务
//    @Scheduled(fixedRate = 1000)
//    public void runTask() {
//        System.out.println("定时任务执行中...");
//    }

    @Scheduled(cron = "0/9 * * * * ?")
    @Async
    public void bakData(){
        // TODO 操作 之前给数据库添加 只读锁
        String fileName = "Bak"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".sql";

        String command = "mysqldump --user=root --password= --databases lshw > F:\\backup\\"+fileName+".sql";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Database backup completed successfully.");
            } else {
                System.out.println("Database backup failed.");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}
