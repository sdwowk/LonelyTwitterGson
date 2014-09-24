package ca.ualberta.cs.lonelytwitter.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import android.content.Context;
import android.util.Log;

import ca.ualberta.cs.lonelytwitter.Tweet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Summary {
	private Gson gson;
	private int averageLatency;
	private int numberTweets;
	private int averageLength;
	private static final String FILENAME = "summaryfile.sav";
	private Context ctx;
	private ArrayList<Tweet> lts;
	
	public Summary(){

	}

	public void saveSummary(Context ctx){
		this.ctx = ctx;
		gson = new Gson();
		ArrayList<Tweet> lts = new ArrayList<Tweet>();
		try{
		BufferedReader fis = new BufferedReader(new InputStreamReader( ctx.openFileInput(FILENAME)));
		String line;
		StringBuffer fileContent = new StringBuffer();
		while((line = fis.readLine()) != null){
			fileContent.append(line);
		}
		
		Type collectionType = new TypeToken<Collection<Tweet>>(){}.getType();
		lts = gson.fromJson(fileContent.toString(), collectionType);
		setAverageLatency(lts);
		
		}catch(Exception e){
			Log.i("LonelyTwitter", "Error casting");
			e.printStackTrace();
		}
	}
	
	public int getAverageLatency(){
		return averageLatency;
	}
	
	public void setAverageLatency(int averageLat) {
		
		this.averageLatency = averageLat;
	}
	public int getNumberTweets() {
		return numberTweets;
	}
	public void setNumberTweets(int numberTweets) {
		this.numberTweets = numberTweets;
	}
	public int getAverageLength() {
		return averageLength;
	}
	public void setAverageLength(int averageLength) {
		this.averageLength = averageLength;
	}
	
}
