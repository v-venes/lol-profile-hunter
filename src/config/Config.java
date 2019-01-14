package config;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {
	public static String LOL_API_KEY = "";
	
	public String getKey() {
		if(LOL_API_KEY.equals("")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/config/Config.json"));
				setKey((String)jsonObject.get("key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.LOL_API_KEY;
	}
	
	private void setKey(String key) {
		this.LOL_API_KEY = key;
	}
}
