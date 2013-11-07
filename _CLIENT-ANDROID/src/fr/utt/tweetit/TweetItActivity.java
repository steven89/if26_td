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
import android.view.Window;
import android.widget.TextView;
import fr.utt.tweetit.OnFragmentInteractionListener;

public class TweetItActivity extends FragmentActivity implements OnFragmentInteractionListener{

	private  ListItemFragment myTweetListFrag;
	private  ListItemFragment followersList;
	private  ListItemFragment followeesList;
	private NewMessageFragment postMessageFrag;
	private IntermediateMenuFragment intermediateMenu;
	
	private Fragment currentFrag;

	private JSONObject meTabData;
	private JSONObject followersTabData;
	private JSONObject followingTabData;

	private Bundle extras;
	private String token;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tweet_it);
		
		extras = getIntent().getExtras();
		this.token = extras.getString("token").toString();
		
		this.initFragment();
		this.showFragment(R.id.listItemFragContainer, this.myTweetListFrag);
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
				this.removeFragment((Fragment) this.intermediateMenu);
				this.showFragment(R.id.listItemFragContainer, (Fragment) this.myTweetListFrag);
				break;
			case R.id.friendsBtn :
				this.showFragment(R.id.intermediateMenuFragContainer, (Fragment) this.intermediateMenu);
				if(!(this.followeesList.isInLayout()) || this.followersList.isInLayout())
					this.showFragment(R.id.listItemFragContainer, (Fragment) this.followersList);
				break;
			case R.id.tweetItBtn :
				this.removeFragment((Fragment) this.intermediateMenu);
				this.showFragment(R.id.listItemFragContainer, (Fragment) this.postMessageFrag);
				break;
			case R.id.followeesBtn :
				this.showFragment(R.id.listItemFragContainer, (Fragment) this.followeesList);
				break;
			case R.id.followersBtn :
				this.showFragment(R.id.listItemFragContainer, (Fragment) this.followersList);
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
			this.myTweetListFrag = ListItemFragment.newInstance(R.integer.defaultList, null);
		}
		
		this.followersList = (ListItemFragment) fragManager.findFragmentByTag(ListItemFragment.TAG);
		if(this.followersList == null){
			this.followersList = ListItemFragment.newInstance(R.integer.followersList, null);
		}
		
		this.followeesList = (ListItemFragment) fragManager.findFragmentByTag(ListItemFragment.TAG);
		if(this.followeesList == null){
			this.followeesList = ListItemFragment.newInstance(R.integer.followeesList, null);
		}
		
		this.postMessageFrag = (NewMessageFragment) fragManager.findFragmentByTag(NewMessageFragment.TAG);
		if(this.postMessageFrag == null){
			this.postMessageFrag = NewMessageFragment.newInstance(null, null);
		}
		
		this.intermediateMenu = (IntermediateMenuFragment) fragManager.findFragmentByTag(IntermediateMenuFragment.TAG);
		if(this.intermediateMenu == null){
			this.intermediateMenu = IntermediateMenuFragment.newInstance(null, null);
		}
	}
	
	public int showFragment(int containerID, final Fragment fragment){
		if(fragment == null){
			return 0;
		}
		final FragmentManager fragManager = getSupportFragmentManager();
		final FragmentTransaction fragTransaction = fragManager.beginTransaction();
		fragTransaction.replace(containerID, fragment);
		/*if(this.currentFrag!=null)
			fragTransaction.detach(this.currentFrag);
		this.currentFrag = fragment;
		fragTransaction.attach(this.currentFrag);*/
		return fragTransaction.commit();
	}
	
	public int removeFragment(final Fragment fragment){
		if(fragment == null){
			return 0;
		}
		final FragmentManager fragManager = getSupportFragmentManager();
		final FragmentTransaction fragTransaction = fragManager.beginTransaction();
		fragTransaction.remove(fragment);
		return fragTransaction.commit();
	}
	
	public String getToken(){
		return this.token;
	}
}
