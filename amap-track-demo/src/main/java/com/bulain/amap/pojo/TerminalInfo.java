package com.bulain.amap.pojo;

import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerminalInfo {

	private Long sid;//服务唯一编号
	private Long tid;//终端唯一编号
	private String name;//终端名字
	private String desc;//终端描述
	private Map<String, ?> props;//自定义字段
	private Date createtime;//创建时间
	private Date locatetime;//最后定位时间

}
