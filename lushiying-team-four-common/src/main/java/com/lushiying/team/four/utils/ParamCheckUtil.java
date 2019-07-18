package com.lushiying.team.four.utils;

import com.google.common.base.Preconditions;
import com.lianjia.common.datasource.impl.HouseRegion;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * @author yuzhibo
 * @date 2019/6/6 13:53
 */
public class ParamCheckUtil {

    private static final int HOUSE_DELCODE_MAX_SIZE = 12;

    private static final int CITY_ID_MAX_SIZE = 6;

    public static void checkHousedelCode(Long housedelCode) {
        Preconditions.checkNotNull(housedelCode, "housedelCode invalid");
        Preconditions.checkArgument(String.valueOf(housedelCode).length() == HOUSE_DELCODE_MAX_SIZE,
                "housedelCode.length must = " + HOUSE_DELCODE_MAX_SIZE);
        Preconditions.checkArgument(HouseRegion.getById(housedelCode) != null,
                "housedelCode region invalid");
    }

    public static void checkMobileNumber(String mobileNumber) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(mobileNumber),
                "MobileNumber invalid");
        Preconditions.checkArgument(NumberUtils.isCreatable(mobileNumber),
                "MobileNumber must be a numeric type");
    }

    public static void checkHousedelCode(String housedelCode) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(housedelCode), "housedelCode invalid");
        Preconditions.checkArgument(NumberUtils.isCreatable(housedelCode), "housedelCode must be a numeric type");
        Preconditions.checkArgument(housedelCode.length() == HOUSE_DELCODE_MAX_SIZE, "housedelCode invalid");
    }

    public static void checkCityId(String cityId) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(cityId), "cityId invalid");
        Preconditions.checkArgument(NumberUtils.isCreatable(cityId), "cityId must be a numeric type");
        Preconditions.checkArgument(cityId.length() == CITY_ID_MAX_SIZE, "cityId.length must = " + CITY_ID_MAX_SIZE);
    }

    public static void checkCityId(Long cityId) {
        Preconditions.checkArgument(cityId != null, "cityId invalid");
        Preconditions.checkArgument(String.valueOf(cityId).length() == CITY_ID_MAX_SIZE,
                "cityId.length must = " + CITY_ID_MAX_SIZE);
    }

    public static void checkHousedelCodes(List<Long> housedelCodes) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(housedelCodes), "housedelCodes is null");
        housedelCodes.forEach(housedelCode -> {
            checkHousedelCode(housedelCode);
        });
    }

    public static void checkStringHousedelCodes(List<String> housedelCodes) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(housedelCodes), "housedelCodes is null");
        housedelCodes.forEach(housedelCode -> {
            checkHousedelCode(housedelCode);
        });
    }

    public static void checkCityIds(List<String> cityIds) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(cityIds), "cityIds is null");
        cityIds.forEach(cityId -> {
            checkCityId(cityId);
        });
    }

    public static void checkUserId(String userId){
        Preconditions.checkArgument(StringUtils.isNotEmpty(userId),"userId must be not Null");
        Preconditions.checkArgument(NumberUtils.isCreatable(userId),"userId must be a numeric type");
        Preconditions.checkArgument(Long.valueOf(userId) > 0,"userId must bigger than zero");
    }

    public static void checkAgentId(String agentId){
        Preconditions.checkArgument(StringUtils.isNotEmpty(agentId),"agentId must be not Null");
        Preconditions.checkArgument(NumberUtils.isCreatable(agentId),"agentId must be a numeric type");
        Preconditions.checkArgument(Long.valueOf(agentId) > 0,"agentId must bigger than zero");
    }

    public static void checkSuggestContent(String suggestContent){
        Preconditions.checkArgument(StringUtils.isNotEmpty(suggestContent),"suggestContent must be not null");
        Preconditions.checkArgument(suggestContent.length() >= 10,"the number of characters int the string must be not less than 10");
        Preconditions.checkArgument(suggestContent.length() <= 300,"the number of characters in the string must be not greater than 300");
    }

}
