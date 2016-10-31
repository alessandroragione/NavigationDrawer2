package com.nd2.org.navigationdrawer2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dragionier on 28/10/2016.
 */

public class OperatoriAdapter extends ArrayAdapter<Operatori> {

    public OperatoriAdapter(Context context, int i ,List<Operatori> users) {
        super(context,i, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Operatori operatore = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_result, parent, false);
        }
        // Lookup view for data population
        TextView Idtv = (TextView) convertView.findViewById(R.id.Idtv);
        TextView Nometv = (TextView) convertView.findViewById(R.id.Nometv);
        TextView Cognometv = (TextView) convertView.findViewById(R.id.Cognometv);
        ImageButton details = (ImageButton) convertView.findViewById(R.id.imageButton);
        details.setVisibility(View.VISIBLE);
        details.setTag(operatore.getId());
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(v.getTag());
            }
        });
        // Populate the data into the template view using the data object
        Idtv.setText(operatore.getId());
        Nometv.setText(operatore.getNome());
        Cognometv.setText(operatore.getCognome());
        // Return the completed view to render on screen
        return convertView;
    }

}
