package com.control.life.home.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created on 1/17/17.
 */

@Component
public class SpringUtils {
    private static ApplicationContext context;

    public static <T> T getBean(Class<T> tClass) {
        return context.getBean(tClass);
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        SpringUtils.context = context;
    }
}
