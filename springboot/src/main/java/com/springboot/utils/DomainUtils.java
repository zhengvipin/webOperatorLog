package com.springboot.utils;

import com.springboot.domain.Change;
import com.springboot.domain.ChangeLog;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DomainUtils<T> {

    /**
     * 判断对象编辑前后是否发生变化
     *
     * @param before 编辑前的对象
     * @param after  编辑后的对象
     * @return 是否发生变化
     * @throws Exception 异常
     */
    public boolean isNotChanged(T before, T after) throws Exception {
        // 声明返回值，默认无变化
        boolean isNotChanged = true;
        // 获取class字节码
        Class clazz = before.getClass();
        // 获得所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 获得属性描述器
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            // 获得用于读取属性值的方法
            Method getMethod = pd.getReadMethod();
            // 读取属性值
            Object beValue = getMethod.invoke(before);
            Object afValue = getMethod.invoke(after);

            if (field.getType().isInstance(new Date())) { // 日期类型特殊处理
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                beValue = sdf.format(beValue);
                afValue = sdf.format(afValue);
            }
            // 对比
            if (!beValue.toString().equals(afValue.toString())) {
                isNotChanged = false;
            }
        }
        return isNotChanged;
    }

    /**
     * 记录某对象编辑前后字段的变化
     *
     * @param before    编辑前的对象
     * @param after     编辑后的对象
     * @param paramList 字段列名数组
     * @return 字段具体变化的日志
     * @throws Exception 异常
     */
    public ChangeLog getChangeLog(T before, T after, List<String> paramList) throws Exception {
        // 声明返回值
        ChangeLog changeLog = new ChangeLog();
        changeLog.setOperator("jack");//初始化
        changeLog.setOperateDate(new Date());//初始化
        // 获得class字节码
        Class clazz = before.getClass();
        // 获得所有属性
        Field[] fields = clazz.getDeclaredFields();

        List<Change> operateList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            // 获得属性描述器
            PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), clazz);
            // 获得读取属性的方法
            Method getMethod = pd.getReadMethod();
            // 读取属性值
            Object beValue = getMethod.invoke(before);
            Object afValue = getMethod.invoke(after);
            if (fields[i].getType().isInstance(new Date())) { // 日期类型特殊处理
                DateFormat dateFormat = new SimpleDateFormat("yyyy-Mm-dd");
                beValue = dateFormat.format(beValue);
                afValue = dateFormat.format(afValue);
            }
            // 判断前后是否发生变化
            if (!beValue.toString().equals(afValue.toString())) {
                Change change = new Change(paramList.get(i), beValue, afValue);
                operateList.add(change);
            }
        }
        changeLog.setOperateList(operateList);
        return changeLog;
    }

//    /**
//     * 记录某对象编辑前后字段的改变
//     *
//     * @param before     编辑前的对象
//     * @param after      编辑后的对象
//     * @param paramNames 对象列名数组
//     * @return 具体改变的记录
//     * @throws Exception 异常
//     */
//    private String saveChangeValue(T before, T after, List<String> paramNames) throws Exception {
//        // 声明返回值
//        StringBuilder result = new StringBuilder();
//        // 获取class字节码
//        Class clazz = before.getClass();
//        // 获取所有属性
//        Field[] fs = clazz.getDeclaredFields();
//
//        for (int i = 0; i < fs.length; i++) {
//            PropertyDescriptor pd = new PropertyDescriptor(fs[i].getName(), clazz);
//            Method getMethod = pd.getReadMethod();
//            Object o1 = getMethod.invoke(before);
//            Object o2 = getMethod.invoke(after);
//
//
//            if (!StringUtils.equals(o1.toString(), o2.toString())) {
//                result
//
//                        .append("编辑")
//                        .append(paramNames.get(i))
//                        .append("(")
//                        .append(getMethod.invoke(before))
//                        .append(")为(")
//                        .append(getMethod.invoke(after))
//                        .append(");");
//            }
//        }
//        return result.toString();
//    }

}
