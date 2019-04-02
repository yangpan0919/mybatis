package com.study.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/2.
 */
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    /**
     * 元对象插入填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        logger.debug("***************INSERT自动填充********************");

        if (updateTime == null) {
            //时间的话mysql数据库就可以自己完成
            setFieldValByName("updateTime",new Date(System.currentTimeMillis()),metaObject);
        }
    }

    /**
     * 元对象更新填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        logger.debug("***************UPDATE自动填充********************");
        if (updateTime == null) {
            setFieldValByName("updateTime",new Date(System.currentTimeMillis()),metaObject);
        }
    }
}

