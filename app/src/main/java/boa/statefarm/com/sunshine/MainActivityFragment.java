package boa.statefarm.com.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import boa.statefarm.com.sunshine.adapters.ForecastAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    View rootView;
    ForecastAdapter forecastAdapter;
    @BindView(R.id.listview_forecast) RecyclerView forcastRecycler;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "Today - Sunny - 31/17",
                "Tomorrow - Foggy - 21/8",
                "Weds - Cloudy - 22/17",
                "Thurs - Rainy - 18/11",
                "Fri - Foggy - 21/10",
                "Sat - Sunny - 23/18",
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));


        forecastAdapter = new ForecastAdapter(weekForecast,getActivity());

        forcastRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        forcastRecycler.setHasFixedSize(true);
        forcastRecycler.setAdapter(forecastAdapter);




        return rootView;
    }
}
