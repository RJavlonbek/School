package com.newrog.fearthespear;

// Adam Gorman
// Android Assignment 4
// Fear the Spear (Media player)
// Sept 27, 2012

// Added stop/pause/play buttons
// Added dynamic loading of songs
// Added a progress bar 

// I intentionally chose to disable screen rotation due to synchronous issues
// I didn't feel like incorporating loading files from android device


import java.io.IOException;
import java.lang.reflect.Field;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable,	OnPreparedListener, 
		OnVideoSizeChangedListener, OnSeekCompleteListener,	OnErrorListener, OnCompletionListener {

	private MediaPlayer myMedia;
	private ProgressBar myProgress;
	
	//switched to using isPlaying()
	//private boolean isPlaying; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		// Was having too many issues with it, so I forced it off in the manifest
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        
        
        setContentView(R.layout.activity_main);
        
        //isPlaying = false;
        
        addSongs();
	    myMedia = new MediaPlayer();
	    myProgress = (ProgressBar)findViewById(R.id.progressBar1);        
	    
	    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	if(myMedia.isPlaying()) {
    		myMedia.pause();
    	}
    	
    	//myMedia.release(); would require a new 'new mediaplayer' 
    }
    
    
    //load songs dynamically from raw folder
    private void addSongs() {
    	final int colorGrey = getResources().getColor(R.color.grey);
    	
    	Field[] fields = R.raw.class.getFields();
    	String[] song = new String[fields.length];
    	for(int i =0 ; i<fields.length;i++) {

    		song[i]=fields[i].getName();
    		TextView tv = new TextView(this);
    		
    		tv.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int count = ((LinearLayout) findViewById(R.layout.texty)).getChildCount();
					for(int i = 0; i < count; ++i) {
						((LinearLayout) findViewById(R.layout.texty)).getChildAt(i).setBackgroundColor(colorGrey);
					}
					 ((TextView) v).setBackgroundColor(getResources().getColor(R.color.maroon));
					 
					//Log.d("clicked!", (String) ((TextView) v).getText());
					//this.getClass().tv.setBackgroundColor(getResources().getColor(R.color.maroon));
					playSong((String) ((TextView) v).getText());
				}
			});
    		
    		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    		lp.setMargins(0, 5, 0, 5);
    		tv.setLayoutParams(lp);
    		tv.setGravity(Gravity.CENTER);
    		tv.setText(song[i]);
    		tv.setPadding(5, 5, 5, 5);
    		tv.setBackgroundColor(colorGrey);
    		
    		tv.setTextSize(getResources().getDimension(R.dimen.text_size_large));
    		((LinearLayout) findViewById(R.layout.texty)).addView(tv);
    	}
    }
    
    
    public void pauseSong(View v) {
    	if(myMedia.isPlaying()) {	
    		myMedia.pause();
    		//isPlaying = false;
    	}
    }
    
    public void playSong(View v) {
    	if(!myMedia.isPlaying())	{
    		myMedia.start();
    		//isPlaying = true;
    	}
    }
    
    
    // I originally had chosen to call stop()
    // however after further testing, I chose to have it pause and reset the song
    // This behaves in a more traditional sense to media players I have used
    // You can hit 'stop' and it just resets it back to the beginning
    // where you can hit play again.
    // the .stop() wouldn't allow you to play it again using the play button
    public void stopSong(View v) {
    	if(myMedia.getCurrentPosition()>0)	{
     		
       		myMedia.pause();
       		myMedia.seekTo(0);
       		//myMedia.stop();
       		//myMedia.release();
    		//isPlaying = false;
    		myProgress.setProgress(0);
    	}
    }
    
    
    public void playSong(String songName) {
    	//isPlaying = true;
    	
    	//Log.d("playing", "starting");
    	//if(myMedia.isPlaying()) {
    	//myMedia.stop();
    	
    	myMedia.reset();
    	//}
    	
		try
		{
			myMedia.setDataSource(getApplicationContext(), Uri.parse("android.resource://com.newrog.fearthespear/raw/"+songName));
			myMedia.setOnPreparedListener(this);
			myMedia.setOnVideoSizeChangedListener(this); // 
			myMedia.setOnCompletionListener(this);
			myMedia.setOnErrorListener(this);
			myMedia.setOnSeekCompleteListener(this);
			myMedia.prepare();
			
			//myMedia.prepareAsync();
			//myMedia.start();
			//Log.d("playing", "Success");
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    }
  
	@Override
	public void onPrepared(MediaPlayer arg0)
	{
		myMedia.start();
		
		myProgress.setVisibility(ProgressBar.VISIBLE);
		myProgress.setProgress(0);
		myProgress.setMax(myMedia.getDuration());
		new Thread(this).start();
		
	}
    
    //Update progress bar graphic
	@Override
	public void run()
	{
		int songProgress = 0;
		int songLength = myMedia.getDuration();
		while(myMedia != null && songProgress < songLength) {
			try {
				Thread.sleep(250); // sleep for 1/4 second
				
				songProgress = myMedia.getCurrentPosition();
			}catch(Exception ex){
				//Log.d("run", ex.toString());
			}
			myProgress.setProgress(songProgress);
		}
		
	}
	

	// Mediaplayer wants to send messages on different events
	// It was throwing a non crashing error if I didn't have something, so
	// I opted to include it, even though they caused no issues without it
	// I just didn't want to leave values as null that wanted something
	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height)
	{
		//intentionally left blank
		//do nothing
	}

	@Override
	public void onSeekComplete(MediaPlayer mp)
	{
		//intentionally left blank
		//do nothing
	}

	@Override
	public void onCompletion(MediaPlayer mp)
	{		
		//intentionally left blank
		//do nothing
	}

	@Override
	public boolean onError(MediaPlayer mp, int x, int y)
	{
		//intentionally left blank
		//do nothing
		return false;
	}


}
