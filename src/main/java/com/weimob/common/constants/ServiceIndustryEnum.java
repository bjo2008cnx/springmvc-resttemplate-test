package com.weimob.common.constants;

import com.weimob.common.lang.EnumCode;

import java.util.HashMap;
import java.util.Map;

import static com.weimob.common.constants.IndustryEnum.*;

/**
 * Created by xiaozhu on 16/11/16.
 */
public enum ServiceIndustryEnum implements EnumCode<Integer> {

    TRY_SERVICE(1005, "智慧餐厅试用套餐", NEW_DINNER_TRY),
    ONE_SHOP_SERVICE(1001, "智慧餐厅单店版", CATERING),
    MANY_SHOP_SERVICE(1002, "智慧餐厅多店版", CATERING),
    TAKE_OUT_SERVICE(1003, "智慧餐厅外卖版", TAKE_OUT),

    /** 不对外销售 */
    STANDARD_SERVICE(1004, "智慧餐厅标准套餐", CATERING),
    /** 不对外售卖，演示版套餐 */
    DEMO_SHOW_SERVICE(1008, "智慧餐厅演示套餐", CATERING),

    RETAIL_SERVICE(209, "智慧餐厅零售套餐", RETAIL),
    UNIVERSAL_SERVICE(4, "智慧餐厅通用套餐", CATERING),

    ZD_SERVICE(1006, "智店版", ZD),
    ZD_MEMBER_SERVICE(1007, "智店会员版", ZD),

    TRY_TAKE_OUT_SERVICE(1009, "智慧餐厅外卖版-试用版", NEW_TAKE_OUT),
    NEW_TAKE_OUT_SERVICE(1010, "智慧餐厅外卖版（新）", NEW_TAKE_OUT),

    NEW_DINNER_SERVICE(1011, "智慧餐厅（新）", NEW_DINNER),

    ;

    private Integer key;
    private String description;
    private IndustryEnum industry;

    private static Map<Integer, ServiceIndustryEnum> enums;
    static {
        enums = new HashMap<Integer, ServiceIndustryEnum>();
        for (ServiceIndustryEnum serviceIndustryEnum : ServiceIndustryEnum.values()) {
            enums.put(serviceIndustryEnum.getKey(), serviceIndustryEnum);
        }
    }

    public static IndustryEnum getIndustryEnumByKey(Integer key) {
        ServiceIndustryEnum serviceIndustryEnum = enums.get(key);
        if (null == serviceIndustryEnum) {
            return null;
        }
        return serviceIndustryEnum.getIndustry();
    }

    public static ServiceIndustryEnum getServiceIndustryEnumByKey(Integer key) {
        return enums.get(key);
    }

    ServiceIndustryEnum(int key, String description, IndustryEnum industry) {
        this.key = key;
        this.description = description;
        this.industry = industry;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public IndustryEnum getIndustry() {
        return industry;
    }
}
