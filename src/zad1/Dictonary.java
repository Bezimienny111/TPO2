package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.utils.Json;

public class Dictonary {

private HashMap<String,String> langList;// = new HashMap<String,String>();
	
	public Dictonary() {
		super();
		this.langList  =new HashMap<String,String>();
	}
	
	public Dictonary(String main,String second) {
		super();
		langList = new HashMap<String,String>();
		if (!this.langList.containsKey(main)) {
			this.langList.put(main, second);
		}
	}
	public Dictonary(HashMap<String,String> listLoad) {
		super();
		this.langList = listLoad;
	}
	public boolean addWord(String key, String value) {
		if (!this.langList.containsKey(key)) {
		this.langList.put(key, value);
		return true;
		}
		return false;
	}

	public void add(String text, String text2) {
		this.langList.put(text, text2);
	}

	public HashMap<String, String> getLangList() {
		return langList;
	}

	public void setLangList(HashMap<String, String> langList) {
		this.langList = langList;
	}	
	
	public String getWord(String in) {
	
		return this.langList.get(in);
		
	}
	
	public Dictonary(int in, String lang) throws IOException {
		if( in == 1) {
			String path = "data/PL-"+lang+".json";
			BufferedReader br = new BufferedReader(new FileReader(path));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
	//	System.out.println(everything);
		    br.close();
			Json json = new Json();
			//json.setIgnoreUnknownFields(true);
			
			this.langList = json.fromJson(Dictonary.class, everything).getLangList();
			
		}
		
	}
	
	
}
