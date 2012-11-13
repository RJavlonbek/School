package com.android.assignment3;

import java.util.Random;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * There are numerous and slightly messy portions of the code that could be more refined
 * However, it does get the job done.
 * The complexity from going from 3 possible moves to 5 increases quite rapidly
 * I had initially done only a rock paper scissors option included in other file
 * It was very simple and straight forward.
 */

public class MainActivity extends Activity {
	
	private SoundPool soundPool;
	private int winSound;
	private int loseSound; 	

	private String outputText;
	private Choice computerChoice;
	private Choice playerChoice;
	
	private AnimationSet countDownAnimSet;
	private TextView winnerOutput;
	private TextView scoreOutput;
	private int playerWins;
	private int cpuWins;
	private int playCount;
	
	private enum Choice{ ROCK, PAPER, SCISSORS, LIZARD, SPOCK }
	private String outcomeOptions[];
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//playerPlayed = (TextView) findViewById(R.id.playerAnswer);
		//computerPlayed = (TextView) findViewById(R.id.computerAnswer);
		winnerOutput = (TextView) findViewById(R.id.winnerOutput);
		scoreOutput = (TextView) findViewById(R.id.scoreOutput);
		cpuWins = 0;
		playerWins = 0;
		playCount = 0;
		outcomeOptions = getResources().getStringArray(R.array.end_game_responses);
		
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
		loseSound = soundPool.load(this, R.raw.lose, 1);
		winSound = soundPool.load(this, R.raw.winning, 1);
		
		((ImageView) findViewById(R.id.playerChoice)).setVisibility(ImageView.INVISIBLE);
		((ImageView) findViewById(R.id.computerChoice)).setVisibility(ImageView.INVISIBLE);
		//((TextView) findViewById(R.id.versusTextView)).setVisibility(ImageView.INVISIBLE);		
		constructCountdown();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	public void playScissors(View v)
	{
		playerChoice = Choice.SCISSORS;
		playTurn();
	}
	
	public void playRock(View v)
	{
		playerChoice = Choice.ROCK;
		playTurn();
	}

	public void playPaper(View v)
	{
		playerChoice = Choice.PAPER;
		playTurn();
	}
	
	public void playLizard(View v)
	{
		playerChoice = Choice.LIZARD;
		playTurn();
	}
	
	public void playSpock(View v)
	{
		playerChoice = Choice.SPOCK;
		playTurn();
	}
	
	public void rules(View v)
	{
		Intent i = new Intent(getApplicationContext(), RulesActivity.class);
		startActivity(i);
	}
	
	private void disableButtons() {
		((ImageButton) findViewById(R.id.rockButton)).setEnabled(false);
		((ImageButton) findViewById(R.id.rockButton)).setAlpha(100);
		
		((ImageButton) findViewById(R.id.paperButton)).setEnabled(false);
		((ImageButton) findViewById(R.id.paperButton)).setAlpha(100);
		
		((ImageButton) findViewById(R.id.scissorsButton)).setEnabled(false);
		((ImageButton) findViewById(R.id.scissorsButton)).setAlpha(100);
		
		((ImageButton) findViewById(R.id.lizardButton)).setEnabled(false);
		((ImageButton) findViewById(R.id.lizardButton)).setAlpha(100);
		
		((ImageButton) findViewById(R.id.spockButton)).setEnabled(false);
		((ImageButton) findViewById(R.id.spockButton)).setAlpha(100);
	}
	
	private void enableButtons() {
		((ImageButton) findViewById(R.id.rockButton)).setEnabled(true);
		((ImageButton) findViewById(R.id.rockButton)).setAlpha(255);
		
		((ImageButton) findViewById(R.id.paperButton)).setEnabled(true);
		((ImageButton) findViewById(R.id.paperButton)).setAlpha(255);
		
		((ImageButton) findViewById(R.id.scissorsButton)).setEnabled(true);
		((ImageButton) findViewById(R.id.scissorsButton)).setAlpha(255);
		
		((ImageButton) findViewById(R.id.lizardButton)).setEnabled(true);
		((ImageButton) findViewById(R.id.lizardButton)).setAlpha(255);
		
		((ImageButton) findViewById(R.id.spockButton)).setEnabled(true);
		((ImageButton) findViewById(R.id.spockButton)).setAlpha(255);
	}
	
