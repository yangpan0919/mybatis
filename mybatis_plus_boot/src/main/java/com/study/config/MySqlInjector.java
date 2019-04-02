package com.study.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;


/**
 * 自定义全局操作,加入自定义注入的方法
 */
public class MySqlInjector  extends AbstractSqlInjector{

	@Override
	public List<AbstractMethod> getMethodList() {
		List<AbstractMethod> list = new ArrayList<>();
		list.add(new DeleteAll());
		return list;

//		return Stream.of(
//				new DeleteAll()
//		).collect(toList());

	}
}
