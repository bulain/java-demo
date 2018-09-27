package com.bulain.amap.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/***
 * 位置信息
 * @author bulain
 */
@Getter
@Setter
public class PointInfo {

	private String location;//最后的经纬度 格式：X,Y 
	private Date locatetime;//最后一次的定位时间
	private BigDecimal accuracy;//定位精度  小数点后最多3位
	private BigDecimal direction;//方向，取值范围：[0,359]，0代表正北方，采用顺时针方向取值 小数点后最多4位
	private BigDecimal speed;//速度，单位：km/h 小数点后最多3位
	private BigDecimal height;//高度，单位：米 小数点后最多3位
	private Map<String, ?> props;//自定义字段
	
	private String _param_err_info;//错误信息
	private String _err_point_index;//错误点的位置
	
}
