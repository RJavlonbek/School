package com.android.assignment3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class RulesActivity extends Activity{
	private RelativeLayout myRulesLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        
        myRulesLayout=(RelativeLayout)findViewById(R.id.myRulesLayout);
        myRulesLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{	
				finish();	
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_rules, menu);
        return true;
    }


}
