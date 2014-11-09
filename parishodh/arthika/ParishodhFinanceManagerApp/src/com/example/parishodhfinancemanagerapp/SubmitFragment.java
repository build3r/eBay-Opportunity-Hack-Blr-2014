package com.example.parishodhfinancemanagerapp;


import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SubmitFragment extends Fragment {

	private Spinner spinner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View mainView = inflater.inflate(R.layout.submit_bill_fragment, container, false);
		setChapterSpinnerContent( mainView );
		setProjectSpinnerContent( mainView );
		
		return mainView;
	}
	
	private void setChapterSpinnerContent( View view )
	{
		List<String> list = new ArrayList<String>();
		list.add("Bangalore");
		list.add("Hyderabad");
		list.add("Chennai");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext().getApplicationContext(), android.R.layout.simple_spinner_item, list);
		
		spinner = (Spinner) view.findViewById( R.id.spinner1);
		spinner.setAdapter( adapter );
	}
	
	private void setProjectSpinnerContent( View view )
	{
		List<String> list = new ArrayList<String>();
		list.add("HR");
		list.add("SAP");
		list.add("SHG");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext().getApplicationContext(), android.R.layout.simple_spinner_item, list);
		
		spinner = (Spinner) view.findViewById( R.id.spinner2);
		spinner.setAdapter( adapter );
	}
	
}