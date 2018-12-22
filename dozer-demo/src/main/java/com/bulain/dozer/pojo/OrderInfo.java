package com.bulain.dozer.pojo;

import java.util.Date;

import org.dozer.Mapping;

import lombok.Data;

@Data
public class OrderInfo {

	private long id;
	@Mapping("name")
    private String value;
    private Date date;
	
}
