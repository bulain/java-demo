package com.bulain.drools;


public class Message {
	public static final int HELLO = 0;
	public static final int GOODBYE = 1;

	private String message;
	private int status;

	public String getMessage() {
		return this.message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
	public int getStatus() {
		return this.status;
	}
	public void setStatus(final int status) {
		this.status = status;
	}

}
