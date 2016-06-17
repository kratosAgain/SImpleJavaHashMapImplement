package myHashMap;

/* Author masterChiefBITS */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestClass {

	public static void main(String[] args)  {
		MyHashMap<String,String> map = new MyHashMap();
		BufferedReader br = null;
		//taking a large dataset from file input.txt 
		try{
			br = new BufferedReader(new FileReader("input.txt"));
			String current = "";
			while((current=br.readLine())!=null){
				String[] pair = current.split("\t");
				map.put(pair[0], pair[1]);				
			}
			
			//System.out.println(map.get("nazren"));
			//map.removeKey("nazren");
			//System.out.println(map.get("nazren"));
		}catch(Exception e){
			System.out.println(e);
		}
		System.out.println("mapping done");
		map.contract();
		//System.out.println(map);
	}

}
