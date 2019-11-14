package com.hx.common.utils;

import com.hx.common.exception.BDException;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * List Utils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-7-22
 */
public class ListUtils {

    /** default join separator **/
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    private ListUtils() {
        throw new AssertionError();
    }

    /**
     * get size of resList
     * 
     * <pre>
     * getSize(null)   =   0;
     * getSize({})     =   0;
     * getSize({1})    =   1;
     * </pre>
     * 
     * @param <V>
     * @param sourceList
     * @return if resList is null or empty, return 0, else return {@link List#size()}.
     */
    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    /**
     * is null or its size is 0
     * 
     * <pre>
     * isEmpty(null)   =   true;
     * isEmpty({})     =   true;
     * isEmpty({1})    =   false;
     * </pre>
     * 
     * @param <V>
     * @param sourceList
     * @return if resList is null or its size is 0, return true, else return false.
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     *
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> boolean isNotEmpty(List<V> sourceList) {
        return !isEmpty(sourceList);
    }

    /**
     * compare two resList
     * 
     * <pre>
     * isEquals(null, null) = true;
     * isEquals(new ArrayList&lt;String&gt;(), null) = false;
     * isEquals(null, new ArrayList&lt;String&gt;()) = false;
     * isEquals(new ArrayList&lt;String&gt;(), new ArrayList&lt;String&gt;()) = true;
     * </pre>
     * 
     * @param <V>
     * @param actual
     * @param expected
     * @return
     */
    public static <V> boolean isEquals(ArrayList<V> actual, ArrayList<V> expected) {
        if (actual == null) {
            return expected == null;
        }
        if (expected == null) {
            return false;
        }
        if (actual.size() != expected.size()) {
            return false;
        }

        for (int i = 0; i < actual.size(); i++) {
            if (!ObjectUtils.isEquals(actual.get(i), expected.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * join resList to string, separator is ","
     * 
     * <pre>
     * join(null)      =   "";
     * join({})        =   "";
     * join({a,b})     =   "a,b";
     * </pre>
     * 
     * @param resList
     * @return join resList to string, separator is ",". if resList is empty, return ""
     */
    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    /**
     * join resList to string
     * 
     * <pre>
     * join(null, '#')     =   "";
     * join({}, '#')       =   "";
     * join({a,b,c}, ' ')  =   "abc";
     * join({a,b,c}, '#')  =   "a#b#c";
     * </pre>
     * 
     * @param resList
     * @param separator
     * @return join resList to string. if resList is empty, return ""
     */
    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[] {separator}));
    }
    /**
     * Returns a string containing the tokens joined by delimiters.
     * @param tokens an array objects to be joined. Strings will be formed from
     *     the objects by calling object.toString().
     */
    public static String join(CharSequence delimiter, Object[] tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token: tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }
    /**
     * Returns a string containing the tokens joined by delimiters.
     * @param tokens an array objects to be joined. Strings will be formed from
     *     the objects by calling object.toString().
     */
    public static String join(CharSequence delimiter, Iterable tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token: tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }
    /**
     * join resList to string. if separator is null, use {@link #DEFAULT_JOIN_SEPARATOR}
     * 
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     * 
     * @param resList
     * @param separator
     * @return join resList to string with separator. if resList is empty, return ""
     */
    public static String join(List<String> list, String separator) {
        return list == null ? "" : join(separator, list);
    }
    
    public static String joinInteger(List<Integer> list, String delimiter) {
    	if(list==null) {
    		return null;
    	}
    	StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        Iterable tokens  = list;
        for (Object token: tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
    	return sb.toString();
    }
    
    /**
     * 取交集
     * @return
     */
    public static <T> List<T> intersection(List<T> src1, List<T> src2) {
    	List<T> list = new ArrayList<>();
    	if(src1!=null && src2!=null) {
    		for(T item : src1) {
    			if(item!=null && src2.contains(item)) {
    				list.add(item);
    			}
    		}
    		return list;
    	}
    	return null;
    }

    /**
     * add distinct entry to resList
     * 
     * @param <V>
     * @param sourceList
     * @param entry
     * @return if entry already exist in sourceList, return false, else add it and return true.
     */
    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        return (sourceList != null && !sourceList.contains(entry)) ? sourceList.add(entry) : false;
    }

    /**
     * add all distinct entry to list1 from list2
     * 
     * @param <V>
     * @param sourceList
     * @param entryList
     * @return the count of entries be added
     */
    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if (sourceList == null || isEmpty(entryList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        for (V entry : entryList) {
            if (!sourceList.contains(entry)) {
                sourceList.add(entry);
            }
        }
        return sourceList.size() - sourceCount;
    }

    /**
     * remove duplicate entries in resList
     * 
     * @param <V>
     * @param sourceList
     * @return the count of entries be removed
     */
    public static <V> int distinctList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return 0;
        }

        int sourceCount = sourceList.size();
        int sourceListSize = sourceList.size();
        for (int i = 0; i < sourceListSize; i++) {
            for (int j = (i + 1); j < sourceListSize; j++) {
                if (sourceList.get(i).equals(sourceList.get(j))) {
                    sourceList.remove(j);
                    sourceListSize = sourceList.size();
                    j--;
                }
            }
        }
        return sourceCount - sourceList.size();
    }

