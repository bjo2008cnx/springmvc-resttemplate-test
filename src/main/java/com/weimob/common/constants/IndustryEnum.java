package com.weimob.common.constants;

import com.weimob.common.lang.EnumCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaozhu on 16/11/17.
 */
public enum IndustryEnum implements EnumCode<Integer> {
    BASIC(0, "基础"),
    BASE(1, "通用"),
    CATERING(2, "餐饮"),
    TAKE_OUT(3, "外卖"),
    RETAIL(4, "零售"),
    ZD(5, "智店"),
    NEW_TAKE_OUT(6, "新外卖"),
    NEW_DINNER(7, "新餐饮"),
    NEW_DINNER_TRY(8, "新餐饮试用版"),
    ;

    private Integer key;
    private String description;

    IndustryEnum(int key, String description) {
        this.key = key;
        this.description = description;
    }

    private static Map<Integer, IndustryEnum> enums;
    static {
        enums = new HashMap<Integer, IndustryEnum>();
        for (IndustryEnum industryEnum: IndustryEnum.values()) {
            enums.put(industryEnum.getKey(), industryEnum);
        }
    }

    public static IndustryEnum getIndustryEnumByKey(Integer key) {
        IndustryEnum industryEnum = enums.get(key);
        if (null == industryEnum) {
            return null;
        }
        return industryEnum;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
