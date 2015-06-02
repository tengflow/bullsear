package com.laughing.generater.template.model;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.Context;

import com.laughing.generater.processer.velocity.VelocityTools;
import com.laughing.util.JsonUtils2;

/**
 * 代码模版文件解析后的实体类
 * Template
 * 
 * @author yongteng.huo 2015年2月25日
 * @see
 * @since 1.0
 */
public class Item {
    /**
     * 模版文件全路径
     */
    private String templateFilePath;
    /**
     * 模版配置项，默认设置，json字符串，相关项，会加入到data的context中
     * ，如 ｛version:"1.0",module:"a",paramA:"parama",paramB:"paramb"｝
     */
    private String optionsFormula;
    /**
     * 生成的文件的保存的相对路径模版
     */
    private String pathFormula;
    /**
     * 文件内容模版
     */
    private String contentFormula;

    public Item() {
        super();
    }

    public Item(String tFilePath,String optionsFormula, String pathFormula, String contentFormula) {
        super();
        this.templateFilePath=tFilePath;
        this.optionsFormula = optionsFormula;
        this.pathFormula = pathFormula;
        this.contentFormula = contentFormula;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }

    public String getOptionsFormula() {
        return optionsFormula;
    }

    public void setOptionsFormula(String optionsFormula) {
        this.optionsFormula = optionsFormula;
    }

    public String getPathFormula() {
        return pathFormula;
    }

    public void setPathFormula(String pathFormula) {
        this.pathFormula = pathFormula;
    }

    public String getContentFormula() {
        return contentFormula;
    }

    public void setContentFormula(String contentFormula) {
        this.contentFormula = contentFormula;
    }

    /**
     * 根据内容生成code文件
     * @param context
     */
    public void evalute(Context context,String outputPath) {
        try {
            String options=VelocityTools.evaluateFormula(context, this.getOptionsFormula());
            if(StringUtils.isNotEmpty(options)){
                Map<String,Object> optionsMap=JsonUtils2.json2map(options);
                for(Map.Entry<String,Object> es:optionsMap.entrySet()){
                    context.put(es.getKey(), es.getValue());
                }
            }
            String filepath=VelocityTools.evaluateFormula(context, this.getPathFormula());
            String content=VelocityTools.evaluateFormula(context, this.getContentFormula());
            FileUtils.writeStringToFile(new File(outputPath+filepath), content, "UTF-8");
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

}
