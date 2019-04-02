package com.study.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * Created by Administrator on 2019/4/2.
 */
public class DeleteAll extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //注入的SQL语句
        String sql = "delete from " +tableInfo.getTableName();
        //注入的方法名   一定要与EmployeeMapper接口中的方法名一致
        String sqlMethod = "deleteAll" ;

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addDeleteMappedStatement(mapperClass, sqlMethod, sqlSource);

    }
}
