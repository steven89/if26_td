package fr.utt.tweetit;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactArrayAdapter extends ArrayAdapter<JSONObject> {

	public static final String CONTACT_FIRSTNAME = "first_name";
	public static final String CONTACT_LASTNAME = "last_name";
	public static final String CONTACT_EMAIL = "email";
	public static final String CONTACT_LASTMESSAGE = "message";
	
	private Context context;
	private ArrayList<JSONObject> itemsList;
	
	public ContactArrayAdapter(Context context, ArrayList<JSONObject> itemsList) {
		super(context, R.layout.contact_row, itemsList);
		this.context = context;
		this.itemsList = itemsList;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.contact_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelFirstName = (TextView) rowView.findViewById(R.id.labelFirstName);
        TextView labelLastName = (TextView) rowView.findViewById(R.id.labelLastName);
        TextView labelEmail = (TextView) rowView.findViewById(R.id.labelEmail);

        // 4. Set the text for textView
        try {
			labelFirstName.setText(itemsList.get(position).getString(ContactArrayAdapter.CONTACT_FIRSTNAME));
			labelLastName.setText(itemsList.get(position).getString(ContactArrayAdapter.CONTACT_LASTNAME));
	        labelEmail.setText(itemsList.get(position).getString(ContactArrayAdapter.CONTACT_EMAIL));
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 5. return rowView
        return rowView;
	}
}
