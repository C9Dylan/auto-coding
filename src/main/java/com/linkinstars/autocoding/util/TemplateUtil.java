package com.linkinstars.autocoding.util;

import com.google.common.base.CaseFormat;
import com.linkinstars.autocoding.model.POJOmaker;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.linkinstars.autocoding.util.StaticPath.*;

/**
 * 模板制作工具
 * @author LinkinStar
 */
public class TemplateUtil {
    
    /** java模板 **/
    private static Template javaTemplate;
    /** golang模板 **/
    private static Template goTemplate;

    public static void init() throws IOException {
        //获取跟目录
        File file = null;
        try {
            file = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!file.exists()) {
            file = new File("");
        }

        absolutePath = file.getAbsolutePath();
        File upload = new File(absolutePath, staticDir + downloadPath);
        if(!upload.exists()) {
            upload.mkdirs();
        }

        ClassPathResource javaClassPathResource = new ClassPathResource("ftl/pojo.ftl");
        ClassPathResource goClassPathResource = new ClassPathResource("ftl/golang-pojo.ftl");
        
        //存文件
        File javaFtlFile = new File(absolutePath, staticDir + "ftl/pojo.ftl");
        File goFtlFile = new File(absolutePath, staticDir + "ftl/golang-pojo.ftl");
        try {
            FileUtils.copyInputStreamToFile(javaClassPathResource.getInputStream(), javaFtlFile);
            FileUtils.copyInputStreamToFile(goClassPathResource.getInputStream(), goFtlFile);
        } catch (IOException e) {
            throw e;
        }

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setDirectoryForTemplateLoading(new File(absolutePath, staticDir + "ftl"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        javaTemplate = cfg.getTemplate("pojo.ftl");
        goTemplate = cfg.getTemplate("golang-pojo.ftl");
    }

    /**
     * 将java对象写入模板
     */
    public static String writeJavaPOJOmakerToTemplate(POJOmaker pojoMaker) throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<>(10);
        map.put("className", pojoMaker.getClassName());
        map.put("tableName", pojoMaker.getTableName());
        map.put("packageName", pojoMaker.getPackageName());
        map.put("fieldList", pojoMaker.getFieldList());
        map.put("classComment", pojoMaker.getClassComment());
        map.put("author", pojoMaker.getAuthor());

        String resultPath = downloadPath + pojoMaker.getClassName() + ".java";

        FileOutputStream fos = new FileOutputStream(new File(absolutePath, staticDir + resultPath));
        Writer out = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        javaTemplate.process(map, out);
        out.flush();
        out.close();

        return pojoMaker.getClassName() + ".java";
    }

    /**
     * 将golang对象写入模板
     */
    public static String writeGoPOJOmakerToTemplate(POJOmaker pojoMaker) throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<>(10);
        map.put("className", pojoMaker.getClassName());
        map.put("packageName", pojoMaker.getPackageName());
        map.put("fieldList", pojoMaker.getFieldList());
        map.put("classComment", pojoMaker.getClassComment());
        map.put("author", pojoMaker.getAuthor());
        
        //golang以下划线命名，所需将对象的名称转换为下划线作为输出文件的名称
        String fileName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pojoMaker.getClassName());

        String resultPath = downloadPath + fileName + ".go";

        FileOutputStream fos = new FileOutputStream(new File(absolutePath, staticDir + resultPath));
        Writer out = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        goTemplate.process(map, out);
        out.flush();
        out.close();

        return fileName + ".go";
    }
}
