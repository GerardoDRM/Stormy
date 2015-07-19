package doge.thecraftcod.stormy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import doge.thecraftcod.stormy.R;
import doge.thecraftcod.stormy.weather.Day;

/**
 * Created by gerardo on 18/07/15.
 */
public class DayAdapter extends BaseAdapter {

    private Context context;
    private Day[] mDays;

    public DayAdapter(Context context, Day[] days) {
        this.context = context;
        this.mDays = days;
    }
    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; // Tag items for easy reference but I'm not going to use it.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // brand new
            convertView = LayoutInflater.from(context).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImg = (ImageView) convertView.findViewById(R.id.iconImg);
            holder.tempL = (TextView) convertView.findViewById(R.id.temperatureL);
            holder.dayL = (TextView) convertView.findViewById(R.id.dayNameL);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = mDays[position];
        holder.iconImg.setImageResource(day.getIconId());
        holder.tempL.setText(day.getmTemperatureMax() + "");

        if (position == 0) {
            holder.dayL.setText("Today");
        }
        else {
            holder.dayL.setText(day.getDayOfTheWeek());
        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView iconImg;
        TextView tempL;
        TextView dayL;
    }
}
