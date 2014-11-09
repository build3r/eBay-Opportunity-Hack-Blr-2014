package com.example.parishodhfinancemanagerapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SecondActivity extends FragmentActivity {
	
	ViewPager pageViewer;
	TabPagerAdapter tabAdapter;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		tabAdapter = new TabPagerAdapter(getSupportFragmentManager());
		pageViewer = (ViewPager)findViewById(R.id.pager);
		pageViewer.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                      actionBar = getActionBar();
                      actionBar.setSelectedNavigationItem(position);                    }
                });
		pageViewer.setAdapter(tabAdapter);
		
		
		actionBar = getActionBar();

	    // Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    // Create a tab listener that is called when the user changes tabs.
	    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	        	pageViewer.setCurrentItem(tab.getPosition());
	        }

	        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // hide the given tab
	        }

	        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // probably ignore this event
	        }
	    };

	    actionBar.addTab( actionBar.newTab().setText("My Bills").setTabListener(tabListener));
	    actionBar.addTab( actionBar.newTab().setText("Submit Bills").setTabListener(tabListener));
	}
	
	public void launchUpload(View view) {
		Intent intent = new Intent(view.getContext().getApplicationContext(), ThirdActivity.class);
		startActivity(intent);
	}
	
	
    // When any link among the given bills is clicked it can be viewed/Edited
    public void switchToDetailsActivity(View view) {
    	Intent intent = new Intent(this, DetailsActivity.class);
		startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
