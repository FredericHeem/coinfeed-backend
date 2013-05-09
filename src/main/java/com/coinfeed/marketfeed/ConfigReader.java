package com.coinfeed.marketfeed;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ConfigReader {
	private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);

	public static Config createFromFilename(String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		log.debug("createFromFilename " + fileName);
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
		return createBufferedReader(new BufferedReader(isr));
	}
	
	public static Config createBufferedReader(BufferedReader br){
		log.debug("createBufferedReader");
		Gson gson = new Gson();
		Config config = null;
		
		try {
			config = gson.fromJson(br, Config.class);
			log.debug(gson.toJson(config));
		} catch (JsonSyntaxException e) {
			log.error("createBufferedReader error: " + e.getMessage());
		}
		
		return config;
	}
	
	public static Config createFromString(String configContent){
		log.debug("createFromString " + configContent);
		Gson gson = new Gson();
		Config config = null;
		
		try {
			config = gson.fromJson(configContent, Config.class);
		} catch (JsonSyntaxException e) {
			log.error("createFromString error: " + e.getMessage());
		}
		
		return config;
	}
}
