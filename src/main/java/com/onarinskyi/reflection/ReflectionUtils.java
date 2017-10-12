package com.onarinskyi.reflection;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.annotations.Url;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.cglib.core.ReflectUtils;

import java.lang.reflect.Field;
import java.util.List;


public class ReflectionUtils {

    public static void instantiatePageObjectFields(Object targetObject) {
        List<Field> annotatedFields = FieldUtils.getFieldsListWithAnnotation(targetObject.getClass(), PageObject.class);
        annotatedFields.forEach(field -> {
            try {
                field.setAccessible(true);
                field.set(targetObject, ReflectUtils.newInstance(field.getType()));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });

    }

    public static String getUrlAnnotationValue(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Url.class)) {
            return clazz.getAnnotation(Url.class).value();
        }
        return "";
    }
}