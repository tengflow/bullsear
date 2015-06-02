package com.laughing.generater.processer.velocity.expand;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.ArrayUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

/**
 * LowerAll
 *全部变为小写
 * @author yongteng.huo 2014年12月8日
 * @see
 * @since 1.0
 */
public class InvokeType extends Directive{

    @Override
    public String getName() {
        return "invoketype";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException,
            ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        String inCase=(String) node.jjtGetChild(0).value(context);
        String inLowerCase=inCase.toLowerCase();
        String result=null;
        if(ArrayUtils.contains(new String[]{"varchar","string","char"}, inLowerCase)){
            result="String";
        }else if (ArrayUtils.contains(new String[]{"integer","int"}, inLowerCase)) {
            result="Integer";
        }else if (ArrayUtils.contains(new String[]{"long"}, inLowerCase)) {
            result="Long";
        }else if (ArrayUtils.contains(new String[]{"double","number"}, inLowerCase)) {
            result="Double";
        }else if (ArrayUtils.contains(new String[]{"boolean"}, inLowerCase)) {
            result="Boolean";
        }else if (ArrayUtils.contains(new String[]{"time","date","datetime","timestamp"}, inLowerCase)) {
            result="TimeStamp";
        }else{
            result=inCase;
        }
        writer.write(result);
        return false;
    }

}
