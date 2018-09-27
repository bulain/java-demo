package com.bulain.amap.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 轨迹自定义字段
 * @author bulain
 */
@Getter
@Setter
public class PointColumnInfo {

	private Long sid;//服务唯一编号
	private String column;//自定义字段名字
	private String type;//自定义字段的类别
	private String list;//自定义字段是否可被检索
	
	private String newcolumn;//新自定义名字
	
}
