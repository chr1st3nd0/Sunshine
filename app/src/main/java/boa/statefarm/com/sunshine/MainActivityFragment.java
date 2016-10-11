package boa.statefarm.com.sunshine;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import boa.statefarm.com.sunshine.adapters.ForecastAdapter;
import boa.statefarm.com.sunshine.asyncs.DownloadWebpageTask;
import boa.statefarm.com.sunshine.decorations.DividerItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    View rootView;
    ForecastAdapter forecastAdapter;
    public static String TAG = "MainActivityFragment";
    @BindView(R.id.listview_forecast) RecyclerView forcastRecycler;
    InterfaceSetEnabled interfaceSetEnabled;
    List<String> weekForecast;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
        } else {
            Snackbar.make(rootView, "No network available", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "Today - Sunny - 31/17",
                "Tomorrow - Foggy - 21/8",
                "Weds - Cloudy - 22/17",
                "Thurs - Rainy - 18/11",
                "Fri - Foggy - 21/10",
                "Sat - Sunny - 23/18",
        };
        weekForecast = new ArrayList<String>(Arrays.asList(data));

        //API_KEY
        //2ff239b1d9ee7ab593d1c041d1a9f6ed

        refresh();
        forecastAdapter = new ForecastAdapter(weekForecast,getActivity(), new ForecastAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Snackbar.make(rootView, item, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                InterfaceStartDetails interfaceStartDetails = (InterfaceStartDetails)getActivity();
                interfaceStartDetails.startDetails(item);

            }
        });
        forcastRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        forcastRecycler.setHasFixedSize(true);
        forcastRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        forcastRecycler.setAdapter(forecastAdapter);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecast_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.forcast_settings) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh() {

        new DownloadWebpageTask()
        {
            @Override
            protected void onPostExecute(String[] result) {
                super.onPostExecute(result);

                weekForecast = new ArrayList<String>(Arrays.asList(result));
                forecastAdapter.updateList(weekForecast);
                forecastAdapter.notifyDataSetChanged();

                interfaceSetEnabled = (InterfaceSetEnabled)getActivity();
                interfaceSetEnabled.setEnabled();
            }
        }.execute("30019");
    }

    public interface InterfaceSetEnabled
    {
        void setEnabled();
    }

    public interface InterfaceStartDetails
    {
        void startDetails(String forcast);
    }



}
