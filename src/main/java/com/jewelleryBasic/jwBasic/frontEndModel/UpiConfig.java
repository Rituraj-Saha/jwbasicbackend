package com.jewelleryBasic.jwBasic.frontEndModel;

import com.jewelleryBasic.jwBasic.common.Util;

import lombok.Data;

@Data
public class UpiConfig {
	String upiVpa = Util.UPI;
	String upiName = Util.UPINAME;
	String upiMerchantCode = Util.UPIMarchentCode;
}
