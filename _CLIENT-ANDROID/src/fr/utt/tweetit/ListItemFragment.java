package fr.utt.tweetit;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
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
	private  ArrayAdapter DataToViewAdapter; 
	private ArrayList dataList;
	
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
		WebServiceManager.initSimpleData();
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
		ListAdapter dataToView = null;
		
//		//Ajout d'une progesse bar
//		ProgressBar progressBar = new ProgressBar(getActivity());
//		progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		progressBar.setIndeterminate(true);
//		this.listView.setEmptyView(progressBar);
//		container.addView(progressBar);
        
        // définission de l'adapter de la viewList
		if(getArguments() != null){	
			switch(getArguments().getInt(ListItemFragment.LISTVIEW_TYPE)){
				case R.integer.defaultList :
					this.dataList = WebServiceManager.getSimpleData();
					Log.i("steven", this.dataList.toString());
					dataToView = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, this.dataList); 
					this.listView.setAdapter(dataToView);
					break;
				case  R.integer.followersList: 
					//SAMPLE DATA
					this.dataList = WebServiceManager.getFollowersData();
					dataToView = new ContactArrayAdapter(getActivity(), this.dataList);
					Log.d("DATA", (Integer.toString(this.dataList.size())));
					this.listView.setAdapter(dataToView);
					this.setItemClickHandler();
					break;
					
				case  R.integer.followeesList: 
					//SAMPLE DATA
					this.dataList = WebServiceManager.getFolloweesData();
					dataToView = new ContactArrayAdapter(getActivity(), this.dataList);
					Log.d("DATA", (Integer.toString(this.dataList.size())));
					this.listView.setAdapter(dataToView);
					this.setItemClickHandler();
					break;
				default : 
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
	
	private void setItemClickHandler(){
		if(this.listView == null)
			return;
		final Activity currentActivity = (Activity) getActivity();
		this.listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View rawClickedView, int itemPosition, long itemId) {
				Log.d("ITEM CLICKED", rawClickedView.toString() );
				final Dialog popup = new Dialog(currentActivity);
				popup.setContentView(R.layout.fragment_new_message);
				popup.setTitle(R.string.newTweet);
				popup.setCancelable(true);
				Button sendBtn = (Button) popup.findViewById(R.id.btn_envoi);
				final TextView inputMessage = (TextView) popup.findViewById(R.id.text_message);
				sendBtn.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						WebServiceManager.postNewMessage(inputMessage.getText().toString());
						popup.dismiss();
					}
					
				});
				popup.show();
			}});
	}
	
	/******************** GETTERS AND SETTERS **********************************/
}
