package com.android.assignment3;

import java.util.Random;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		playerPlayed = (TextView) findViewById(R.id.playerAnswer);
		computerPlayed = (TextView) findViewById(R.id.computerAnswer);
		winnerOutput = (TextView) findViewById(R.id.winnerAnswer);
		scoreOutput = (TextView) findViewById(R.id.scoreOutput);
		cpuWins = 0;
		playerWins = 0;
		
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
		loseSound = soundPool.load(this, R.raw.lose, 1);
		winSound = soundPool.load(this, R.raw.winning, 1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private SoundPool soundPool;
	private int winSound;
	private int loseSound; 	

	private TextView playerPlayed;
	private TextView computerPlayed;
	private TextView winnerOutput;
	private TextView scoreOutput;
	private int playerWins;
	private int cpuWins;
	
	private enum Choice{ ROCK, PAPER, SCISSORS }
			
	private Choice playerChoice;
	
	public void playScissors(View v)
	{
		playerChoice = Choice.SCISSORS;
		checkWinner();
	}
	public void playRock(View v)
	{
		playerChoice = Choice.ROCK;
		checkWinner();
	}

	public void playPaper(View v)
	{
		playerChoice = Choice.PAPER;
		checkWinner();
	}
	
	
	private Choice computer() {
		Random r = new Random();
		return Choice.values()[r.nextInt(3)];
		
	}
	

	
	private void checkWinner() {
		playerPlayed.setText(playerChoice.toString());
		Choice computerChoice = computer();
		computerPlayed.setText(computerChoice.toString());
		
		
		if(playerChoice == Choice.ROCK) {

			if(computerChoice == Choice.ROCK) {
				winnerOutput.setText("TIE");
			}else if(computerChoice == Choice.PAPER) {
				winnerOutput.setText("COMPUTER");
				soundPool.play(loseSound, 1, 1, 0, 0, 1);
				++cpuWins;
				
			}else {
				winnerOutput.setText("PLAYER");
				soundPool.play(winSound, 1, 1, 0, 0, 1);
				++playerWins;
			}
			
		}else if( playerChoice == Choice.PAPER) {
			if(computerChoice == Choice.ROCK) {
				winnerOutput.setText("PLAYER");
				soundPool.play(winSound, 1, 1, 0, 0, 1);
				++playerWins;
			}else if(computerChoice == Choice.PAPER) {
				winnerOutput.setText("TIE");
			}else {
				winnerOutput.setText("COMPUTER");
				soundPool.play(loseSound, 1, 1, 0, 0, 1);
				++cpuWins;
			}
		}else {
			if(computerChoice == Choice.ROCK) {
				winnerOutput.setText("COMPUTER");
				soundPool.play(loseSound, 1, 1, 0, 0, 1);
				++cpuWins;
			}else if(computerChoice == Choice.PAPER) {
				winnerOutput.setText("PLAYER");
				soundPool.play(winSound, 1, 1, 0, 0, 1);
				++playerWins;
			}else {
				winnerOutput.setText("TIE");
			}
		}
		scoreOutput.setText("P: " + playerWins + "    C: " + cpuWins);
		
	}
	
	
}
