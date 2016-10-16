package boa.statefarm.com.sunshine.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ShareActionProvider mShareActionProvider;
    private static final String FORECAST_SHARE_HASHTAG = "#SunshineApp";
            private String mForecastStr;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,rootView);

        mForecastStr = getArguments().getString("forecast");
        forcastText.setText(mForecastStr);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);
        setShareIntent(createShareForcastIntent());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_share) {
        }
        return super.onOptionsItemSelected(item);

    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    // Call to update the share intent
    private Intent createShareForcastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,mForecastStr + FORECAST_SHARE_HASHTAG);
        return shareIntent;
    }

}
