package com.anjlab.ads.integrator;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.adapters.AdWhirlAdapter;

import android.app.Activity;
import android.widget.LinearLayout;

public class AdWhirlProvider extends AbstractAdProvider implements AdWhirlInterface {

	private String publisherId;
	private boolean testing;
	private int width;
	private int height;

	public AdWhirlProvider(String publisherId, int width, int height, boolean testing) {
		this.publisherId = publisherId;
		this.testing = testing;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void tryLoadAd(Activity actv, LinearLayout layout) {
		AdWhirlManager.setConfigExpireTimeout(1000 * 60 * 5);
//		AdWhirlTargeting.setAge(23);
//		AdWhirlTargeting.setGender(AdWhirlTargeting.Gender.MALE);
//		AdWhirlTargeting.setKeywords("online games gaming");
//		AdWhirlTargeting.setPostalCode("94123"); 
		AdWhirlTargeting.setTestMode(testing);
		//AdWhirlAdapter.setGoogleAdSenseChannel("9800041398");

		AdWhirlLayout adWhirlLayout = new AdWhirlLayout(actv, publisherId);
		float density = actv.getResources().getDisplayMetrics().density;
		
		adWhirlLayout.setAdWhirlInterface(this);
		adWhirlLayout.setMaxWidth((int) (width * density));
		adWhirlLayout.setMaxHeight((int) (height * density));

		layout.addView(adWhirlLayout);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adWhirlGeneric() {
		// TODO Auto-generated method stub
		
	}

}
