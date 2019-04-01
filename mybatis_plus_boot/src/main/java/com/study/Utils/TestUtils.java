package com.study.Utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.beans.Employee;
import com.study.mapper.EmployeeMapper;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/29.
 */
public class TestUtils {

    public static ApplicationContext ioc = SpringBeanUtil.getApplicationContext();

    public static EmployeeMapper employeeMapper =  ioc.getBean("employeeMapper",EmployeeMapper.class);

    public static void test(ApplicationContext applicationContext) throws SQLException {

        DataSource ds  = ioc.getBean("dataSource",DataSource.class);
//        System.out.println(ds);
        Connection conn = ds.getConnection();
        System.out.println("----------------------------------------------------------------------");

        test4();
    }


    public static void test4(){
        Employee employee  = new Employee();
        employee.setLastName("朱卫红");
        employee.setEmail("mp@atguigu.com");
        employee.setGender(2);
        employee.setAge(11);
        employee.setId(12);
//        boolean insert = employee.insert();
        boolean b = employee.insertOrUpdate();
        List<Employee> employees = employee.selectAll();
        System.out.println(employees);


        System.out.println(b);
    }


    public static void test1(){
        //初始化Employee对象
        Employee employee  = new Employee();
        employee.setLastName("MP");
        employee.setEmail("mp@atguigu.com");
//        employee.setGender(1);
//        employee.setAge(22);
        employee.setSalary(20000.0);
//        int insert = employeeMapper.insert(employee);
        int insert = employeeMapper.insert(employee);
        System.out.println(insert);
        System.out.println(employee.getId());
    }
    public static void test2(){
        Employee employee = new Employee();
        employee.setId(10);
        employee.setLastName("朱卫紅");
//        employee.setAge(18);
//        employee.setEmail("mybatisPlus@qq.com");
//        employee.setGender(6);


//        int i = employeeMapper.updateById(employee);
        int i = employeeMapper.update(employee, new UpdateWrapper<Employee>().likeLeft(true,"email","com"));
        System.out.println(i);

    }

    public static void test3(){
        Employee employee = employeeMapper.selectById(1);
        System.out.println(employee);

//        Employee employee1 = employeeMapper.selectOne(new QueryWrapper<Employee>().eq("last_name", "朱卫紅"));
//        List<Employee> employees = employeeMapper.selectBatchIds(Arrays.asList(new Integer[]{1, 2, 3, 4, 5}));
        Map<String ,Object> columMap = new HashMap<>();
        columMap.put("last_name","朱卫紅");
        columMap.put("gender","1");

        List<Employee> employees1 = employeeMapper.selectByMap(columMap);

        IPage<Employee> employeeIPage = employeeMapper.selectPage(new Page<>(3,2), new QueryWrapper<>());

        System.out.println(employeeIPage.getRecords());
    }




}
