package com.onarinskyi.framework.reflection;

import com.onarinskyi.framework.annotations.FindBy;
import com.onarinskyi.framework.annotations.Url;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.cglib.core.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;


public class Reflection {

    public static void instantiateAnnotatedField(Object targetObject, Class<? extends Annotation> annotation) {
        List<Field> annotatedFields = FieldUtils.getFieldsListWithAnnotation(targetObject.getClass(), annotation);
        annotatedFields.forEach(field -> {
            try {
                field.setAccessible(true);
                field.set(targetObject, ReflectUtils.newInstance(field.getType()));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });
    }

    public static void instantiateFieldsAnnotatedWithBy(Object targetObject) {
        List<Field> annotatedFields = FieldUtils.getFieldsListWithAnnotation(targetObject.getClass(), FindBy.class);
        annotatedFields.forEach(field -> {
            try {
                field.setAccessible(true);
                field.set(targetObject, getByLocatorOf(field));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });
    }

    private static org.openqa.selenium.By getByLocatorOf(Field field) {
        FindBy findByAnnotation = field.getAnnotation(FindBy.class);
        return findByAnnotation.id().isEmpty() ?
                findByAnnotation.name().isEmpty() ?
                        findByAnnotation.css().isEmpty() ?
                                findByAnnotation.xpath().isEmpty() ? null :
                                        org.openqa.selenium.By.xpath(findByAnnotation.xpath()) :
                                org.openqa.selenium.By.cssSelector(findByAnnotation.css()) :
                        org.openqa.selenium.By.name(findByAnnotation.name()) :
                org.openqa.selenium.By.id(findByAnnotation.id());
    }

    public static String getUrlAnnotationValue(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Url.class)) {
            return clazz.getAnnotation(Url.class).value();
        }
        return "";
    }
}
