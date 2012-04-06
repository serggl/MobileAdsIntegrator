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
	private boolean started;
	private boolean cycle;
	private int maxCycleDepth;
	private int cycleDepth;
	
	public AdIntegrator(){
		providers = new ArrayList<AbstractAdProvider>();
		rnd = new Random();
		started = false;
		cycle = false;
		maxCycleDepth = 100;
		cycleDepth = 0;
	}
	
	public void setRandomOrder(boolean value){
		isRandomOrder = value;
	}
	
	public void setCycle(boolean value){
		cycle = value;
	}
	
	public void setMaxCycleDepth(int value){
		maxCycleDepth = value;
	}
	
	public void register(AbstractAdProvider provider){
		providers.add(provider);
		provider.setCallbackListener(this);
	}
	
	public void loadAds(Activity ctx, int layoutId){
		if (!providers.isEmpty()){
			stop();
			started = true;
			context = ctx;
			layout = (LinearLayout)ctx.findViewById(layoutId);
			if (layout == null)
				return;
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
		if (!started)
			return;
		int size = providers.size();
		if (size == 0)
			return;
		if (size > currentIdx) {
			current = providers.get(currentIdx);
			layout.removeAllViews();
			current.tryLoadAd(context, layout);
		} else{
			if (cycle) {
				cycleDepth ++;
				if (cycleDepth < maxCycleDepth) {
					currentIdx = 0;
					loadCurrentProvider();
				}
			}
		}
	}
	
	public void stop(boolean clearBanner){
		if (started && current != null){
			Log.i("Ads", "stopping ad provider");
			started = false;
			current.stop();
			current = null;
			if (clearBanner && layout != null)
				layout.removeAllViews();
		}
	}
	
	public void stop(){
		stop(false);
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
