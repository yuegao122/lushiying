package com.lushiying.team.four.utils;

import com.lianjia.common.datasource.impl.HouseRegion;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuzhibo
 * @date 2019/6/5 10:10
 */
public class HouseRegionUtil {

    public static Map<HouseRegion, List<Long>> groupByHousedelCode(List<Long> housedelCodes){
        if (CollectionUtils.isEmpty(housedelCodes)){
            return Collections.emptyMap();
        }

        return housedelCodes.stream().collect(Collectors.groupingBy(HouseRegion::getById));
    }
}
