package com.control.life.home.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * Created on 24.03.16.
 */
@Component
public class ModifyInputContent {

    public static String modifyEnterName(String string) {
        string = string.trim();
        string = string.replaceAll(" +", "\\ ");
        return StringUtils.capitalize(string.toLowerCase());
    }
}
