package com.laughing.generater.processer.velocity.expand;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

/**
 * UpperAll
 *全部变为da写
 * @author yongteng.huo 2014年12月8日
 * @see
 * @since 1.0
 */
public class UpperFirst extends Directive{

    @Override
    public String getName() {
        return "upperfirst";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException,
            ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        writer.write(execute((String) node.jjtGetChild(0).value(context)));
        return false;
    }

    public static String execute(String inCase){
        if(StringUtils.isEmpty(inCase)) return inCase;
        return StringUtils.upperCase(String.valueOf(inCase.charAt(0)))+inCase.substring(1);
    }
}
