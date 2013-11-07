package fr.utt.tweetit;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link IntermediateMenuFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the
 * {@link IntermediateMenuFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class IntermediateMenuFragment extends Fragment {
	//Tag de classe, permettant de récupérer les fragments de cette classe
	// ex :TopMenuFragment test = (TopMenuFragment) fragManager.findFragmentByTag(TopMenuFragment.TAG)
	public static final String TAG = "intermediateMenuFragment";
	public static final String UriPath = "application/IntermediateMenuFragment";
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;
	private Button followersBtn;
	private Button followeesBtn;
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment IntermediateMenuFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static IntermediateMenuFragment newInstance(String param1,
			String param2) {
		IntermediateMenuFragment fragment = new IntermediateMenuFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public IntermediateMenuFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_intermediate_menu, container, false);
		this.followeesBtn = (Button) rootView.findViewById(R.id.followeesBtn);
		this.followersBtn = (Button) rootView.findViewById(R.id.followersBtn);
		
		this.followeesBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.onFragmentInteraction(Uri.parse("click://"+UriPath+"#"+v.getId()));
			}
		});
		
		this.followersBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.onFragmentInteraction(Uri.parse("click://"+UriPath+"#"+v.getId()));
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
		mListener = null;
	}
}
