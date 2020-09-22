package com.linkinstars.mybatisPlus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.util.StringUtils;

/**
 * Created by Kim QQ.Cong on 2020-09-22 / 11:02 上午
 *
 * @author: CongQingquan
 * @Description:
 */
public class MybatisPlusGenerator {

    /**
     * 1. 链接/端口/数据库名
     */
    public static final String url_port_dbname = "10.0.45.222:3309/smartdata";

    /**
     * 2. 登陆账号/密码
     */
    public static final String user_name = "admin";
    public static final String user_pwd = "Bmzt2016_mysql";

    /**
     * 3. 生成文件所在目录
     */
    public static final String module_name = "generate";
    public static final String parent_package_name_of_module = "com.linkinstars.mybatisPlus";

    /**
     * 4. 生成的表
     */
    public static final String[] tables = new String[] {
      "sds_tables"
    };


    /**
     * 5. 自定义配置 (非必须)
     */

    /**
     * 实体类是否加swagger注解
     */
    public static final boolean enableSwagger = false;

    /**
     * Controller
     */
    public static final String super_controller = "";
    /**
     * service
     */
    public static final String super_service_class = "";
    public static final String super_service_impl_class = "";
    /**
     * entity
     */
    public static final String super_entity = "";
    public static final String[] fields_in_super_entity = new String[] {};



    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("用户名");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");

        //实体属性 Swagger2 注解
        gc.setSwagger2(enableSwagger);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + url_port_dbname
            + "?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(user_name);
        dsc.setPassword(user_pwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(module_name);
        pc.setParent(parent_package_name_of_module);
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        /**
         * Controller
         */
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(super_controller);
        strategy.setControllerMappingHyphenStyle(false);
        /**
         * service
         */
        strategy.setSuperServiceClass(super_service_class);
        strategy.setSuperServiceImplClass(super_service_impl_class);
        /**
         * Entity
         */
        strategy.setSuperEntityClass(super_entity);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(fields_in_super_entity);
        strategy.setEntityColumnConstant(true);
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setTablePrefix("t_");

        strategy.setInclude(tables);
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}