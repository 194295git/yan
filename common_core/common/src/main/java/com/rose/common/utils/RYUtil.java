package com.rose.common.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RYUtil {

    /**
     * 日期格式 yyyy-MM-dd.
     */
    public static final String DATEFORMAT_YM = "yyyy-MM";
    /**
     * 日期格式 yyyy-MM-dd.
     */
    public static final String DATEFORMAT_YMD = "yyyy-MM-dd";

    /**
     * 日期格式 yyyy-MM-dd HH:mm:ss.
     */
    public static final String DATEFORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    @SuppressWarnings({ "unchecked" })
    public static <T> T safeConvert(final Object object, final Class<T> clazz) {
        if (object != null && !"".equals(object.toString())) {
            if (clazz == Integer.class) {
                return (T) Integer.valueOf(object.toString());
            }

            if (clazz == BigDecimal.class) {
                return (T) new BigDecimal(object.toString());
            }
            if (clazz == Date.class) {
                if (isValid(object.toString(), DATEFORMAT_YMDHMS)) {
                    return (T) getDateFormat(object.toString(), DATEFORMAT_YMDHMS);
                }
                if (isValid(object.toString(), DATEFORMAT_YMD)) {
                    return (T) getDateFormat(object.toString(), DATEFORMAT_YMD);
                }
                if (isValid(object.toString(), DATEFORMAT_YM)) {
                    return (T) getDateFormat(object.toString(), DATEFORMAT_YM);
                }

                return (T) object;
            }
            return (T) object;
        }

        if (clazz == BigDecimal.class) {
            return (T) new BigDecimal(0);
        }
        if (clazz == Integer.class) {
            return (T) new Integer(0);
        }
        if (clazz == String.class) {
            return (T) new String("");
        }

        return null;
    }
    /**
     * 用于判断数组是否为空，避免空指针异常.
     * @param list 传入数组
     * @return 布尔
     */
    public static boolean isEmptyArray(final List list) {
        return list != null && list.size() != 0;

    }

    /**
     * 正则判断字符串是否是金额格式.
     *
     * @param str str
     * @return 返回结果 是否
     */
    public static boolean isNumber(final String str) {
        final java.util.regex.Pattern pattern = java.util.regex.Pattern
                .compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        final java.util.regex.Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * 是否是日期格式.
     *
     * @param dateStr  2022-03-32   2022-02
     * @param pattern  格式类型
     * @return 布尔
     */
    public static boolean isValid(final String dateStr, final String pattern) {
        final DateFormat sDateFormat = new SimpleDateFormat(pattern);
        sDateFormat.setLenient(false);
        try {
            sDateFormat.parse(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;

    }
    /**
     * 将字符串转为Date.
     * @param dateString 日期字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date getDateFormat(final String dateString, final String pattern) {
        if (dateString == null && "".equals(dateString)) {
            return null;
        }
        try {
            return getFormat(pattern).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回SimpleDateFormat对象.
     * @param pattern 格式
     * @return SimpleDateFormat
     */
    public static DateFormat getFormat(final String pattern) {
        if (pattern == null && "".equals(pattern)) {
            return null;
        }
        return new SimpleDateFormat(pattern);
    }

    /**
     * 将时间戳转化为指定格式.
     * @param timeInMillis timeInMillis
     * @return String(yyyy-MM-dd HH:mm:ss)
     */
    public static String formatTimeInMillis(final long timeInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMillis);
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormatDate = dateFormat.format(date);

        return dateFormatDate;
    }

    /**
     * sql添加的时候使用这个方法.
     *
     * @param sb StringBuffer
     * @param sql String
     * @return
     */
    public static void appendPlus(final StringBuffer sb, final String sql) {
        sb.append(" " + sql + " ");

    }

    /**
     * sql添加and.
     *
     * @param sb sb
     * @param sql sql
     */
    public static void appendAndPlus(final StringBuffer sb, final String sql) {
        sb.append(" and " + sql + " ");
    }
    /**
     *
     * @param sb
     * @param sql
     * @return
     */
    public static List JsonToArray(final String json) {
        JSONArray array = (JSONArray) JSONArray.parse(json);
        List<Map> finalList = new ArrayList<Map>();

        for(int i = 0 ; i<array.size();i++){
            final JSONObject jsonObj = (JSONObject) JSONObject.parse(array.getString(i));
            Map<String, Object> userMap = new HashMap<String, Object>();
            //循环转换
            for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
                userMap.put(entry.getKey(), entry.getValue());
            }

            finalList.add(userMap);
        }
        return  finalList;
    }

    /**
     * 传入的格式应该是"2020-08".
     *
     * @param dateString dateString
     * @return String
     */
    public static String getLastDayOfMonth(final String dateString) {
        // 获取2020-02的最后一天
        // String dateString = "2020-08";
        // 获取年月
        final int year = Integer.parseInt(dateString.split("-")[0]);
        final int month = Integer.parseInt(dateString.split("-")[1]);
        // 获取 日历 对象
        final Calendar calendar = Calendar.getInstance();
        // 填充年
        calendar.set(Calendar.YEAR, year);
        // 填充月 calenda里的月份是0-11
        calendar.set(Calendar.MONTH, month - 1);

        // 获取2020-02日历的最大字段，也就是最大多少天
        final int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 填充 最后一天
        calendar.set(Calendar.DAY_OF_MONTH, maxDay);
        final String date = formatTimeInMillis(calendar.getTimeInMillis());
        return date;

    }

    /**
     * 将map转为bean .
     *
     * @param source
     *            map
     * @param target
     *            实体类
     * @throws IntrospectionException
     *             异常
     * @throws InvocationTargetException
     *             异常
     * @throws IllegalAccessException
     *             异常
     */
    public static void copyProperties2Bean(final Map<String, Object> source, final Object target)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        final BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
        final PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop : properties) {
            final String key = prop.getName().toUpperCase();
            final Class<?> propertyType = prop.getPropertyType();
            if (source.containsKey(key) && source.get(key) != null) {
                final Object value = source.get(key);
                final Method setMethod = prop.getWriteMethod();
                if (propertyType.toString().equals("class java.util.Date")) {
                    if (key != null && !"".equals(key)) {
                        final Date date = getDateFormat(value.toString(), DATEFORMAT_YMD);
                        setMethod.invoke(target, date);
                        continue;
                    }
                }
                if (propertyType.toString().equals("class java.math.BigDecimal")) {
                    if (key != null && !"".equals(key)) {
                        final BigDecimal amount = new BigDecimal(value.toString());
                        setMethod.invoke(target, amount);
                    }
                    continue;
                }
                if (propertyType.toString().equals("class java.lang.Integer")) {
                    if (key != null && !"".equals(key)) {
                        setMethod.invoke(target, Integer.valueOf(value.toString()));
                    }
                    continue;
                }
                setMethod.invoke(target, value);
            }
        }

    }

    /**
     * 判断传入的String[]是否不为空.
     *
     * @param arr
     *            数组
     * @return boolean
     */
    public boolean isNotEmpty(final String[] arr) {
        final boolean rs = true;
        for (String item : arr) {
            if (item != null && !"".equals(item)) {
                return false;
            }
        }
        return rs;

    }

}
