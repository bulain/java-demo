package com.bulain.amap.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerminalTrackReq {

	private Long sid;//服务唯一编号
	private String filter;//筛选条件
	private String sortrule;//排序规则
	
	private String keywords;//要查询的关键字
	
	private String center;//中心点
	private String radius;//半径

	private String polygon;//多边形区域范围
	
}
