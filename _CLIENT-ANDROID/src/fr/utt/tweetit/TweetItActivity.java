package fr.utt.tweetit;

import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import fr.utt.tweetit.OnFragmentInteractionListener;

public class TweetItActivity extends FragmentActivity implements OnFragmentInteractionListener{

	private  ListItemFragment myTweetListFrag;
	private  ListItemFragment friendListFrag;

	private JSONObject meTabData;
	private JSONObject followersTabData;
	private JSONObject followingTabData;

	private Bundle extras;
	private TextView token;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_it);
		
		extras = getIntent().getExtras();
		//TextView lab_token = (TextView) findViewById(R.id.lab_token);
		//lab_token.setText(extras.get("token").toString());
		
		this.initFragment();
		this.showFragment(this.myTweetListFrag);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweet_it, menu);
		return true;
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		Bundle args = new Bundle();
		//on récupère l'id du 'bouton' ou tout autre element ayant trigger une interaction sur le fragment
		// l'id est stocké dans la partie 'fragment' de l'Uri (ex : 'protocol://domain/path#fragment)
		switch((Integer.parseInt(uri.getFragment()))){
			case R.id.meBtn : 
				this.showFragment((Fragment) this.myTweetListFrag, android.R.anim.slide_out_right, android.R.anim.slide_in_left);
				break;
			case R.id.friendsBtn :
				Log.d("LIST_TYPE", "R.integer.friendList");
				this.showFragment((Fragment) this.friendListFrag, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
				break;
			case R.id.tweetItBtn :
				break;
			default :
				break;
		}		
	}
	
	public void initFragment(){
		final FragmentManager fragManager = getSupportFragmentManager();
		//pour chaque frag dynamique
		this.myTweetListFrag = (ListItemFragment) fragManager.findFragmentByTag(ListItemFragment.TAG);
		if(this.myTweetListFrag == null){
			this.myTweetListFrag = ListItemFragment.newInstance(R.integer.defaultList, "test");
		}
		
		this.friendListFrag = (ListItemFragment) fragManager.findFragmentByTag(ListItemFragment.TAG);
		if(this.friendListFrag == null){
			this.friendListFrag = ListItemFragment.newInstance(R.integer.friendList, "test");
		}
	}
	
	//showFragment simple, sans mise à jour du fragment (utile pour le fragment d'envoie de msg)
	public int showFragment(final Fragment fragment, int directionFrom, int directionTo){
		if(fragment == null){
			return 0;
		}
		final FragmentManager fragManager = getSupportFragmentManager();
		final FragmentTransaction fragTransaction = fragManager.beginTransaction();
		fragTransaction.setCustomAnimations(directionFrom, directionTo);
		fragTransaction.replace(R.id.listItemFragContainer, fragment);
		return fragTransaction.commit();
	}
	
	public int showFragment(final Fragment fragment){
		if(fragment == null){
			return 0;
		}
		final FragmentManager fragManager = getSupportFragmentManager();
		final FragmentTransaction fragTransaction = fragManager.beginTransaction();
		fragTransaction.replace(R.id.listItemFragContainer, fragment);
		return fragTransaction.commit();
	}
}
