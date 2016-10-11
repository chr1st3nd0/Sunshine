package boa.statefarm.com.sunshine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import boa.statefarm.com.sunshine.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dxhf on 5/26/15.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    private List<String> mForecasts;
    private Context mContext;
    private final OnItemClickListener listener;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View parentView;

        @BindView(R.id.forecast_textview) TextView forecast;
        @BindView(R.id.frame_area)
        FrameLayout frameLayout;

        public ViewHolder(View v) {
            super(v);
            this.parentView = v;
            ButterKnife.bind(this,v);

            v.setClickable(true);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ForecastAdapter(List<String> conv, Context context,OnItemClickListener listener) {
        mForecasts = conv;
        mContext = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item, parent, false);

        return new ViewHolder(v);
    }

    public void updateList(List<String> newList)
    {
        mForecasts = newList;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.forecast.setText(mForecasts.get(position));
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                listener.onItemClick(mForecasts.get(position));

            }

        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    public interface OnItemClickListener {

        void onItemClick(String item);

    }




}
