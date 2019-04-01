package com.study.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YP
 * @since 2019-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    private String lastName;

    private String email;

    private String gender;

    private Integer age;

    private LocalDateTime createTime;

    @TableField("update_TIME")
    private LocalDateTime updateTime;


    public static final String LAST_NAME = "last_name";

    public static final String EMAIL = "email";

    public static final String GENDER = "gender";

    public static final String AGE = "age";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_TIME";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
