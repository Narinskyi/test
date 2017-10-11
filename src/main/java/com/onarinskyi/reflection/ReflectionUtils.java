package com.onarinskyi.reflection;

import com.onarinskyi.annotations.Url;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ReflectionUtils {

    private static List<Class<?>> getClassesFromPackage(String basePackage) {
        List<Class<?>> result = new ArrayList<>();
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        Set<BeanDefinition> beans = provider.findCandidateComponents(basePackage);
        for (BeanDefinition beanDefinition : beans) {
            try {
                result.add(Class.forName(beanDefinition.getBeanClassName()));
            } catch (ClassNotFoundException ignored) {}
        }
        return result;
    }

    public static void instantiateAnnotatedFields(String packageName, Class<? extends Annotation> annotation) {
        List<Class<?>> classes = getClassesFromPackage(packageName);
        for (Class<?> clazz : classes) {
            List<Field> annotatedFields = FieldUtils.getFieldsListWithAnnotation(clazz, annotation);
            annotatedFields.forEach(field -> {
                try {
                    field.get(clazz.newInstance());
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static String getUrlAnnotationValue(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Url.class)) {
            return clazz.getAnnotation(Url.class).value();
        }
        return "";
    }
}
