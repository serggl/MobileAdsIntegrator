package com.anjlab.ads.integrator;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

public class AdIntegrator implements IAdCallback {

	private Activity context;
	private LinearLayout layout;
	private ArrayList<AbstractAdProvider> providers;
	private AbstractAdProvider current;
	private int currentIdx;
	private boolean isRandomOrder;
	private Random rnd;
	
	public AdIntegrator(){
		providers = new ArrayList<AbstractAdProvider>();
		rnd = new Random();
	}
	
	public void setRandomOrder(boolean value){
		isRandomOrder = value;
	}
	
	public void register(AbstractAdProvider provider){
		providers.add(provider);
		provider.setCallbackListener(this);
	}
	
	public void loadAds(Activity ctx, int layoutId){
		if (!providers.isEmpty()){
			stop();
			context = ctx;
			layout = (LinearLayout)ctx.findViewById(layoutId);
			Log.i("Ads", "lodaing ads");
			layout.removeAllViews();
			if (isRandomOrder)
				currentIdx = rnd.nextInt(providers.size());
			else
				currentIdx = 0;
			loadCurrentProvider();
		}			
	}
	
	private void loadCurrentProvider(){
		if (providers.size() > currentIdx) {
			current = providers.get(currentIdx);
			current.tryLoadAd(context, layout);
		}
	}
	
	public void stop(){
		if (current != null){
			Log.i("Ads", "stopping ad provider");
			current.stop();
			current = null;
		}
	}
	
	@Override
	public void onLoadFailed(AbstractAdProvider provider) {
		Log.i("Ads", "lodaing next provider");
		layout.removeAllViews();
		if (isRandomOrder)
			currentIdx = rnd.nextInt(providers.size());
		else
			currentIdx++;
		loadCurrentProvider();		
	}

}
