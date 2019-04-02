package com.study.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Administrator on 2019/4/2.
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {

    private long time;


    //方法拦截
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //通过StatementHandler获取执行的sql
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();


        //所有的拦截到的sql,都会进行修改参数
//        Object target = invocation.getTarget();
//        System.out.println("当前拦截到的对象："+target);
//        //拿到target的元数据
//        MetaObject metaObject = SystemMetaObject.forObject(target);
//        Object value = metaObject.getValue("parameterHandler.parameterObject");
//        System.out.println("sql语句用的参数是："+value);
//        //修改完sql语句要用的参数
//        metaObject.setValue("parameterHandler.parameterObject", 0);


        long start = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long end = System.currentTimeMillis();
        long allTime = end - start;
        if (allTime> time) {
            System.out.println("本次数据库操作是慢查询，sql是:" + sql);
            System.out.println("本次查询的时间是：" + allTime);
        }else{
            System.out.println("本次数据库操作是慢查询，sql是:" + sql);
            System.out.println("本次查询的时间是：" + allTime);

        }
        return proceed;
    }

    //获取到拦截的对象，底层也是通过代理实现的，实际上是拿到一个目标代理对象
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    //获取设置的阈值等参数
    @Override
    public void setProperties(Properties properties) {
        this.time = Long.parseLong(properties.getProperty("time"));
    }
}

