package com.control.life.home.utils;

import org.springframework.stereotype.Component;


/**
 * Created on 24.03.16.
 */
@Component
public class ModifyInputContent {

    public static String modifyEnterName(String string) {
        string = string.trim();
        string = string.replaceAll(" +", "\\ ");
        return string;
    }
}
