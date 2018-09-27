package com.bulain.amap.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerminalTrackInfo {

	private TerminalInfo terminal;//终端信息
	private PointInfo location;//设备最后的位置点
	
}
