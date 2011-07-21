package com.anjlab.ads.integrator;

import adclick.mod.AdClick;
import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

public class AdClickProvider extends AbstractAdProvider {

	private String publisherId;
	private AdClick adClick;
	
	public AdClickProvider(String publisherId){
		this.publisherId = publisherId;
	}
		
	@Override
	public void tryLoadAd(Activity actv, LinearLayout layout) {
		Log.d("Ads", "loading AdClick");
		adClick = new AdClick();
		adClick.CONTX = actv;
		adClick.PUB_ID = publisherId;
		adClick.AdBox = layout;
		adClick.ShowAD();
	}

	@Override
	public void stop() {
		Log.d("Ads", "stoping AdClick");
		if (adClick != null)
			adClick.AdBox = null;
	}
}
