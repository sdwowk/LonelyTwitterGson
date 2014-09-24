package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.lonelytwitter.data.FileDataManager;
import ca.ualberta.cs.lonelytwitter.data.GsonFileDataManager;
import ca.ualberta.cs.lonelytwitter.data.IDataManager;
import ca.ualberta.cs.lonelytwitter.data.Summary;

public class LonelyTwitterActivity extends Activity {

	private IDataManager dataManager;

	private EditText bodyText;

	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets;
	
	private Summary tweetSummary;

	private ArrayAdapter<Tweet> tweetsViewAdapter;
	private int numberTweets = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		dataManager = new GsonFileDataManager(this);
		
		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweets = dataManager.loadTweets();
		tweetsViewAdapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(tweetsViewAdapter);
	}

	public void save(View v) {
		
		String text = bodyText.getText().toString();

		Tweet tweet = new Tweet(new Date(), text);
		tweets.add(tweet);
		numberTweets++;
		calculateLatency(tweets);
		tweetsViewAdapter.notifyDataSetChanged();

		bodyText.setText("");
		dataManager.saveTweets(tweets);
	}

	public void clear(View v) {

		tweets.clear();
		tweetsViewAdapter.notifyDataSetChanged();
		dataManager.saveTweets(tweets);
	}
	
	
	public void calculateLatency(ArrayList<Tweet> tweets2){
		int Latency = 0;
		for(int i = 0; i < tweets2.size()-1; i ++){
			Tweet begin = tweets2.get(i);
			Tweet end = tweets2.get(i+1);
			long tempTime = begin.getTweetDate().getTime();
			
		}
	}

}