package com.onarinskyi.utils;

import com.onarinskyi.core.Environment;
import com.onarinskyi.interfaces.Page;
import com.onarinskyi.reflection.ReflectionUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

class UrlResolver {

    private static final Logger log = Logger.getAnonymousLogger();

    static String resolveUrlFor(Page page) {
        try {
            String baseUrl = Environment.getBaseUrl();
            baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
            new URL(baseUrl);

            String currentPageUrlAnnotationValue = ReflectionUtils.getUrlAnnotationValue(page.getClass());
            String currentPageFullUrl = currentPageUrlAnnotationValue.contains("http") ?
                    baseUrl + currentPageUrlAnnotationValue.substring(currentPageUrlAnnotationValue.lastIndexOf("/")) :
                    baseUrl + currentPageUrlAnnotationValue;
            currentPageFullUrl = currentPageFullUrl.replaceAll("//", "/");
            new URL(currentPageFullUrl);

            return currentPageFullUrl;
        } catch (MalformedURLException e1) {
            log.warning("Application base url in application.properties is malformed. Please review it!");
            try {
                return new URL(ReflectionUtils.getUrlAnnotationValue(page.getClass())).toString();
            } catch (MalformedURLException e2) {
                log.warning("Application url in class: " + page.getClass().getSimpleName() + " is malformed. Please review it!");
                throw new RuntimeException("No valid URL was found in page declaration or application.properties file");
            }
        }
    }
}
