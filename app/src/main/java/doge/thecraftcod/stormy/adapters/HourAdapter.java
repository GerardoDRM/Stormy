package doge.thecraftcod.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import doge.thecraftcod.stormy.R;
import doge.thecraftcod.stormy.weather.Hour;

/**
 * Created by gerardo on 19/07/15.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Hour[] mHours;
    private Context mcontext;

    public HourAdapter(Context contexts, Hour[] hours) {
        mcontext = contexts;
        mHours = hours;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hourly_list_item, viewGroup, false);
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder hourViewHolder, int i) {
        hourViewHolder.bindHour(mHours[i]);

    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIcon;


        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = (TextView) itemView.findViewById(R.id.timeL);
            mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryL);
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.tempL);
            mIcon = (ImageView) itemView.findViewById(R.id.iconImg);
        }

        public void bindHour(Hour hour) {
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getmSummary());
            mTemperatureLabel.setText(hour.getmTemperature() + "");
            mIcon.setImageResource(hour.getIconId());
        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString();
            String temp = mTemperatureLabel.getText().toString();
            String summary = mSummaryLabel.getText().toString();
            String message = String.format("Ar %s it will be %s and %s",
                    time,temp,summary);
            Toast.makeText(mcontext, message, Toast.LENGTH_LONG).show();
        }
    }


}

