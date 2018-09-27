package com.bulain.amap.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackReq {

	private Long sid;//服务唯一编号
	private Long tid;//终端唯一编号
	private Long trid;//轨迹唯一编号
	private Date starttime;
	private Date endtime;
	private String correction;
	private String recoup;
	private String gap;
	private String ispoints;
	
}
