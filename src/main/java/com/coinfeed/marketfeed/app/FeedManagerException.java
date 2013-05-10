package com.coinfeed.marketfeed.app;

public class FeedManagerException extends Exception {

	public static final String CONFIGURATION = "configuration";
	private static final long serialVersionUID = 6677949075532975808L;
	public static final String DB_AUTHENTICATION = "db authentication";
	public static final String GENERIC = "generic";
	private String type;
	public FeedManagerException(String type, String message){
		super(message);
		this.type = type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}
