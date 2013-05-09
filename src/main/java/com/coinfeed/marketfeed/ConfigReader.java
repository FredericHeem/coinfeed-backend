package com.coinfeed.marketfeed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ConfigReader {
	private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);

	public static Config createFromFilename(String fileName) throws FileNotFoundException{
		log.debug("createFromFilename " + fileName);
		return createBufferedReader(new BufferedReader(new FileReader(fileName)));
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