	private Choice computer() {
		Random r = new Random();
		return Choice.values()[r.nextInt(5)];
	}
	
	private void playTurn() {
		++playCount;
		
		disableButtons();
		
		//Get computer's choice
		computerChoice = computer();
		
		((ImageView) findViewById(R.id.computerChoice)).startAnimation(countDownAnimSet);
		((ImageView) findViewById(R.id.playerChoice)).startAnimation(countDownAnimSet);
	}
	
	private void win() {
		soundPool.play(winSound, 1, 1, 0, 0, 1);
		++playerWins;
	}
	
	private void lose() {
		soundPool.play(loseSound, 1, 1, 0, 0, 1);
		++cpuWins;
	}
	
	private void endGame() {
		// default output if no one wins
		outputText = "It is a Tie!";
		
		// Winning conditions only
		if (playerChoice == Choice.ROCK)
		{
			((ImageView) findViewById(R.id.playerChoice))
			.setImageResource(R.drawable.rock);
			
			if (computerChoice == Choice.LIZARD)
			{
				outputText = outcomeOptions[1];
				win();
			} else if (computerChoice == Choice.SCISSORS)
			{
				outputText =  outcomeOptions[0];
				win();
			} else if (computerChoice == Choice.PAPER)
			{
				outputText = outcomeOptions[2];
				lose();
			} else if (computerChoice == Choice.SPOCK)
			{
				outputText = outcomeOptions[9];
				lose();
			}
		} else if (playerChoice == Choice.PAPER)
		{
			
			((ImageView) findViewById(R.id.playerChoice))
			.setImageResource(R.drawable.paper);
			if (computerChoice == Choice.ROCK)
			{
				outputText = outcomeOptions[2];
				win();
			} else if (computerChoice == Choice.SPOCK)
			{
				outputText = outcomeOptions[3];
				win();
			} else if (computerChoice == Choice.LIZARD)
			{
				outputText = outcomeOptions[7];
				lose();
			} else if (computerChoice == Choice.SCISSORS)
			{
				outputText = outcomeOptions[4];
				lose();
			}
		} else if (playerChoice == Choice.SCISSORS)
		{
			((ImageView) findViewById(R.id.playerChoice))
			.setImageResource(R.drawable.scissors);
			
			if (computerChoice == Choice.PAPER)
			{
				outputText = outcomeOptions[4];
				win();
			} else if (computerChoice == Choice.LIZARD)
			{
				outputText = outcomeOptions[5];
				win();
			} else if (computerChoice == Choice.ROCK)
			{
				outputText = outcomeOptions[0];
				lose();
			} else if (computerChoice == Choice.SPOCK)
			{
				outputText = outcomeOptions[8];
				lose();
			}
		} else if (playerChoice == Choice.LIZARD)
		{
			((ImageView) findViewById(R.id.playerChoice))
			.setImageResource(R.drawable.lizard);
			
			if (computerChoice == Choice.PAPER)
			{
				outputText = outcomeOptions[7];
				win();
			} else if (computerChoice == Choice.SPOCK)
			{
				outputText = outcomeOptions[6];
				win();
			} else if (computerChoice == Choice.ROCK)
			{
				outputText = outcomeOptions[1];
				lose();
			} else if (computerChoice == Choice.SCISSORS)
			{
				outputText = outcomeOptions[5];
				lose();
			}
		} else if (playerChoice == Choice.SPOCK)
		{
			((ImageView) findViewById(R.id.playerChoice))
			.setImageResource(R.drawable.spock);
			
			if (computerChoice == Choice.SCISSORS)
			{
				outputText = outcomeOptions[8];
				win();
			} else if (computerChoice == Choice.ROCK)
			{
				outputText = outcomeOptions[9];
				win();
			} else if (computerChoice == Choice.PAPER)
			{
				outputText = outcomeOptions[3];
				lose();
			} else if (computerChoice == Choice.LIZARD)
			{
				outputText = outcomeOptions[6];
				lose();
			}
		}
		
		//	It was too messy to put in the above computer conditions
		switch (computerChoice) {
		case ROCK:
			((ImageView) findViewById(R.id.computerChoice))
					.setImageResource(R.drawable.rock);
			break;
		case PAPER:
			((ImageView) findViewById(R.id.computerChoice))
					.setImageResource(R.drawable.paper);
			break;
		case SCISSORS:
			((ImageView) findViewById(R.id.computerChoice))
					.setImageResource(R.drawable.scissors);
			break;
		case LIZARD:
			((ImageView) findViewById(R.id.computerChoice))
					.setImageResource(R.drawable.lizard);
			break;
		case SPOCK:
			((ImageView) findViewById(R.id.computerChoice))
					.setImageResource(R.drawable.spock);
			break;
		default:
			((ImageView) findViewById(R.id.computerChoice))
					.setImageResource(R.drawable.circle);
		}
		
		winnerOutput.setText(outputText);
		scoreOutput.setText("Wins: " + playerWins + "    Loses: " + cpuWins + "    Plays: " + playCount);
		enableButtons();
	}

	
	//I initially was going to have this enlarged number shrink and fade for each number
	//In addition to a drum beat for each one.
	//However, I ran into numerous problems involving "onAniimationStart"
	//Apparently, even though I have startOffset and other variations attempted
	//They all "start" at the same time, so the only way for me to achieve this
	//Would have been to link start/end calls of animations, It would have just been
	// tedious and time consuming and I was growing a little tired of the project
	// I figured I had put in enough extra effort and the results were good enough for now
	
