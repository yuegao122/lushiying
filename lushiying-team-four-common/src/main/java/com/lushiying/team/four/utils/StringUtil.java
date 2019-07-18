package com.lushiying.team.four.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuzhibo
 * @date 2019/6/18 16:34
 */
public class StringUtil {

    /**
     * 去除字符串中的[]
     * @param data
     * @return
     */
    public static String removeSquareBrackets(String data){
        if (StringUtils.isEmpty(data)){
            return StringUtils.EMPTY;
        }

        return data.replaceAll("\\[","").replaceAll("\\]","");
    }
}
