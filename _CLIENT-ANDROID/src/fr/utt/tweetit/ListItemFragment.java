package fr.utt.tweetit;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import fr.utt.tweetit.OnFragmentInteractionListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link ListItemFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link ListItemFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class ListItemFragment extends Fragment {
	//Tag de classe, permettant de récupérer les fragments de cette classe
	// ex :ListItemFragment test = (ListItemFragment) fragManager.findFragmentByTag(ListItemFragment.TAG)
	public static final String TAG = "listItemFrag";
	
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private static final String LISTVIEW_TYPE = "listViewType";

	private OnFragmentInteractionListener mListener;
	private ListView listView;
	//adapter entre la listView et les données
	private  Adapter adapter; 
	
	/********************** FACTORY AND CONSTRUCTOR **********************************/
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ListItemFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ListItemFragment newInstance(int listViewType, String param2) {
		ListItemFragment fragment = new ListItemFragment();
		Bundle args = new Bundle();
		//Manière officielle de passer des args au fragment, 
		//mais ne permet pas une mise à jour d'argument pendant le cycle de vie du fragment
		args.putInt(ListItemFragment.LISTVIEW_TYPE, listViewType);
		fragment.setArguments(args);
		return fragment;
	}

	public ListItemFragment() {
		// Required empty public constructor
	}
	
	/*************** CYCLE DE VIE DU FRAGMENT ****************/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView =  inflater.inflate(R.layout.fragment_list_item, container, false);
		
		this.listView = (ListView) rootView.findViewById(R.id.fragList_listView);
		
		//Ajout d'une progesse bar
		ProgressBar progressBar = new ProgressBar(getActivity());
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		progressBar.setIndeterminate(true);
		this.listView.setEmptyView(progressBar);
		container.addView(progressBar);
        
        // définission de l'adapter de la viewList
		if(getArguments() != null){	
			switch(getArguments().getInt(ListItemFragment.LISTVIEW_TYPE)){
				case R.integer.defaultList :
					String[] samples = {"France", "Allemagne", "Italie"};
					this.listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, samples));
					break;
				case  R.integer.friendList: 
					//SAMPLE DATA
					String json = "{'first_name':'Karina','last_name':'Sokolova'"
							+ ",'email':'test2@test.fr','message':{'message':'jizi',"
							+ "'date':'2013-10-17 16:02:59','sent':false}}";
					JSONObject contact1;
					ArrayList<JSONObject> datas = new ArrayList<JSONObject>();
					try {
						contact1 = new JSONObject(json);
						datas.add(contact1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ContactArrayAdapter newAdapter = new ContactArrayAdapter(getActivity(), datas);
					this.listView.setAdapter(newAdapter);
					break;
			}
		}
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("ListItemFragment.onAttach", activity.getClass().toString());
		try {
			mListener = (OnFragmentInteractionListener) activity;
			
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}
	
	/******************* INTERACTIONS HANDLERS *********************************/
	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}
	
	/******************** GETTERS AND SETTERS **********************************/
}
