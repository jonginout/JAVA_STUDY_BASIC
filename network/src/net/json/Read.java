package net.json;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Read {
	public static void main(String[] args) throws Exception {
		
		Gson gson = new Gson();
		FileReader fr = new FileReader("blog.json");
		BufferedReader br = new BufferedReader(fr);
		JsonReader reader = new JsonReader(br);

        
		Blog data = gson.fromJson(reader, Blog.class);
		
		System.out.println(data.getChannel().getDescription());
		
	}
}
