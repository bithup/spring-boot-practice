package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;
//不需要数据库时，没有配置数据库信息，排除自动配置，否则会报错
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication()
public class DemoApplication implements CommandLineRunner {

	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATASOURCE: "+dataSource);
		System.out.println(dataSource.getClass().getName());
	}
}
