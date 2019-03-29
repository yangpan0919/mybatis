package com.study;

import com.study.Utils.SpringBeanUtil;
import com.study.Utils.TestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class MybatisPlusBootApplication2 {

	private static ApplicationContext applicationContext;

	public static void main(String[] args)  {
		ConfigurableApplicationContext run = SpringApplication.run(MybatisPlusBootApplication2.class, args);
		applicationContext = run;
		SpringBeanUtil.setApplicationContext(applicationContext);
		try {
			TestUtils.test(applicationContext);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
