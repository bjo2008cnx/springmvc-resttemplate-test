package com.weimob.common.risk;

import jersey.repackaged.com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 风控服务
 *
 * @author Administrator
 */
@Log4j2
public class RiskControlService {
    private static final List<String> ipList = Lists.newArrayList("120.10.10.1", "173.22.13.23", "121.23.54.11", "152.16.16.6", "121.22.33.24",
            "123.54.23.87", "175.23.5.7", "166.12.53.3", "168.23.11.2", "120.18.10.1");

    private static final String RISK_URL_PARAM = "?methodName=qualityRiskControl&serviceName=com.weimob.mengdian.risk.management.service" + "" + "" + "" +
            ".RiskManageQualityService&applicationName=o2o";
    public static final int BIZ_TYP_O2O = 13;
    public static Set<String> WHITE_LIST_WORDS = new HashSet<String>();

    public static void setWhiteListWords(List<String> words) {
        if (CollectionUtils.isNotEmpty(words)) {
            WHITE_LIST_WORDS.addAll(words);
        }
    }

    public static boolean qualityRiskControl(String riskStr, String ip) {
        if (StringUtils.isBlank(riskStr)) {
            return true;
        }
        log.info("input data {} " + riskStr);
        return true;
    }
}