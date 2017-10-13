package com.onarinskyi.utils;

import com.onarinskyi.core.Environment;
import com.onarinskyi.interfaces.Page;
import com.onarinskyi.reflection.Reflection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

class UrlResolver {

    private static final Logger log = Logger.getAnonymousLogger();

    static String resolveUrlFor(Page page) {
        try {
            return getPropertiesBasedUrl(page);
        } catch (MalformedURLException e1) {
            log.warning("Application base url in application.properties is malformed. Please review it!");
            try {
                return getClassAnnotationBasedUrl(page);
            } catch (MalformedURLException e2) {
                log.warning("Application url in class: " + page.getClass().getSimpleName() + " is malformed. Please review it!");
                throw new RuntimeException("No valid URL was found in page declaration or application.properties file");
            }
        }
    }

    private static String getPropertiesBasedUrl(Page page) throws MalformedURLException {
        String baseUrl = Environment.getBaseUrl();
        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        new URL(baseUrl);

        String currentPageUrlAnnotationValue = Reflection.getUrlAnnotationValue(page.getClass());
        String currentPageFullUrl = currentPageUrlAnnotationValue.contains("http") ?
                baseUrl + currentPageUrlAnnotationValue.substring(currentPageUrlAnnotationValue.lastIndexOf("/")) :
                baseUrl + currentPageUrlAnnotationValue;
        currentPageFullUrl = currentPageFullUrl.replaceAll("//", "/");
        new URL(currentPageFullUrl);

        return currentPageFullUrl;
    }

    private static String getClassAnnotationBasedUrl(Page page) throws MalformedURLException {
        return new URL(Reflection.getUrlAnnotationValue(page.getClass())).toString();
    }
}
