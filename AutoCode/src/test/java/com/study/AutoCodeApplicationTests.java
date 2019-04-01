package com.study;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoCodeApplicationTests {

	public static String tableName = "tbl_employee";
	/**
	 * 获取TemplateConfig
	 *
	 * @return
	 */
	protected TemplateConfig getTemplateConfig() {
		return new TemplateConfig().setXml(null);
	}
	/**
	 * 获取InjectionConfig
	 *
	 * @return
	 */
	protected InjectionConfig getInjectionConfig() {
		return new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				this.setMap(map);
			}
		}.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
				"/templates/mapper.xml.vm") {
			// 自定义输出文件目录
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getResourcePath() + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
			}
		}));
	}
	/**
	 * 获取TableFill策略
	 *
	 * @return
	 */
	protected List<TableFill> getTableFills() {
		// 自定义需要填充的字段
		List<TableFill> tableFillList = new ArrayList<>();
		tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
		tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
		tableFillList.add(new TableFill("createUid", FieldFill.INSERT));
		tableFillList.add(new TableFill("updateUid", FieldFill.INSERT_UPDATE));
		return tableFillList;
	}

	/**
	 * 获取根目录
	 *
	 * @return
	 */
	private String getRootPath() {
		String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getFile();
		String rootPath = new File(file).getParentFile().getParent();
		return rootPath;
	}

	/**
	 * 获取JAVA目录
	 *
	 * @return
	 */
	protected String getJavaPath() {
		return getRootPath() + "/src/main/java";
	}

	/**
	 * 获取Resource目录
	 *
	 * @return
	 */
	protected String getResourcePath() {
		return getRootPath() + "/src/main/resources";
	}
	@Test
	public void contextLoads() {


	}
	/**
	 * 代码生成    示例代码
	 */
	@Test
	public void  testGenerator() {
		//1. 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setOutputDir(getJavaPath())//输出目录
				.setFileOverride(true)// 是否覆盖文件
				.setActiveRecord(true)// 开启 activeRecord 模式  (AR模式)
				.setEnableCache(false)// XML 二级缓存
				.setBaseResultMap(false)// XML ResultMap
				.setBaseColumnList(false)// XML columList
				.setKotlin(false) //是否生成 kotlin 代码
				.setOpen(false)
				.setAuthor("YP") //作者
				//自定义文件命名，注意 %s 会自动填充表实体属性！
				.setEntityName("%s")
				.setMapperName("%sMapper")
				.setXmlName("%sMapper")
				.setServiceName("%sServiceI")
				.setServiceImplName("%sServiceImpl")
				.setControllerName("%sRestController");

		//2. 数据源配置
		DataSourceConfig dsConfig  = new DataSourceConfig();
		dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
				.setDriverName("com.mysql.jdbc.Driver")
				.setUrl("jdbc:mysql://39.104.206.97:3306/test?useSSL=false")
				.setUsername("root")
				.setPassword("123456");

		//3. 策略配置
		StrategyConfig stConfig = new StrategyConfig();
		List<TableFill> tableFillList = getTableFills();
		stConfig.setCapitalMode(false)// 全局大写命名
				.setTablePrefix("tbl_")// 去除前缀
				.setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
				//.setInclude(new String[] { "user" }) // 需要生成的表
				//自定义实体父类
//				.setSuperEntityClass("com.study.framework.model.BaseModel")
				// 自定义实体，公共字段
				.setSuperEntityColumns("id")
				.setTableFillList(tableFillList)
				// 自定义 mapper 父类
//				.setSuperMapperClass("com.study.framework.mapper.BaseMapper")
				// 自定义 controller 父类
//				.setSuperControllerClass("com.study.framework.controller.SuperController")
				// 自定义 service 实现类父类
//				.setSuperServiceImplClass("com.study.framework.service.impl.BaseServiceImpl")
				// 自定义 service 接口父类
//				.setSuperServiceClass("com.study.framework.service.BaseService")
				// 【实体】是否生成字段常量（默认 false）
				.setEntityColumnConstant(true)
				// 【实体】是否为构建者模型（默认 false）
				.setEntityBuilderModel(false)
				// 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
//				.setEntityLombokModel(true)
				// Boolean类型字段是否移除is前缀处理
				.setEntityBooleanColumnRemoveIsPrefix(true)
				.setRestControllerStyle(false)
				.setRestControllerStyle(true)
				.setInclude(tableName);

		//4. 包名策略配置
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setParent("com.study")
				.setController("controller")
				.setEntity("model.entity")
				.setMapper("mapper")
				.setService("service")
				.setServiceImpl("service.impl");

		//5. 整合配置
		AutoGenerator ag = new AutoGenerator();
		         // 全局配置
		ag.      setGlobalConfig(config)
				// 数据源配置
				.setDataSource(dsConfig)
				// 策略配置
				.setStrategy(stConfig)
				// 包配置
				.setPackageInfo(pkConfig)
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		.setCfg(getInjectionConfig())
				.setTemplate(getTemplateConfig());

		//6. 执行
		ag.execute();
		System.out.println("------------------------------------------------------------------------------------------------");
	}

}
