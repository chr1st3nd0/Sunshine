package boa.statefarm.com.sunshine.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import boa.statefarm.com.sunshine.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class DetailFragment extends Fragment {

    public static final String TAG = "DetailFragment";
    View rootView;
    @BindView(R.id.forcast_bundle_text)TextView forcastText;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,rootView);

        String strtext = getArguments().getString("forecast");
        forcastText.setText(strtext);

        return rootView;
    }


}
