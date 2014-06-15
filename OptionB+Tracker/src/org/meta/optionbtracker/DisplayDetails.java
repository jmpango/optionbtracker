package org.meta.optionbtracker;

import java.util.ArrayList;

import org.meta.optionbtracker.async.impl.AsyncPipeImpl;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DisplayDetails extends FragmentActivity {
	
	private String entity;
	private AsyncPipeImpl serverData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_details);
		
		this.entity = getIntent().getExtras().getString("entity");
		this.serverData = new AsyncPipeImpl();
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		TextView appHeaderView = (TextView) findViewById(R.id.appHeader);
		appHeaderView.setText(getEntity());
		
		
		Button btnNextScreen = (Button) findViewById(R.id.extBtn);
		btnNextScreen.setOnClickListener(new View.OnClickListener() {
			 
			   public void onClick(View arg0) {
	            	finish();
	            }
	        });
			
			loadGUI();
		}
		
		@SuppressWarnings("unchecked")
		private void loadGUI() {
			ListView detailedListView = (ListView) findViewById(R.id.detailsView);
			ArrayList<String> itemsList = (ArrayList<String>) serverData.getRegisteredFacilities(getEntity());
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsList);
			detailedListView.setAdapter(adapter);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.home:
				Intent upParent =  new Intent(this, Startup.class);
				startActivity(upParent);
				DisplayDetails.this.finish();
			default:
				return super.onOptionsItemSelected(item);
			}
		}

		public String getEntity() {
			return entity;
		}
	
}
