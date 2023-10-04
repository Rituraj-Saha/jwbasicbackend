package com.jewelleryBasic.jwBasic.frontEndModel;

import com.jewelleryBasic.jwBasic.common.Util;

import lombok.Getter;

@Getter
public class ConfigForAPP {
	String vcode = Util.VERSIONCODE;
	String vname = Util.VERSIONNAME;
	String appStatus = Util.ACTIVE;
	
}
