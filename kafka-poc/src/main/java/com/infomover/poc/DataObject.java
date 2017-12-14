package com.infomover.poc;

import java.io.Serializable;

public class DataObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7259349498650701849L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public DataObject setMessage(String message) {
		this.message = message;
		return this;
	}

}
