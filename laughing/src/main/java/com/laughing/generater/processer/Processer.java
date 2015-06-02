package com.laughing.generater.processer;

import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import com.laughing.generater.codebricks.parser.Parser;
import com.laughing.generater.template.model.Item;
import com.laughing.generater.template.model.Template;

/**
 * Generater
 * 代码生成器
 * @author yongteng.huo 2015年3月6日
 * @see
 * @since 1.0
 */
public class Processer {
    //生成Code文件的保存路径
    protected   String outputPath;
    //解析class或者pdm的parser哦
    protected   Parser parser;
    //模版
    protected   Template template;
    
    public Processer(String outputPath, Parser parser, Template template) {
        super();
        this.outputPath = outputPath;
        this.parser = parser;
        this.template = template;
    }
    /**
     * 开始生成！
     */
    public void process(){
        //数据们，每个表、类一坨数据。使用一次模版
        for(Context data:parser.parse()){
            //数据转成veloctity可识别的数据
            Context localVarible=dealVaribleScope(data);
            //模版转化为code
            for (Item t: template.getItems()) {
                try {
                    t.evalute(localVarible,outputPath);
                } catch (Exception e) {
                    System.out.println("改某模版时出错:"+t.getTemplateFilePath());
                }
            }
            //本局部用到的变量
            for(Object o:localVarible.getKeys()){
                System.out.println(o+"---"+localVarible.get((String)o));
            }
        }
    }
    /**
     * 扩展数据作用域
     * @param local
     * @return
     */
    protected Context dealVaribleScope(Context local) {
        //TODO 未来可考虑考虑下多个context相互引用的情况
        Context global=new VelocityContext();
        //全局变量 加载模版全局自定义配置。
        for(Map.Entry<String,Object> es:template.getOptions().entrySet()){
            global.put(es.getKey(), es.getValue());
        }
        //局部变量
        for(Object key:local.getKeys()){
            global.put((String)key,local.get((String)key));
        }
        return global;
    }
    public String getOutputPath() {
        return outputPath;
    }
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
    public Parser getParser() {
        return parser;
    }
    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
