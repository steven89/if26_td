package fr.utt.tweetit;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TitleBarFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link TitleBarFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class TitleBarFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	
	private TextView app_title;
	private ImageView app_copyright;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment TitleBarFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static TitleBarFragment newInstance(String param1, String param2) {
		TitleBarFragment fragment = new TitleBarFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public TitleBarFragment() {
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
		View rootView = inflater.inflate(R.layout.fragment_title_bar, container, false);
		this.app_title = (TextView) rootView.findViewById(R.id.appname);
		this.app_copyright = (ImageView) rootView.findViewById(R.id.copyright);
		final Activity currentActivity = this.getActivity();
		this.app_copyright.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Dialog popup = new Dialog(currentActivity);
				popup.setContentView(R.layout.auteur_layout);
				popup.setTitle(R.string.auteur_title);
				popup.setCancelable(true);
				//Button sendBtn = (Button) popup.findViewById(R.id.btn_envoi);
//				sendBtn.setOnClickListener(new View.OnClickListener(){
//
//					@Override
//					public void onClick(View v) {
//						WebServiceManager.postNewMessage(inputMessage.getText().toString());
//						popup.dismiss();
//					}
//					
//				});
				popup.show();
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
