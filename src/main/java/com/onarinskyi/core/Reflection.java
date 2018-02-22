package com.onarinskyi.core;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.annotations.Url;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class Reflection {

    static void instantiateAnnotatedFields(Object targetObject) {
        instantiateAnnotatedField(targetObject, PageObject.class, PageComponent.class, FindBy.class);
    }

    @SafeVarargs
    private static void instantiateAnnotatedField(Object targetObject, Class<? extends Annotation>... annotations) {
        for (Class<? extends Annotation> annotation : annotations) {
            List<Field> annotatedFields = FieldUtils.getFieldsListWithAnnotation(targetObject.getClass(), annotation);
            annotatedFields.forEach(field -> {
                try {
                    field.setAccessible(true);
                    switch (annotation.getSimpleName()) {
                        case "FindBy":
                            field.set(targetObject, getByLocatorOf(field));
                            break;
                        case "PageComponent":
                            field.set(targetObject, getPageComponent(field));
                            break;
                        default:
                            field.set(targetObject, ReflectUtils.newInstance(field.getType()));
                            break;
                    }
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static Object getPageComponent(Field field) throws ReflectiveOperationException {
        PageComponent findByAnnotation = field.getAnnotation(PageComponent.class);
        By locator = findByAnnotation.id().isEmpty() ?
                findByAnnotation.name().isEmpty() ?
                        findByAnnotation.css().isEmpty() ?
                                findByAnnotation.xpath().isEmpty() ? null :
                                        By.xpath(findByAnnotation.xpath()) :
                                By.cssSelector(findByAnnotation.css()) :
                        By.name(findByAnnotation.name()) :
                By.id(findByAnnotation.id());
        return locator == null ?
                ReflectUtils.newInstance(field.getType()) :
                field.getType().getDeclaredConstructor(By.class).newInstance(locator);
    }

    private static By getByLocatorOf(Field field) {
        FindBy findByAnnotation = field.getAnnotation(FindBy.class);
        return findByAnnotation.id().isEmpty() ?
                findByAnnotation.name().isEmpty() ?
                        findByAnnotation.css().isEmpty() ?
                                findByAnnotation.xpath().isEmpty() ? null :
                                        By.xpath(findByAnnotation.xpath()) :
                                By.cssSelector(findByAnnotation.css()) :
                        By.name(findByAnnotation.name()) :
                By.id(findByAnnotation.id());
    }

    public static String getUrlAnnotationValue(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Url.class)) {
            return clazz.getAnnotation(Url.class).value();
        }
        return "";
    }
}
