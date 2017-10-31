package com.weimob.common.constants;

import com.weimob.utility.property.PropertyUtils;

import java.util.Properties;

public class O2OCommonConstant {
	
	private static final  Properties WEIMOB_CONSTANT =  PropertyUtils.getProperties("o2o-weimob.properties");
	
	public static final String CURRENT_ENV = WEIMOB_CONSTANT.getProperty("CURRENT_ENV");

}
