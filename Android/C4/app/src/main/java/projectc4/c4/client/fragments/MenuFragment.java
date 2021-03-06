package projectc4.c4.client.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import c4.utils.C4Color;
import c4.utils.C4Constants;
import projectc4.c4.R;
import projectc4.c4.client.MainActivity;

/**
 * @author Kalle Bornemark, Jimmy Maksymiw, Erik Sandgren, Emil Sandgren.
 */
public class MenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // init buttons
        Button buttonPlayLocal = (Button)view.findViewById(R.id.buttonPlayLocal);
        Button buttonPlayOnline = (Button)view.findViewById(R.id.buttonPlayOnline);
        Button buttonHowToPlay = (Button)view.findViewById(R.id.buttonHowToPlay);
        Button buttonAbout = (Button)view.findViewById(R.id.buttonAbout);

        // Init buttongraphics
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/msyi.ttf");

        buttonPlayLocal.setTypeface(type,Typeface.BOLD);
        buttonPlayOnline.setTypeface(type,Typeface.BOLD);
        buttonHowToPlay.setTypeface(type, Typeface.BOLD);
        buttonAbout.setTypeface(type, Typeface.BOLD);

        buttonPlayLocal.setTextColor(C4Color.WHITE);
        buttonPlayOnline.setTextColor(C4Color.WHITE);
        buttonHowToPlay.setTextColor(C4Color.WHITE);
        buttonAbout.setTextColor(C4Color.WHITE);

        // Init listeners
        ButtonClickListener buttonClickListener = new ButtonClickListener();

        buttonPlayLocal.setOnClickListener(buttonClickListener);
        buttonPlayOnline.setOnClickListener(buttonClickListener);
        buttonHowToPlay.setOnClickListener(buttonClickListener);
        buttonAbout.setOnClickListener(buttonClickListener);

        return view;
    }

    /**
     * Private class to handel the buttons in the MenuFragment.
     */
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.setCustomAnimations(R.anim.transition1, R.anim.transition2, R.anim.transition1, R.anim.transition2);
            transaction.setCustomAnimations(R.anim.in, R.anim.out, R.anim.out2, R.anim.in2);

            switch (v.getId()) {
                case R.id.buttonPlayLocal:
                    transaction.replace(R.id.activity_layout_fragmentpos, new GameSettingsFragment()).addToBackStack("LocalSettings").commit();

                    break;

                case R.id.buttonPlayOnline:
                    if ((((MainActivity)getActivity()).getClientController().getClient().getUser() != null)) {
                        transaction.replace(R.id.activity_layout_fragmentpos, new MatchmakingFragment()).addToBackStack("Matchmaking").commit();
                    } else {
                        transaction.replace(R.id.activity_layout_fragmentpos, new LoginFragment()).addToBackStack("LogIn").commit();
                    }
                    break;

                case R.id.buttonHowToPlay:
                    transaction.replace(R.id.activity_layout_fragmentpos, new HowToPlayFragment()).addToBackStack(null).commit();
                    break;

                case R.id.buttonAbout:
                    transaction.replace(R.id.activity_layout_fragmentpos, new AboutFragment()).addToBackStack(null).commit();
                    break;
            }

        }

    }
}
