package com.study.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.study.plugin.MyPlugin;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2019/4/2.
 */
@Configuration
public class MapperConfig {





    //注册插件mybatis  自定义插件   //只为自定义
    @Bean
    public MyPlugin myPlugin() {
        MyPlugin myPlugin = new MyPlugin();
        //设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试

        Properties properties = new Properties();
        //这里设置慢查询阈值为1毫秒，便于测试
        properties.setProperty("time", "1");
        myPlugin.setProperties(properties);
        return myPlugin;
    }
    /**
     * MybatisPlus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setOverflow(true);  //  溢出总页数，设置第一页

        //设置为true，启用pageHeper支持
        Properties properties = new Properties();
        properties.setProperty("localPage","true");
        paginationInterceptor.setProperties(properties);

        //阻止恶意的全表更新删除
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        // 攻击 SQL 阻断解析器、加入解析链
//        sqlParserList.add(new BlockAttackSqlParser());
//        paginationInterceptor.setSqlParserList(sqlParserList);

        return paginationInterceptor;
    }

    /**
     * SQL执行分析插件，开发环境使用，线上不推荐
     * @return
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();

        Properties properties = new Properties();
        //就没成功过      mybatisPlus的分页插件PaginationInterceptor 上已经实现了全表删除更新操作的阻止
        properties.setProperty("stopProceed","true");
        properties.setProperty("stop_Proceed","true");
        properties.setProperty("STOP_PROCEED","true");
        properties.setProperty("proceed","true");
        properties.setProperty("proceed","false");
        properties.setProperty("xxxxxxxx","trccccccccue");
        //发现全表执行 delete update停止运行
        sqlExplainInterceptor.setProperties(properties);
        return sqlExplainInterceptor;
    }

//    /**
//     * 自定义方法注入
//     * @return
//     */
//    @Bean(name = "mySqlInjector",value = "mySqlInjector",autowire =Autowire.BY_NAME )
////    @Primary    加入該註解后原有的mappr方法不能綁定sql語句
//    public MySqlInjector mySqlInjector() {
//        return new MySqlInjector();
//    }
    /**
     *  逻辑删除注入       與上面的自定義注入就沒有成功過
     *  No qualifying bean of type 'com.baomidou.mybatisplus.core.injector.ISqlInjector' available: expected single matching bean but found 2: mySqlInjector,deteleInjector
     * @return
     */
    @Bean(name = "deleteInjector",value = "deleteInjector",autowire =Autowire.BY_NAME)
    public LogicSqlInjector deleteInjector() {
        return new LogicSqlInjector();
    }
    /**
     * SQL 执行性能分析，开发环境使用，线上不推荐
     * @return
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //SQL 执行最大时长，超过自动停止运行，有助于发现问题，单位ms
        performanceInterceptor.setMaxTime(500);
        //SQL是否格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * 乐观锁机制
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }



    @Bean
    public String str1(){
        return  "testStr";
    }
    @Bean
    public String str2(){
        return  "testStr2";
    }

}
