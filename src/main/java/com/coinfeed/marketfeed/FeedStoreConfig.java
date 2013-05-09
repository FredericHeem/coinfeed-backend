package com.coinfeed.marketfeed;

public class FeedStoreConfig {
	private String hostname = "ds059947.mongolab.com";
	private int port = 59947;
	private String username = "coinfeed";
	private String password = "coinfeed1234";
	private String dbName = "bitcointickers-dev";
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(getUsername()).append(":").append(getPassword()).append("@");
		builder.append(getHostname()).append(":").append(getPort());
		builder.append("/").append(getDbName());
		return builder.toString();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getHostname() {
		return hostname;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}

}
