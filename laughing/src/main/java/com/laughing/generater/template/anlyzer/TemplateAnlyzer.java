package com.laughing.generater.template.anlyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.laughing.generater.template.model.Item;
import com.laughing.generater.template.model.Template;
import com.laughing.util.JsonUtils2;

/**
 * TemplateReader
 *
 * @author yongteng.huo 2015年5月13日
 * @see
 * @since 1.0
 */
public class TemplateAnlyzer {

    /**
     * 分析单个文件
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Item analyze(File file) throws FileNotFoundException, IOException {
        String optionsFormula=null;
        String filePathFormula = null;
        InputStream inputStream=new FileInputStream(file);
        List<String> lines = IOUtils.readLines(inputStream, "UTF-8");
        if (lines.size() >= 1) {
            optionsFormula=lines.get(0);
            filePathFormula=lines.get(1);
        }
        StringBuffer fileContentFormula=new StringBuffer();
        for(int i=2;i<lines.size();i++){
            fileContentFormula.append(lines.get(i)+"\r\n");
        }
        return new Item(file.getAbsolutePath(),optionsFormula,filePathFormula,fileContentFormula.toString());
    }
    /**
     * 分析目录下所有模版（.t结束的文件）
     * @param templatesPath
     * @return
     */
    public static Template analyze(String templatesPath) {
        Template t=new Template();
        List<Item> items=new ArrayList<Item>();
        //模版文件们
        Object[] templateFiles= FileUtils.listFiles(new File(templatesPath), new String[]{"t"},true).toArray();
        //按序号从小到大排序
        Arrays.sort(templateFiles, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                return ((File)arg0).getName().compareTo(((File)arg1).getName());
            }
        });
        //模版转为实体Object
        for(Object templateFile:templateFiles){
            try {
                items.add(analyze((File)templateFile));
            }catch(Exception e) {//没有找到指定的模版文件或者读取错误
                System.out.println("没有找到指定的模版文件或者读取错误,filePath:"+((File)templateFile).getAbsolutePath());
            }
        }
        t.setItems(items);
        //加载模版自定义全局配置。
        try {
            Map<String,Object> options=JsonUtils2.json2map(FileUtils.readFileToString(new File(templatesPath+"/template-gloabl-options"), "UTF-8"));
            t.setOptions(options);
        } catch (IOException e) {//配置文件不存在或加载错误
            System.out.println("没有在模版目录下找到全局配置文件或加载错误。忽略之");
        }
        
        return t;
    }
}
