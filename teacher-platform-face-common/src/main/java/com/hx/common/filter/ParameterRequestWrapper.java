package com.hx.common.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author zw
 * 包装类 -- 处理页面所有参数
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String , String[]> params = new HashMap<String, String[]>();
    private static final String REG_EX = "[`~!#$%^&*()=|{}';'\\[\\]<>?~！#￥%……&*（）——|{}【】‘；：”“’。、？\\s+]";
    private static final Pattern p = Pattern.compile(REG_EX);
    @SuppressWarnings("unchecked")
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
        this.modifyParameterValues();
    }
    //重载一个构造方法
    public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
        this(request);
        addAllParameters(extendParams);
    }

    public void modifyParameterValues(){
        Set<String> set =params.keySet();
        if (set != null && set.size() >0) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String key = it.next();
                String[] values = params.get(key);
                if (values!= null && values.length >0) {
                    values[0] = stringFilter(values[0]);//过滤特殊字符
                }
                params.put(key, values);
            }
        }
    }

    @Override
    public String getParameter(String name) {
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public void addAllParameters(Map<String , Object>otherParams) {
        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }


    public void addParameter(String name , Object value) {
        if(value != null) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }

    // 过滤特殊字符
    public static String stringFilter(String str) throws PatternSyntaxException {
        // 清除掉所有特殊字符
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}