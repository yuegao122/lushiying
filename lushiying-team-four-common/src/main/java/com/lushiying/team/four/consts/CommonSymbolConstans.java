package com.lushiying.team.four.consts;

import com.google.common.base.Splitter;

/**
 * @author yuzhibo
 * @date 2019/6/17 14:49
 */
public class CommonSymbolConstans {

    public static final String DEFAULT_PARAMETER = "default";

    public static final String COMMA = ",";

    public static final String PIPE = "|";

    public static final String EQUAL = "=";

    public static final String COLON = ":";

    public static final String POINT = ".";

    public static final String POUND_SIGN = "#";

    public static final String MUTI_SIGN = "#-#";

    public static final Splitter SPLITTER_COMMA = Splitter.on(COMMA).omitEmptyStrings().trimResults();

    public static final String UNDERLINE = "_";

    public static final String DUBBO_UNDERLINE = "__";
}
