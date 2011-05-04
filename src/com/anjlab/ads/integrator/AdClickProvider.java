package com.anjlab.ads.integrator;

import adclick.mod.AdClick;
import android.app.Activity;
import android.widget.LinearLayout;

public class AdClickProvider extends AbstractAdProvider {

	private String publisherId;
	
	public AdClickProvider(String publisherId){
		this.publisherId = publisherId;
	}
		
	@Override
	public void tryLoadAd(Activity actv, LinearLayout layout) {
		final AdClick ADS = new AdClick();
		ADS.CONTX = actv;
		ADS.PUB_ID = publisherId;
		ADS.AdBox = layout;
		ADS.ShowAD();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}
}