    /**
     * add not null entry to resList
     * 
     * @param sourceList
     * @param value
     * @return <ul>
     *         <li>if sourceList is null, return false</li>
     *         <li>if value is null, return false</li>
     *         <li>return {@link List#add(Object)}</li>
     *         </ul>
     */
    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        return (sourceList != null && value != null) ? sourceList.add(value) : false;
    }

    /**
     * @see {@link ArrayUtils#getLast(Object[], Object, Object, boolean)} defaultValue is null, isCircle is true
     */
    @SuppressWarnings("unchecked")
    public static <V> V getLast(List<V> sourceList, V value) {
        return (sourceList == null) ? null : (V)ArrayUtils.getLast(sourceList.toArray(), value, true);
    }

    /**
     * @see {@link ArrayUtils#getNext(Object[], Object, Object, boolean)} defaultValue is null, isCircle is true
     */
    @SuppressWarnings("unchecked")
    public static <V> V getNext(List<V> sourceList, V value) {
        return (sourceList == null) ? null : (V)ArrayUtils.getNext(sourceList.toArray(), value, true);
    }

    /**
     * invert resList
     * 
     * @param <V>
     * @param sourceList
     * @return
     */
    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }

        List<V> invertList = new ArrayList<V>(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        return invertList;
    }
    
    @SuppressWarnings("rawtypes")
    public static List getFiledList(List list, String filed) throws BDException {
        if (CollectionUtils.isEmpty(list))
            return null;
        List filedList = new ArrayList();
        try {
            for (Object obj : list) {
                Class clazz = obj.getClass();// 获取集合中的对象类型
                Method method;
                method = clazz.getMethod("get" + change(filed), null);
                Object val = method.invoke(obj, null);
                if (val == null || "".equals(val)) {
                    continue;
                }
                filedList.add(val);
            }
        } catch (Exception e) {
           throw new BDException("未找到该参数的get方法");
        }

        return filedList;
    }

    /**
     * @author zhouwei
     * 
     * @param src
     *            源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
     */
    public static String change(String src) {
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }
    /**
     * @author zhouwei
     * @param <K>
     * 
     * @param param 集合
     * @param fieldKey  将集合中的参数按某个字段作为key进行分类 - list中 该key存在多个对象
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <K, T> Map<K, List<T>> getClassificationMapList(List<T> param,String fieldKey) throws Exception{
        if (isEmpty(param)) {
           return new HashMap<>();
        }
        if (StringUtils.isBlank(fieldKey)) {
            throw new Exception("需要按条件进行分类的字段参数为空");
        }
        Map<K, List<T>> map = new HashMap<>();
        try{
            for (T fpt : param) {
                Method method = fpt.getClass().getMethod("get" + ListUtils.change(fieldKey),
                                null);
                if (method == null) {
                }
                K invoke = (K)method.invoke(fpt, null);
                List<T> list = map.get(invoke);
                if (isEmpty(list)) {
                    list = new ArrayList<>();
                    map.put(invoke, list);
                }
                list.add(fpt);
            }
        }catch (Exception e) {
            throw new Exception("未获取到对象中该字段的get方法,或执行该方法出错");
        }
        return map;
    }

    /**
     * @author zhouwei
     * @param <K>
     *
     * @param param 集合
     * @param fieldKey  将集合中的参数某个字段作为key进行分类- list中每个对象是某一个
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <K, T> Map<K, T> getClassificationMapObject(List<T> param, String fieldKey) throws Exception{
        if (isEmpty(param)) {
            return new HashMap<>();
        }
        if (StringUtils.isBlank(fieldKey)) {
            throw new Exception("需要按条件进行分类的字段参数为空");
        }
        Map<K, T> map = new HashMap<>();
        try{
            for (T fpt : param) {
                Method method = fpt.getClass().getMethod("get" + ListUtils.change(fieldKey),
                        null);
                if (method == null) {
                }
                K invoke = (K)method.invoke(fpt, null);
                map.put(invoke,fpt);
            }
        }catch (Exception e) {
            throw new Exception("未获取到对象中该字段的get方法,或执行该方法出错");
        }
        return map;
    }
}
