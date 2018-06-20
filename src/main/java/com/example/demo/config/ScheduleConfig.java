package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Scheduled(fixedRate = 1000 * 60 * 2)
    public void schedule1() {
        System.out.println("schedule1 run");
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void schedule2() {
        System.out.println("schedule2 run");
    }
}
