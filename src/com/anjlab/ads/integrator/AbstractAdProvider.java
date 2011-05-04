package com.anjlab.ads.integrator;

import android.app.Activity;
import android.widget.LinearLayout;

public abstract class AbstractAdProvider {
	protected IAdCallback callback;
	
	public abstract void tryLoadAd(Activity actv, LinearLayout layout);
	
	public abstract void stop();
	
	protected void onLoadFailed(){
		if (callback != null)
			callback.onLoadFailed(this);
	}
	
	public void setCallbackListener(IAdCallback callbackListener){
		callback = callbackListener;
	}
}
