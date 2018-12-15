package com.bulain.socketio.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataEvent {

	private String type;
	private Date time;
	private String data;
	
}
