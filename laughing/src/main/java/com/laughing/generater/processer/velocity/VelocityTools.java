package com.laughing.generater.processer.velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

public class VelocityTools {
    static{
        Properties prop=new Properties();
        try {
            prop.load(VelocityTools.class.getClassLoader().getResourceAsStream("velocity.properties"));
        } catch (IOException e) {}
        Velocity.init(prop);
    }
    public VelocityTools() {}

    public static String evaluateFormula(Context context,String template) throws Exception{
        StringWriter fWriter=new StringWriter();
        Velocity.evaluate(context, fWriter, "", template);
        fWriter.close();
        return fWriter.toString();
    }
}
