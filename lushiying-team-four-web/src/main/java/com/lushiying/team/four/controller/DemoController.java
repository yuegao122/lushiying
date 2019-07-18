package com.lushiying.team.four.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhibo
 * @date 2019/7/6 11:18
 */
@RestController
@RequestMapping("/test/path")
@Slf4j
public class DemoController {

    private String getUrl = "http://10.26.15.71:4321/auth/getAuthByAppkeyAndSecret";

    private String postUrl = "http://10.26.28.55:20236/open/dk/api/http2dubbo/invoke";

    private String appKey = "quality.rent.ke.com";

    private String secret = "q9wzEQChA01n1W63P40c";

    //最大连接数
    private static final int maxTotal = 100;
    //最大路由连接数
    private static final int maxPerRouter = 40;
    //超时时间
    private static final int DEFAULT_TIME_OUT = 2 * 1000;

    private static final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager() {{
        setDefaultMaxPerRoute(maxPerRouter);
        setMaxTotal(maxTotal);
    }};

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(DEFAULT_TIME_OUT)
            .setConnectTimeout(DEFAULT_TIME_OUT)
            .build();

    @GetMapping(value = "/query")
    public String queryHttp2Dubbo() {
        String finalUrl = generateGetUrl(getUrl, appKey, secret);
        String result = sendGet(finalUrl);
        JSONObject resultJson = JSON.parseObject(result);
        String signCode = (String) resultJson.get("signCode");
        Long timestamp = (Long) resultJson.get("timestamp");
        String traceId = (String) resultJson.get("traceId");

        Map<String, String> params = generateParams(signCode, timestamp, traceId);
        postWithForm(postUrl, params);
        return "";
    }

    public static String postWithForm(String url, Map<String, String> params) {
        return postWithForm(url, params, null);
    }

    private static Header[] assemblyHeader(Map<String, String> headers) {
        Header[] allHeader = new BasicHeader[headers.size()];
        int i = 0;
        for (String str : headers.keySet()) {
            allHeader[i] = new BasicHeader(str, headers.get(str));
            i++;
        }
        return allHeader;
    }


    /**
     * 发送HttpPost请求 (表单方式)
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String postWithForm(String url, Map<String, String> params, Map<String, String> headers) {

        HttpPost httpPost = new HttpPost(url);

        //设置请求头
        if (headers != null) {
            httpPost.setHeaders(assemblyHeader(headers));
        }
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");


        List<NameValuePair> lst = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            lst.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        //  设置HTTP POST请求参数
        UrlEncodedFormEntity posEntity = null;
        try {
            posEntity = new UrlEncodedFormEntity(lst, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        httpPost.setEntity(posEntity);
        return post(httpPost);
    }

    private static String post(HttpPost httpPost) {
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(httpPost);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }

        HttpEntity entityResponse = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entityResponse);
        } catch (ParseException | IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        log.info("HttpUtil.sendPost response：{}", result);
        return result;
    }

    private Map<String, String> generateParams(String signCode, Long timeStamp, String traceId) {
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("appKey", "devRD");
        paramMap.put("signCode", signCode);
        paramMap.put("timestamp", String.valueOf(timeStamp));
        paramMap.put("traceId", traceId);
        paramMap.put("className", "com.lianjia.mls.datakeeper.housedel.facade.AggregationFacade");
        paramMap.put("method", "queryHouseByHouseParam");
        paramMap.put("dataPackage", "house_role_data");
        paramMap.put("param", "{housedelCodes:[102084450616]}");
        return paramMap;
    }

    public String generateGetUrl(String url, String appKey, String secret) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(url);
        urlBuilder.append("?");
        urlBuilder.append("appKey=");
        urlBuilder.append(appKey);
        urlBuilder.append("&");
        urlBuilder.append("secret=");
        urlBuilder.append(secret);
        return urlBuilder.toString();
    }

    /**
     * 发送HttpGet请求
     */
    public static String sendGet(String url) {
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient().execute(new HttpGet(url));
        } catch (IOException e1) {
            log.error(e1.getLocalizedMessage(), e1);
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            log.error(e.getLocalizedMessage(), e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        return result;
    }

    private static CloseableHttpClient getHttpClient() {
        return HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
}
