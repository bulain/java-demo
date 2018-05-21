package com.bulain.zk.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.SimpleNode;
import ognl.TypeConverter;

public class OgnlUtil {

    private static ConcurrentHashMap<String, Object> expressions = new ConcurrentHashMap<String, Object>();
    private static boolean enableExpressionCache = true;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void setProperties(Map<String, ?> properties, Object o) throws OgnlException {
        Map context = Ognl.createDefaultContext(o);
        setProperties(properties, o, context);
    }

    private static void setProperties(Map<String, ?> props, Object o, Map<String, Object> context) throws OgnlException {
        if (props == null) {
            return;
        }

        TypeConverter typeConverter = new DefaultTypeConverter();
        Ognl.setTypeConverter(context, typeConverter);

        Object oldRoot = Ognl.getRoot(context);
        Ognl.setRoot(context, o);

        for (Map.Entry<String, ?> entry : props.entrySet()) {
            String expression = entry.getKey();
            setValue(expression, context, o, entry.getValue(), true);
        }

        Ognl.setRoot(context, oldRoot);
    }

    private static void setValue(String name, Map<String, Object> context, Object root, Object value, boolean evalName)
            throws OgnlException {
        Object tree = compile(name);
        if (!evalName && isEvalExpression(tree, context)) {
            throw new OgnlException("Eval expression cannot be used as parameter name");
        }
        Ognl.setValue(tree, context, root, value);
    }

    private static boolean isEvalExpression(Object tree, Map<String, Object> context) throws OgnlException {
        if (tree instanceof SimpleNode) {
            SimpleNode node = (SimpleNode) tree;
            return node.isEvalChain((OgnlContext) context);
        }
        return false;
    }

    public static Object compile(String expression) throws OgnlException {
        if (enableExpressionCache) {
            Object o = expressions.get(expression);
            if (o == null) {
                o = Ognl.parseExpression(expression);
                expressions.put(expression, o);
            }
            return o;
        } else
            return Ognl.parseExpression(expression);
    }

}
