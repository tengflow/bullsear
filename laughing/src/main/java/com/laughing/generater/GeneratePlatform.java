package com.laughing.generater;

import java.io.File;
import java.util.Arrays;

import com.laughing.generater.codebricks.entity.parser.ClassParser;
import com.laughing.generater.codebricks.entity.parser.DataBaseTableParser;
import com.laughing.generater.codebricks.entity.parser.PdmFileParser;
import com.laughing.generater.codebricks.httpapi.parser.XmlParser;
import com.laughing.generater.codebricks.parser.Parser;
import com.laughing.generater.processer.Processer;
import com.laughing.generater.template.anlyzer.TemplateAnlyzer;

/**
 * 
 * 用于生成一个实体的增删改查，需要给定以下的一种：
 * 1.实体类定义
 * 2.数据库链接方式和表名
 * 3.pdm文件
 * GeneratePlatform
 *
 * @author yongteng.huo 2015年5月13日
 * @see
 * @since 1.0
 */
public class GeneratePlatform {
    private static String P=GeneratePlatform.class.getClassLoader().getResource("").getPath();
    private static String O="F:/tmp/code/";
    public static void main(String[] args) {
        entityCodeByClass();
//        entityCodeByTable();
//        entityCodeByPdm();
//        httpApiCodeByXml();
    }
    protected static void httpApiCodeByXml() {
        String templatePath="http-api-template";
        Parser p=new XmlParser(new File("F:/tmp/退款明细.xml"));
        new Processer(O, p, TemplateAnlyzer.analyze(P+templatePath)).process();
        System.out.println("over~");
    }
    protected static void entityCodeByTable() {
        String templatePath="yeepay-hotel-template";
        Parser p=new DataBaseTableParser("ec",new String[]{"TBL_CHECKIN_ORDER"});
        new Processer(O, p,TemplateAnlyzer.analyze(P+templatePath)).process();
        System.out.println("over~");
    }

    protected static void entityCodeByPdm() {
        String templatePath="yeepay-hotel-template";
        Parser p=new PdmFileParser("invite",new File("F:/tmp/1.pdm"));
        new Processer(O, p, TemplateAnlyzer.analyze(P+templatePath)).process();
        System.out.println("over~");
    }
    protected static void entityCodeByClass() {
        String templatePath="yeepay-hotel-template";
//        Parser p=new ClassParser("room", Arrays.asList(new Class[]{Room.class}));
//        Parser p=new ClassParser("hotel", Arrays.asList(new Class[]{HotelConfig.class}));
        Parser p=new ClassParser("common", Arrays.asList(new Class[]{Temp.class}));
        new Processer(O, p, TemplateAnlyzer.analyze(P+templatePath)).process();
        System.out.println("over~");
    }
}
