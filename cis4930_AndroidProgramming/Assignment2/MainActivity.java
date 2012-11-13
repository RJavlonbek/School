package com.android.assignment2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

	private AnimationSet fadeOutAnimSet;
	private AnimationSet fadeInAnimSet;
	private AnimationSet rotateScaleOutAnimSet;
	private AnimationSet teleportInAnimSet;
	private ImageView myImg;
	private Button myButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myButton = (Button) findViewById(R.id.switchTeamsBtn);
		myImg = (ImageView) findViewById(R.id.switchTeamsImageView);
		myImg.bringToFront();
		initAnimations();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	// I tested this out on 1 emulated android phone and 1 physical android
	// phone. First was on the default AVD one and the other was Samsung Galaxy S3
	// I could vertically center it, but I wanted it offset a little, because in
	// my 2 tests it looked better if there was a margin/buffer on the top of the image

	// I had originally implemented a onClickListener and done this a different way
	// However, I have decided to use the XML android:onClick call this time.
	
	public void flipIt(View v)
	{

		
		if ( (myButton.getText().toString()).equals(getString(R.string.show_gator)))
		{
			myImg.setAnimation(fadeOutAnimSet);
			myImg.startAnimation(fadeOutAnimSet);
		} else
		{
			myImg.setAnimation(rotateScaleOutAnimSet);
			myImg.startAnimation(rotateScaleOutAnimSet);
		}

	}

	
	
	private void initAnimations() {
		constructFadeOut();
		constructFadeIn();
		constructRotateScaleOut();
		constructTeleportIn();
	}
	
	
	private void constructFadeOut(){
		fadeOutAnimSet = new AnimationSet(false);
		
		
		//This Animation will handle the fading out of the Seminole
		Animation fadeOutAnim = new AlphaAnimation(1, 0.0f);
		fadeOutAnim.setInterpolator(new AccelerateInterpolator());
		fadeOutAnim.setDuration(500);
		fadeOutAnim.setFillAfter(true);
		fadeOutAnim.setAnimationListener(new AnimationListener()
		{
			// I am overriding the onAnimationEnd to call another animation when this animation is 100% done.
			@Override
			public void onAnimationEnd(Animation animation)
			{
				//Switch to the new graphics and text!
				myImg.setImageDrawable(getResources().getDrawable(R.drawable.koolgator));
				myButton.setText(getString(R.string.show_nole));
				myImg.startAnimation(fadeInAnimSet);
			}
			@Override
			public void onAnimationRepeat(Animation animation){}
			@Override
			public void onAnimationStart(Animation animation){}
		});
		fadeOutAnimSet.addAnimation(fadeOutAnim);
	}
	
	
	private void constructFadeIn() {
		//This Animation will handle the bouncing in of the KoolAid Guy
		fadeInAnimSet = new AnimationSet(false);
		
		// Fade in 0% to 100% 
		Animation fadeInAnim = new AlphaAnimation(0.0f, 1f);
		fadeInAnim.setInterpolator(new DecelerateInterpolator());
		fadeInAnim.setDuration(1000);
		fadeInAnimSet.addAnimation(fadeInAnim);

		//Move from top of screen to default position
		Animation bounceInAnim = new TranslateAnimation(0.0f, 0.0f, -600.0f, 0.0f);
		
		//Bounce into place
		bounceInAnim.setInterpolator(new BounceInterpolator());
		bounceInAnim.setDuration(1250);
		fadeInAnimSet.addAnimation(bounceInAnim);
	}
	
	//the koolaid dude will SPIN OUT OF CONTROL!!! Cause koolaid guy is crazzzzy
	private void constructRotateScaleOut()
	{
		rotateScaleOutAnimSet = new AnimationSet(false);

		//Rotate around the center of self from 0 to 900 degrees
		Animation rotateAnim = new RotateAnimation(0, 900,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnim.setDuration(1750);
		rotateScaleOutAnimSet.addAnimation(rotateAnim);

		//Scale up a bit to start with
		Animation scaleUp = new ScaleAnimation(0.45f, 2.5f, 0.45f, 2.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleUp.setDuration(500);
		rotateScaleOutAnimSet.addAnimation(scaleUp);

		//Then scale down into nothingness
		Animation scaleDown = new ScaleAnimation(2.5f, 0.0f, 2.5f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleDown.setDuration(1000);
		scaleDown.setStartOffset(500);
		rotateScaleOutAnimSet.addAnimation(scaleDown);

		rotateScaleOutAnimSet.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationEnd(Animation animation)
			{
				myButton.setText(getString(R.string.show_gator));
				myImg.setImageDrawable(getResources().getDrawable(R.drawable.nole));
				
				myImg.startAnimation(teleportInAnimSet);
			}
			@Override
			public void onAnimationRepeat(Animation animation){}

			@Override
			public void onAnimationStart(Animation animation){}

		});	
	}

	//The Seminole Teleports in!!
	private void constructTeleportIn()
	{		
		teleportInAnimSet = new AnimationSet(false);
		teleportInAnimSet.setDuration(500);
		
		//First I make sure we are 'thin' for teleportation, I could have manually set the width
		Animation thinLineAnim = new ScaleAnimation(0.1f, 0.1f, 1.0f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		thinLineAnim.setDuration(10);
		
		//Preserve the thinness, even after the animation has ended
		thinLineAnim.setFillAfter(true);
		teleportInAnimSet.addAnimation(thinLineAnim);

		//Move the thin line down to the ground, decelerating at the end
		Animation beamMeDownScotty = new TranslateAnimation(0.0f, 0.0f, -500.0f, 0.0f);
		beamMeDownScotty.setDuration(65);
		beamMeDownScotty.setInterpolator(new DecelerateInterpolator());
		beamMeDownScotty.setFillAfter(true);
		teleportInAnimSet.addAnimation(beamMeDownScotty);

		//Expand back into existence, completing the teleportation
		Animation teleportInAnim = new ScaleAnimation(0.1f, 10.0f, 1.0f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		teleportInAnim.setDuration(100);
		teleportInAnim.setStartOffset(150);
		teleportInAnimSet.addAnimation(teleportInAnim);
	}
}
