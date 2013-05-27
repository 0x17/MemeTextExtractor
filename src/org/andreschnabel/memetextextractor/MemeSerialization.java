package org.andreschnabel.memetextextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MemeSerialization {
	
	public static void serializeMemes(List<Meme> memes, File out) throws Exception {
		Gson gson = new Gson();
		String s = gson.toJson(memes);
		FileWriter fw = new FileWriter(out);
		fw.write(s);
		fw.close();
	}
	
	public static List<Meme> deserializeMemes(File f) throws Exception {
		Gson gson = new Gson();
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		while(br.ready()) {
			sb.append(br.readLine());
			sb.append('\n');
		}
		br.close();
		fr.close();
		return gson.fromJson(sb.toString(), new TypeToken<List<Meme>>(){}.getType());
	}

}
