package com.bulain.core.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回值基类 
 * @author Bulain
 */
@Getter
@Setter
public class BaseResp {

	private int errcode; //结果状态码
	private String errmsg;//返回结果状态码描述
	private String errdetail;//错误细节

}
