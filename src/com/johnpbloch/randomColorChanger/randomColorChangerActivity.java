package com.johnpbloch.randomColorChanger;

import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class randomColorChangerActivity extends Activity {

	protected View myLayout;
	protected PowerManager pm;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		WakeLock mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
				| PowerManager.ON_AFTER_RELEASE, "");
		mWakeLock.acquire();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myLayout = findViewById(R.id.mainLayoutWrapper);
		myLayout.setBackgroundColor(getRandColor());
		myLayout.setOnTouchListener(touchlistener);
	}

	protected int getRandColor() {
		Random color = new Random();

		int randomColor = Color.rgb(color.nextInt(256), color.nextInt(256),
				color.nextInt(256));

		return randomColor;
	}

	public OnTouchListener touchlistener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				myLayout.setBackgroundColor(getRandColor());
			}
			return false;
		}
	};
}