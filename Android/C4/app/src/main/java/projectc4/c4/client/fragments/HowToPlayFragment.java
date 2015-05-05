package projectc4.c4.client.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import projectc4.c4.R;

/**
 * @author Kalle Bornemark, Jimmy Maksymiw, Erik Sandgren, Emil Sandgren.
 */
public class HowToPlayFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_howtoplay, container, false);

        TextView rush = (TextView)view.findViewById(R.id.rush);
        TextView rushdesc = (TextView)view.findViewById(R.id.rushdesc);
        TextView color = (TextView)view.findViewById(R.id.color);
        TextView colordesc = (TextView)view.findViewById(R.id.colordesc);
        TextView bomb = (TextView)view.findViewById(R.id.bomb);
        TextView bombdesc = (TextView)view.findViewById(R.id.bombdesc);
        TextView extra = (TextView)view.findViewById(R.id.extra);
        TextView extradesc = (TextView)view.findViewById(R.id.extradesc);
        TextView swap = (TextView)view.findViewById(R.id.swap);
        TextView swapdesc = (TextView)view.findViewById(R.id.swapdesc);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/msyi.ttf");

        rush.setTypeface(type, Typeface.BOLD);
        rushdesc.setTypeface(type, Typeface.BOLD);
        bomb.setTypeface(type, Typeface.BOLD);
        bombdesc.setTypeface(type, Typeface.BOLD);
        color.setTypeface(type, Typeface.BOLD);
        colordesc.setTypeface(type, Typeface.BOLD);
        extra.setTypeface(type, Typeface.BOLD);
        extradesc.setTypeface(type, Typeface.BOLD);
        swap.setTypeface(type, Typeface.BOLD);
        swapdesc.setTypeface(type, Typeface.BOLD);
        return view;
    }
}
