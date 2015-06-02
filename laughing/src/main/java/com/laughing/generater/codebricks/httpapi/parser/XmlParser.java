package com.laughing.generater.codebricks.httpapi.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.laughing.generater.codebricks.httpapi.data.ApiInfo;
import com.laughing.generater.codebricks.parser.Parser;
import com.laughing.util.JsonUtils2;

/**
 * XmlParser
 *
 * @author yongteng.huo 2015年5月13日
 * @see
 * @since 1.0
 */
public class XmlParser implements Parser{
    private File defineXmlFile=null;
    /**
     * 不要太复杂哦
     * @param pdm
     */
    public XmlParser(File defineXmlFile) {
       this.defineXmlFile=defineXmlFile;
    }
    public List<Context> parse(){
        Context context=new VelocityContext();
        Map<String, Object> l=JsonUtils2.pojo2map(generateApiInfo());
        for(Entry<String, Object> s:l.entrySet()){
            context.put(s.getKey(),s.getValue());
        }
        return Arrays.asList(new Context[]{context});
    }
    private ApiInfo generateApiInfo() {
        ApiInfo api=new ApiInfo();
        try {
            Document root=DocumentHelper.parseText(FileUtils.readFileToString(defineXmlFile));
            api.setApiName(root.selectSingleNode("/root/apiName").getText());
            api.setAddress(root.selectSingleNode("/root/address").getText());
            api.setAddressForTest(root.selectSingleNode("/root/addressForTest").getText());
            api.setAskDataType(root.selectSingleNode("/root/askDataType").getText());
            api.setReplyDataType(root.selectSingleNode("/root/replyDataType").getText());
            
            Element ask=(Element)root.selectSingleNode("/root/askAttrs");
            List<String> askAttrs=new ArrayList<String>();
            for(Object x:ask.elements()){
                askAttrs.add(((Element)x).getName());
            }
            api.setAskAttrs(askAttrs);
            
            Element reply=(Element)root.selectSingleNode("/root/replyAttrs");
            List<String> replyAttrs=new ArrayList<String>();
            for(Object x:reply.elements()){
                replyAttrs.add(((Element)x).getName());
            }
            api.setReplyAttrs(replyAttrs);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return api;
    }
}
