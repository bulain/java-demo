package com.bulain.amap.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackInfo {

	private TraceInfo trace;//轨迹信息
	private List<PointInfo> points;//轨迹经纬度点具体信息

}