	//Long story short, using animationSets and built in animations are a major pain :(
	
	//I am well aware of numerous issues that exist beyond this point. 
	//Next time I will try an XML implementation of animations
	
	private void constructCountdown(){
		//int countdownTime = 500;
		
		countDownAnimSet = new AnimationSet(false);
			
		//AnimationSet count3as = new AnimationSet(false);
		Animation countdown3 = new AlphaAnimation(1.0f,0.66f);
		countdown3.setInterpolator(new DecelerateInterpolator());
		countdown3.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation){
				//Log.d("anims","3 end");
				((ImageView)findViewById(R.id.playerChoice)).setImageDrawable(getResources().getDrawable(R.drawable.two));
				((ImageView)findViewById(R.id.computerChoice)).setImageDrawable(getResources().getDrawable(R.drawable.two));
			}
			@Override
			public void onAnimationRepeat(Animation animation){}
			@Override
			public void onAnimationStart(Animation animation){	
				((ImageView)findViewById(R.id.playerChoice)).setVisibility(ImageView.VISIBLE);
				((ImageView)findViewById(R.id.playerChoice)).setImageDrawable(getResources().getDrawable(R.drawable.three));
				((ImageView)findViewById(R.id.computerChoice)).setVisibility(ImageView.VISIBLE);
				((ImageView)findViewById(R.id.computerChoice)).setImageDrawable(getResources().getDrawable(R.drawable.three));
			}
		});
		countdown3.setDuration(499);
		countDownAnimSet.addAnimation(countdown3);

		
		Animation countdown2 = new AlphaAnimation(1.0f, 0.33f);
		//countdown2.setInterpolator(new AccelerateInterpolator());
		
		countdown2.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation){
				//Log.d("anims","2 end");
				((ImageView)findViewById(R.id.playerChoice)).setImageDrawable(getResources().getDrawable(R.drawable.one));
				((ImageView)findViewById(R.id.computerChoice)).setImageDrawable(getResources().getDrawable(R.drawable.one));
			}
			@Override
			public void onAnimationRepeat(Animation animation){}
			@Override
			public void onAnimationStart(Animation animation){
				//Log.d("anims","2 start");
			}
		});
		countdown2.setDuration(500); 
		countdown2.setStartOffset(501);
		countDownAnimSet.addAnimation(countdown2);
		
		
		Animation countdown1 = new AlphaAnimation(1.0f,0.0f);
		//countdown1.setInterpolator(new AccelerateInterpolator());
		countdown1.setFillAfter(true);
		countdown1.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation){
				//Log.d("anims","1 end");
				endGame();
			}
			@Override
			public void onAnimationRepeat(Animation animation){}
			@Override
			public void onAnimationStart(Animation animation){
				//Log.d("anims","1 start");
			}
		});
		countdown1.setStartOffset(1000);
		countdown1.setDuration(500);
		countDownAnimSet.addAnimation(countdown1);
	}
	
}
