package boa.statefarm.com.sunshine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import boa.statefarm.com.sunshine.R;
import boa.statefarm.com.sunshine.fragments.DetailFragment;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Bundle bundle = new Bundle();
        bundle.putString("forecast",message);

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().add(R.id.activity_detail,detailFragment,detailFragment.TAG).commit();

    }
}
