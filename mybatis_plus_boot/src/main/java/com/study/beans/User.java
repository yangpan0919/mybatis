package com.study.beans;



import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

//@KeySequence(value="seq_user",clazz=Integer.class)
public class User extends Parent {
	//@TableId(type=IdType.INPUT)
	private Integer id  ;
	
	@TableField(fill= FieldFill.INSERT_UPDATE)
	private String name ;
	
	@TableLogic   // 逻辑删除属性
	private Integer deleteFlag ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", deleteFlag=" + deleteFlag +
				'}';
	}
}
