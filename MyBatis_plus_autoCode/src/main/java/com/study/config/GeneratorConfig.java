package com.study.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019/4/1.
 */
@Configuration
public class GeneratorConfig {

//    @Bean
//    public GlobalConfig creteGlobalConfig (){
//        GlobalConfig config = new GlobalConfig();
//        config.setActiveRecord(true)
//                .setAuthor("weiyunhui") // 作者
//                .setOutputDir("D:\\workspace_mp\\mp03\\src\\main\\java") // 生成路径
//                .setFileOverride(true)  // 文件覆盖
//                .setIdType(IdType.AUTO) // 主键策略
//                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
//                // IEmployeeService
//                .setBaseResultMap(true)
//                .setBaseColumnList(true);
//        return  config;
//    }
//
//    @Bean
//    public PackageConfig cretePackageConfig (){
//        PackageConfig config = new PackageConfig();
//        config.setParent("com.study.mp")
//                .setMapper("mapper")
//                .setService("service")
//                .setController("controller")
//                .setEntity("beans")
//                .setXml("mapperXml");
//        return  config;
//    }
    @Bean
    public PackageConfig cretePackageConfig (){
        PackageConfig config = new PackageConfig();
        config.setParent("com.study.mp")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("beans")
                .setXml("mapperXml");
        return  config;
    }

}
