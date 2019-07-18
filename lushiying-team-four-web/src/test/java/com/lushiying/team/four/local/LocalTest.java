package com.lushiying.team.four.local;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.lushiying.team.four.BaseTest;
import com.lushiying.team.four.dao.mapper.SuggestContentMapper;
import com.lushiying.team.four.model.db.SuggestContent;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashSet;
import java.util.List;

/**
 * @author yuzhibo
 * @date 2019/7/10 17:07
 */
public class LocalTest extends BaseTest {

    @Resource
    private SuggestContentMapper suggestContentMapper;

    @Test
    public void test11(){
        SuggestContent suggestContent = new SuggestContent();
        suggestContent.setAgentId(1111L);
        suggestContent.setSuggestContent("hello world");
        suggestContent.setUserId(3334L);
        suggestContentMapper.insert(suggestContent);
    }

    @Test
    public void test() {
        List<Long> list = Lists.newArrayList();
        list.add(12222222222222L);
        list.add(333333L);
        list.add(444444L);
        list.add(555555L);
        list.add(555555L);
        list.add(555555L);
        list.add(666666L);
        list.add(666666L);
        list.add(666666L);
        list.add(666666L);
        list.add(666666L);

        HashSet<Long> hashSet = Sets.newHashSet(list);
        List<Long> housedelCodes = Lists.newArrayList(hashSet);

        for (Long housedelCode : housedelCodes) {
            System.out.println(housedelCode);
        }
    }

    @Test
    public void test1()throws IOException{
        File file = new File("C:\\Users\\seray\\Desktop\\1000house.json");
        File file1 = new File("C:\\Users\\seray\\Desktop\\outer.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file1), "utf-8");

        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        String lineText = "";
        while ((lineText = bufferedReader.readLine()) != null){
            JSONObject object = JSONObject.parseObject(lineText);
            Object housedelCode = object.get("housedelCode");
            JSONArray followUps = (JSONArray) object.get("followUps");

            for (int i = 0; i < followUps.size(); i++){
                JSONObject o = (JSONObject) followUps.get(i);
                Object followUpContent = o.get("followUpContent");

                bufferedWriter.append(String.valueOf(housedelCode));
                bufferedWriter.append(":");
                bufferedWriter.append(String.valueOf(followUpContent));
                bufferedWriter.newLine();

            }
        }
        bufferedWriter.close();
    }

    @Test
    public void test2() throws IOException {
        File file1 = new File("C:\\Users\\seray\\Desktop\\outer.txt");
        FileWriter fileWriter = new FileWriter(file1);
        fileWriter.write("haha");
        fileWriter.close();
    }
}
