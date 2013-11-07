package fr.utt.tweetit;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import fr.utt.tweetit.OnFragmentInteractionListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TopMenuFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link TopMenuFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class TopMenuFragment extends Fragment {
	//Tag de classe, permettant de récupérer les fragments de cette classe
	// ex :TopMenuFragment test = (TopMenuFragment) fragManager.findFragmentByTag(TopMenuFragment.TAG)
	public static final String TAG = "topMenuFragment";
	public static final String UriPath = "application/TopMenuFragment";
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private OnFragmentInteractionListener mListener;
	private Button meBtn;
	private Button friendsBtn;
	private Button tweetItBtn;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment TopMenuFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static TopMenuFragment newInstance(String param1, String param2) {
		TopMenuFragment fragment = new TopMenuFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public TopMenuFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			getArguments().getString(ARG_PARAM1);
			getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_top_menu, container, false);
		this.meBtn = (Button) rootView.findViewById(R.id.meBtn);
		this.friendsBtn = (Button) rootView.findViewById(R.id.friendsBtn);
		this.tweetItBtn = (Button) rootView.findViewById(R.id.tweetItBtn);
		
		this.meBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.onFragmentInteraction(Uri.parse("click://"+TopMenuFragment.UriPath+"#"+v.getId()));
			}
		});
		
		this.friendsBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.onFragmentInteraction(Uri.parse("click://"+TopMenuFragment.UriPath+"#"+v.getId()));
			}
		});
		
		this.tweetItBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.onFragmentInteraction(Uri.parse("click://"+TopMenuFragment.UriPath+"#"+v.getId()));
			}
		});
		return rootView;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
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
		this.mListener = null;
		this.meBtn = null;
		this.friendsBtn = null;
		this.tweetItBtn = null;
	}

}
