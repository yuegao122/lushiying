package com.lushiying.team.four.dubbo;

import com.alibaba.fastjson.JSONObject;
import com.github.rholder.retry.RetryException;
import com.google.common.collect.Lists;
import com.lushiying.team.four.BaseTest;
import com.lushiying.team.four.utils.RetryContainer;
import com.lianjia.mls.common.base.exception.HouseException;
import com.lianjia.mls.common.base.response.RstStatus;
import com.lianjia.mls.common.base.util.MessageUtil;
import com.lianjia.mls.datakeeper.housedel.facade.AggregationFacade;
import com.lianjia.mls.datakeeper.housedel.facade.dto.request.HouseParam;
import com.lianjia.mls.datakeeper.housedel.facade.dto.response.HouseAggregationInfo;
import com.lianjia.mls.datakeeper.housedel.facade.search.aggregation.SearchAggregationFacade;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author yuzhibo
 * @date 2019/7/10 17:04
 */
@ActiveProfiles("dev")
@Slf4j
public class DubboTest extends BaseTest {

    @Resource
    private AggregationFacade aggregationFacade;

    @Resource
    private SearchAggregationFacade searchAggregationFacade;

    private Set<String> packages = Sets.newHashSet();

    private List<Long> housedelCodes = Lists.newArrayList();

    /**
     * 测试根据房源委托编码获取房源委托信息
     */
    @Test
    public void testHouseByHouseParam() {

        packages.add("house_address_basic_data");
        packages.add("housedel_basic_data");
        packages.add("house_prospect_data");
        packages.add("house_price_change_data");
        packages.add("house_label_data");
        packages.add("house_haofang_data");
        packages.add("house_appearance_data");
        packages.add("subway_data");
        packages.add("housedel_data");
        packages.add("house_status_data");
        packages.add("house_basic_data");
        packages.add("house_address_basic_data");
        packages.add("house_address_sensitive_data");
        packages.add("building_data");
        packages.add("life_data");
        packages.add("house_valid_true_data");
        packages.add("house_ower_data");
        packages.add("house_price_data");
        packages.add("house_picture_data");
        packages.add("house_credient_basic_data");
        packages.add("house_credient_all_data");
        packages.add("house_key_data");
        packages.add("house_role_data");
        packages.add("house_view_data");
        packages.add("house_followup_data");
        packages.add("house_comment_data");
        packages.add("house_traded_data");

        housedelCodes.add(102084450616L);
        housedelCodes.add(102084450640L);
        housedelCodes.add(102084450648L);
        housedelCodes.add(102084450661L);

        HouseParam param = new HouseParam();
        param.setDataPackage(packages);
        param.setHousedelCodes(housedelCodes);


        try {
            RetryContainer.aggregationRetryer.call(() -> {
                Map<Long, HouseAggregationInfo> aggregationInfoMap = MessageUtil.checkMessageStatusAndReturnData(aggregationFacade.queryHouseByHouseParam(param));
                return aggregationInfoMap;
            });
        } catch (ExecutionException | RetryException e) {
            log.error("invoke,aggregation.queryHouseByHouseParam failed,try again and fail three times,error.", e);
            throw new HouseException(RstStatus.serviceError, "聚合接口异常");
        }

        Map<Long, HouseAggregationInfo> aggregationMap = MessageUtil.checkMessageStatusAndReturnData(aggregationFacade.queryHouseByHouseParam(param));
        Set<Map.Entry<Long, HouseAggregationInfo>> entries = aggregationMap.entrySet();
        for (Map.Entry<Long, HouseAggregationInfo> entry : entries) {
            System.out.println("housedelCode value :" + entry.getKey() + "houseAggregation value :" + entry.getValue());
        }

    }

    @Test
    public void testRetryer() throws IOException {
        File file = new File("C:\\Users\\seray\\Desktop");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"gbk");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lineText = "";
        while ((lineText = bufferedReader.readLine()) != null){
            JSONObject object = JSONObject.parseObject(lineText);
            Object housedelCode = object.get("housedelCode");
            Object followContent = object.get("followContent");
            FileWriter fileWriter = new FileWriter("C:\\Users\\seray\\Desktop");
            fileWriter.append(String.valueOf(housedelCode));
            fileWriter.append(":");
            fileWriter.append(String.valueOf(followContent));
            fileWriter.append("\n");
        }
    }
}
