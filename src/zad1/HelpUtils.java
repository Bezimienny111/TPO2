package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.utils.Json;

public class HelpUtils {
	private static String path = "data/Langs.json";
	static String test;
	public static void main(String[] args) {
		

		
		
		System.out.println(number().size());
		for (int i = 0; i < number().size(); i ++)
			System.out.println(number().get(i));
		
		}
	
	
	
	
	
	public static ArrayList<String> number() {
		ArrayList<String> list = new ArrayList<String>(); 
		BufferedReader br;
		
		
		
		
		try {
			
	
			br = new BufferedReader(new FileReader(path));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		//System.out.println(everything);
		    br.close();
			Json json = new Json();
			//json.setIgnoreUnknownFields(true);
			
			list = json.fromJson(ArrayList.class, everything);
			//System.out.println(wordList[0]);
		//	json.prettyPrint(list);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return list;
		
		
		
		
	}
	
	
	
	public void creator() {
		ArrayList<String> list = new ArrayList<String>(); 
		list.add("EN");
		list.add("JP");
		
		
		Json json = new Json();
		
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(path);
		
	    byte[] strToBytes = json.prettyPrint(list).getBytes();
	    outputStream.write(strToBytes);
	    outputStream.flush();
	    outputStream.close();
		
		//file.writeString(wordList[0].toString(), false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String printIt() {
		String out = "Dostępne języki: ";
		for (int i = 0; i < number().size(); i ++)
			out = out+number().get(i)+"; ";
		return out;
		}
	
	
	public String PrintThisString(String in) {
		test = in;
		return "wydrukowałem " + in;
	}
	}
	

