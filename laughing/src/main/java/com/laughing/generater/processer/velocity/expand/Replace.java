package com.laughing.generater.processer.velocity.expand;


import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

/**
 * Replace
 *全部变为da写
 * @author yongteng.huo 2014年12月8日
 * @see
 * @since 1.0
 */
public class Replace extends Directive{

    @Override
    public String getName() {
        return "replace";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException,
            ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        String inCase=(String) node.jjtGetChild(0).value(context);
        String a=(String) node.jjtGetChild(1).value(context);
        String b=(String) node.jjtGetChild(2).value(context);
        writer.write(inCase.replaceAll(a, b));
        return false;
    }

}
