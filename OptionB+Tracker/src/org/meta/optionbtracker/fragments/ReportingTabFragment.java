package org.meta.optionbtracker.fragments;

import org.meta.optionbtracker.DisplayDetails;
import org.meta.optionbtracker.Startup;
import org.meta.optionbtracker.async.AsyncPipe;
import org.meta.optionbtracker.async.impl.AsyncPipeImpl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ReportingTabFragment extends ListFragment {
	
	private ArrayAdapter<String> reportingListAdapter;
	private AsyncPipe getServerData = new AsyncPipeImpl();
	private String entity;
	
	private Startup startup;

	public ReportingTabFragment() {
		

	}
	
	public ReportingTabFragment(Startup startup, String entity) {
		if (entity == null)
			entity = "National";

		this.entity = entity;
		this.startup = startup;
	}

	@SuppressLint("InlinedApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		reportingListAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, getServerData.getEntities(entity));
		setListAdapter(reportingListAdapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public void onListItemClick(ListView list, View v, int position, long id) {
		if(position == 3){
			Toast.makeText(getActivity(),
					getListView().getItemAtPosition(position).toString(),
					Toast.LENGTH_LONG).show();
		}else{
			Intent intent = new Intent(getStartup(), DisplayDetails.class);
			intent.putExtra("entity", getEntity());
			startActivity(intent);
			
		}
	}

	public void onStart() {
		super.onStart();
	}

	public Startup getStartup() {
		return startup;
	}

	public String getEntity() {
		return entity;
	}
	
	
}
