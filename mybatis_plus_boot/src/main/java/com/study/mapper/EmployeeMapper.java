package com.study.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.beans.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper接口
 * 	
 * 基于Mybatis:  在Mapper接口中编写CRUD相关的方法  提供Mapper接口所对应的SQL映射文件 以及 方法对应的SQL语句. 
 * 
 * 基于MP:  让XxxMapper接口继承 BaseMapper接口即可.
 * 		   BaseMapper<T> : 泛型指定的就是当前Mapper接口所操作的实体类类型 
 * 
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    //   Integer  insertEmployee(Employee employee );
	//   <insert useGeneratedKeys="true" keyProperty="id" > SQL...</insert>
}
