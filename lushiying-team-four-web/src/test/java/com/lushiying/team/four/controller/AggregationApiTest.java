package com.lushiying.team.four.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lianjia.mls.common.util.http.HttpResult;
import com.lianjia.mls.common.util.http.HttpUtil;
import com.lianjia.mls.datakeeper.housedel.model.utils.FilterUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Map;

/**
 * @author yuzhibo
 * @date 2019/7/10 19:42
 */
public class AggregationApiTest {

    private final static String APP_KEY = "devRD";

    private final static String APP_TOKEN = "78S2s28kf7p6Q89Hfj59";

    private final static String HTTP_2_DUBBO_URL = "http://preview-housedel.datakeeper.mls.lianjia.com/open/dk/api/http2dubbo/invoke";

    @Test
    public void test() throws Throwable{
        long timestamp = System.currentTimeMillis();
        Map<String,String> headers = Maps.newHashMap();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> paramMap = Maps.newHashMap();

        paramMap.put("appKey", APP_KEY);
        paramMap.put("signCode", FilterUtils.encode(APP_KEY, APP_TOKEN, timestamp));
        paramMap.put("timestamp", timestamp + "");
        paramMap.put("traceId", timestamp + "");
        paramMap.put("className", "com.lianjia.mls.datakeeper.housedel.facade.AggregationFacade");
        paramMap.put("method", "queryHouseByHouseParam");
        paramMap.put("dataPackage", "housedel_data,house_address_sensitive_data");
        JSONObject paramObj = new JSONObject();

        paramObj.put("housedelCodes", Lists.newArrayList(101105039509L));

        paramMap.put("param", paramObj.toJSONString());

        HttpResult httpResult = HttpUtil.post(HTTP_2_DUBBO_URL, headers, paramMap, "utf-8");
        System.out.println(httpResult.getBody());
    }
}
