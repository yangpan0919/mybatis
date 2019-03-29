package com.study;

import com.study.Utils.SpringBeanUtil;
import com.study.Utils.TestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class MybatisPlusBootApplication {

	private static ApplicationContext applicationContext;

	public static void main(String[] args)  {
		ConfigurableApplicationContext run = SpringApplication.run(MybatisPlusBootApplication.class, args);
		applicationContext = run;
		SpringBeanUtil.setApplicationContext(applicationContext);
		try {
			TestUtils.test(applicationContext);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
