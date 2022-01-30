package com.bulain.core.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据返回值基类 
 * @author Bulain
 */
@Getter
@Setter
public class DataResp<T> extends BaseResp {

	private T data; //具体的数据

}
